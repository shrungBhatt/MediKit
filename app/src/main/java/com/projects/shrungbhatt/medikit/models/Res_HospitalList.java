package com.projects.shrungbhatt.medikit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jigsaw on 12/3/18.
 */

public class Res_HospitalList {

    @SerializedName("list")
    @Expose
    private ArrayList<List> list = null;

    public ArrayList<List> getList() {
        return list;
    }

    public void setList(ArrayList<List> list) {
        this.list = list;
    }

    public class List {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("hospital_name")
        @Expose
        private String hospitalName;
        @SerializedName("hospital_image_url")
        @Expose
        private String hospitalImageUrl;
        @SerializedName("hospital_address")
        @Expose
        private String hospitalAddress;
        @SerializedName("hospital_contact")
        @Expose
        private String hospitalContact;
        @SerializedName("hospital_rating")
        @Expose
        private String hospitalRating;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getHospitalImageUrl() {
            return hospitalImageUrl;
        }

        public void setHospitalImageUrl(String hospitalImageUrl) {
            this.hospitalImageUrl = hospitalImageUrl;
        }

        public String getHospitalAddress() {
            return hospitalAddress;
        }

        public void setHospitalAddress(String hospitalAddress) {
            this.hospitalAddress = hospitalAddress;
        }

        public String getHospitalContact() {
            return hospitalContact;
        }

        public void setHospitalContact(String hospitalContact) {
            this.hospitalContact = hospitalContact;
        }

        public String getHospitalRating() {
            return hospitalRating;
        }

        public void setHospitalRating(String hospitalRating) {
            this.hospitalRating = hospitalRating;
        }

    }
}
