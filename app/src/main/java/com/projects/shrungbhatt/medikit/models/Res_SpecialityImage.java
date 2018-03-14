package com.projects.shrungbhatt.medikit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jigsaw on 14/3/18.
 */

public class Res_SpecialityImage extends BaseModel {


    @SerializedName("speciality_image_url")
    @Expose
    private String specialityImageUrl;

    public String getSpecialityImageUrl() {
        return specialityImageUrl;
    }

    public void setSpecialityImageUrl(String specialityImageUrl) {
        this.specialityImageUrl = specialityImageUrl;
    }

}
