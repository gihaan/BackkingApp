package com.example.gihan.backkingapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Gihan on 9/20/2017.
 */

public class Recips extends ArrayList<Parcelable> implements Parcelable{

    private int recipsID;
    private String recipsName;
    private List<RecipsIngerdiant>recipsIngerdiant;
    private List<RecipsSteps>recipsSteps;
    private String serving;
    private String image;


    public Recips() {
    }


    public Recips(int recipsID, String recipsName, List<RecipsIngerdiant> recipsIngerdiant, List<RecipsSteps> recipsSteps, String serving, String image) {
        this.recipsID = recipsID;
        this.recipsName = recipsName;
        this.recipsIngerdiant = recipsIngerdiant;
        this.recipsSteps = recipsSteps;
        this.serving = serving;
        this.image = image;
    }

    public Recips(@NonNull Collection<? extends Parcelable> c, int recipsID, String recipsName, List<RecipsIngerdiant> recipsIngerdiant, List<RecipsSteps> recipsSteps, String serving, String image) {
        super(c);
        this.recipsID = recipsID;
        this.recipsName = recipsName;
        this.recipsIngerdiant = recipsIngerdiant;
        this.recipsSteps = recipsSteps;
        this.serving = serving;
        this.image = image;
    }

    protected Recips(Parcel in) {
        recipsID = in.readInt();
        recipsName = in.readString();
        recipsSteps = in.createTypedArrayList(RecipsSteps.CREATOR);
        serving = in.readString();
        image = in.readString();
    }

    public static final Creator<Recips> CREATOR = new Creator<Recips>() {
        @Override
        public Recips createFromParcel(Parcel in) {
            return new Recips(in);
        }

        @Override
        public Recips[] newArray(int size) {
            return new Recips[size];
        }
    };

    public String getServing() {
        return serving;
    }

    public void setServing(String serving) {
        this.serving = serving;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static Creator<Recips> getCREATOR() {
        return CREATOR;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(recipsID);
        dest.writeString(recipsName);
        dest.writeTypedList(recipsSteps);
        dest.writeString(serving);
        dest.writeString(image);
    }
}
