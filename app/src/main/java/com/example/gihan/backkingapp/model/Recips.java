package com.example.gihan.backkingapp.model;

import java.util.List;

/**
 * Created by Gihan on 9/20/2017.
 */

public class Recips {

    private int recipsID;
    private String recipsName;
    private List<RecipsIngerdiant>recipsIngerdiant;
    private List<RecipsSteps>recipsSteps;

    public Recips() {
    }

    public Recips(int recipsID, String recipsName, List<RecipsIngerdiant> recipsIngerdiant, List<RecipsSteps> recipsSteps) {
        this.recipsID = recipsID;
        this.recipsName = recipsName;
        this.recipsIngerdiant = recipsIngerdiant;
        this.recipsSteps = recipsSteps;
    }

    public int getRecipsID() {
        return recipsID;
    }

    public void setRecipsID(int recipsID) {
        this.recipsID = recipsID;
    }

    public String getRecipsName() {
        return recipsName;
    }

    public void setRecipsName(String recipsName) {
        this.recipsName = recipsName;
    }

    public List<RecipsIngerdiant> getRecipsIngerdiant() {
        return recipsIngerdiant;
    }

    public void setRecipsIngerdiant(List<RecipsIngerdiant> recipsIngerdiant) {
        this.recipsIngerdiant = recipsIngerdiant;
    }

    public List<RecipsSteps> getRecipsSteps() {
        return recipsSteps;
    }

    public void setRecipsSteps(List<RecipsSteps> recipsSteps) {
        this.recipsSteps = recipsSteps;
    }
}
