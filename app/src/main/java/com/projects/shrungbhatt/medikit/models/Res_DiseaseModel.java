package com.projects.shrungbhatt.medikit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jigsaw on 22/2/18.
 */

public class Res_DiseaseModel {


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
        @SerializedName("disease_name")
        @Expose
        private String diseaseName;
        @SerializedName("medicines_used")
        @Expose
        private String medicinesUsed;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDiseaseName() {
            return diseaseName;
        }

        public void setDiseaseName(String diseaseName) {
            this.diseaseName = diseaseName;
        }

        public String getMedicinesUsed() {
            return medicinesUsed;
        }

        public void setMedicinesUsed(String medicinesUsed) {
            this.medicinesUsed = medicinesUsed;
        }

    }

}
