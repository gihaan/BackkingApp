package com.example.gihan.backkingapp.model;

/**
 * Created by Gihan on 9/20/2017.
 */

public class RecipsIngerdiant {



    private String ingrediantQuality;
    private String meaureOfIngerdiant;
    private String ingerdiantName;

    public RecipsIngerdiant() {
    }

    public RecipsIngerdiant(String ingrediantQuality, String meaureOfIngerdiant, String ingerdiantName) {
        this.ingrediantQuality = ingrediantQuality;
        this.meaureOfIngerdiant = meaureOfIngerdiant;
        this.ingerdiantName = ingerdiantName;
    }

    public String getIngrediantQuality() {
        return ingrediantQuality;
    }

    public void setIngrediantQuality(String ingrediantQuality) {
        this.ingrediantQuality = ingrediantQuality;
    }

    public String getMeaureOfIngerdiant() {
        return meaureOfIngerdiant;
    }

    public void setMeaureOfIngerdiant(String meaureOfIngerdiant) {
        this.meaureOfIngerdiant = meaureOfIngerdiant;
    }

    public String getIngerdiantName() {
        return ingerdiantName;
    }

    public void setIngerdiantName(String ingerdiantName) {
        this.ingerdiantName = ingerdiantName;
    }
}
