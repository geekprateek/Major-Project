package com.fewstera.injectablemedicinesguide.models;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;

import com.fewstera.injectablemedicinesguide.database.DatabaseHelper;

public class Drug implements Comparable<Drug>{
	
	private int _id;
	private String _name;
	private String _route;
	private String _tradeName;
    private String _medicineName;
    private String _version;
    private Date _datePublished;
    private DrugCalculatorInfo _calculatorInfo;

    private ArrayList<DrugInformation> _drugInformations;

    public int getId(){
        return _id;
    }

    public void setId(int id){
        _id = id;
    }

    public String getName(){
        return _name;
    }

    public void setName(String name){
        _name = name;
    }

    public String getRoute() {
        return _route;
    }

    public void setRoute(String route) {
        this._route = route;
    }

    public String getTradeName() {
        return _tradeName;
    }

    public void setTradeName(String tradeName) {
        _tradeName = tradeName;
    }

    public String getMedicineName() {
        return _medicineName;
    }

    public void setMedicineName(String medicineName) {
        _medicineName = medicineName;
    }

    public String getVersion() {
        return _version;
    }

    public void setVersion(String version) {
        _version = version;
    }


    public Date getDatePublished() {
        return _datePublished;
    }

    public void setDatePublished(Date datePublished) {
        _datePublished = datePublished;
    }

    // Add new information about a drug
    public void addDrugInformation(DrugInformation drugInfo){
        if(_drugInformations==null){ _drugInformations = new ArrayList<DrugInformation>(); }

        _drugInformations.add(drugInfo);
    }

    //Get an ArrayList of all the drug information
    public ArrayList<DrugInformation> getDrugInformations(){
        if(_drugInformations==null){ _drugInformations = new ArrayList<DrugInformation>(); }
        return _drugInformations;
    }

    //Get an ArrayList of all the drug information from database
    public ArrayList<DrugInformation> getDrugInformations(Context context){
        if(_drugInformations==null){
            DatabaseHelper db = new DatabaseHelper(context);
            _drugInformations = db.getDrugInformationsFromDrugId(this.getId());
        }
        return _drugInformations;
    }

    public DrugCalculatorInfo getCalculatorInfo(Context context){
        if(_calculatorInfo==null){
            DatabaseHelper db = new DatabaseHelper(context);
            _calculatorInfo = db.getDrugCalcInfoFromDrugId(this.getId());
        }
        return _calculatorInfo;
    }

    public int compareTo(Drug test) {
        return getName().compareTo(test.getName());
    }

    @Override
    public String toString(){
        return getName();
    }
	
}