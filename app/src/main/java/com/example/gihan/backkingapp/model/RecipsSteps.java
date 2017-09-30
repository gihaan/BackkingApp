package com.example.gihan.backkingapp.model;

import java.io.Serializable;

/**
 * Created by Gihan on 9/20/2017.
 */

public class RecipsSteps implements Serializable{

    private int stepID;
    private String shortDescrptionOfStep;
    private String fullDescrptionOfStep;
    private String videoUrl;
    private String thumpUrl;

    public RecipsSteps(){

    }
    public RecipsSteps(int stepID, String shortDescrptionOfStep, String fullDescrptionOfStep, String videoUrl, String thumpUrl) {
        this.stepID = stepID;
        this.shortDescrptionOfStep = shortDescrptionOfStep;
        this.fullDescrptionOfStep = fullDescrptionOfStep;
        this.videoUrl = videoUrl;
        this.thumpUrl = thumpUrl;
    }

    public int getStepID() {
        return stepID;
    }

    public void setStepID(int stepID) {
        this.stepID = stepID;
    }

    public String getShortDescrptionOfStep() {
        return shortDescrptionOfStep;
    }

    public void setShortDescrptionOfStep(String shortDescrptionOfStep) {
        this.shortDescrptionOfStep = shortDescrptionOfStep;
    }

    public String getFullDescrptionOfStep() {
        return fullDescrptionOfStep;
    }

    public void setFullDescrptionOfStep(String fullDescrptionOfStep) {
        this.fullDescrptionOfStep = fullDescrptionOfStep;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumpUrl() {
        return thumpUrl;
    }

    public void setThumpUrl(String thumpUrl) {
        this.thumpUrl = thumpUrl;
    }
}
