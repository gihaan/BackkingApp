package com.example.gihan.backkingapp.model;

/**
 * Created by Gihan on 9/20/2017.
 */

public class RecipsSteps {




    private String stepID;
    private String shortDescrptionOfStep;
    private String fullDescrptionOfStep;
    private String videoUrl;
    private String thumpUrl;

    public RecipsSteps(){

    }
    public RecipsSteps(String stepID, String shortDescrptionOfStep, String fullDescrptionOfStep, String videoUrl, String thumpUrl) {
        this.stepID = stepID;
        this.shortDescrptionOfStep = shortDescrptionOfStep;
        this.fullDescrptionOfStep = fullDescrptionOfStep;
        this.videoUrl = videoUrl;
        this.thumpUrl = thumpUrl;
    }

    public String getStepID() {
        return stepID;
    }

    public void setStepID(String stepID) {
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
