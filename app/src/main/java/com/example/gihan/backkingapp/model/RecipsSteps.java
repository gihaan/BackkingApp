package com.example.gihan.backkingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Gihan on 9/20/2017.
 */

public class RecipsSteps implements Parcelable{

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

    protected RecipsSteps(Parcel in) {
        stepID = in.readInt();
        shortDescrptionOfStep = in.readString();
        fullDescrptionOfStep = in.readString();
        videoUrl = in.readString();
        thumpUrl = in.readString();
    }

    public static final Creator<RecipsSteps> CREATOR = new Creator<RecipsSteps>() {
        @Override
        public RecipsSteps createFromParcel(Parcel in) {
            return new RecipsSteps(in);
        }

        @Override
        public RecipsSteps[] newArray(int size) {
            return new RecipsSteps[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(stepID);
        dest.writeString(shortDescrptionOfStep);
        dest.writeString(fullDescrptionOfStep);
        dest.writeString(videoUrl);
        dest.writeString(thumpUrl);
    }
}
