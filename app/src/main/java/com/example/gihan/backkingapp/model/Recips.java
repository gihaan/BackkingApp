package com.example.gihan.backkingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gihan on 9/20/2017.
 */

public class Recips extends ArrayList<Parcelable> implements Parcelable{

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

    protected Recips(Parcel in) {
        recipsID = in.readInt();
        recipsName = in.readString();
        recipsSteps = in.createTypedArrayList(RecipsSteps.CREATOR);
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
    }
}
