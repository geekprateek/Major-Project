package com.fewstera.injectablemedicinesguide.dataDownload;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.fewstera.injectablemedicinesguide.R;
import com.octo.android.robospice.UncachedSpiceService;
import com.octo.android.robospice.request.notifier.RequestListenerNotifier;

/**
 * Created by fewstera on 23/03/2014.
 */
public class DownloadService  extends UncachedSpiceService {
    @Override
    public int getThreadCount() {
        return 3;
    }

    @Override
    protected RequestListenerNotifier createRequestRequestListenerNotifier() {
        return new DownloadRequestListenerNotifier();
    }

    // Suppressing deprecation, as deprecated method have to be used for older device notifications
    @SuppressWarnings("deprecation")
    @Override
    public Notification createDefaultNotification() {
        String title = getResources().getString(R.string.app_name);
        String subTitle = getResources().getString(R.string.service_message);

        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notification = new Notification.Builder(this).setContentTitle(title)
                    .setContentText(subTitle).setSmallIcon(getApplicationInfo().icon)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)).build();
        } else if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            notification = new Notification.Builder(this).setSmallIcon(getApplicationInfo().icon)
                    .setContentTitle(title).setContentText(subTitle)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)).getNotification();
        } else {
            notification = new Notification();
            notification.icon = getApplicationInfo().icon;
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);
            notification.setLatestEventInfo(this, title, subTitle, pendingIntent);
            notification.tickerText = null;
            notification.when = System.currentTimeMillis();
        }

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notification.priority = Notification.PRIORITY_MIN;
        }

        return notification;
    }
}