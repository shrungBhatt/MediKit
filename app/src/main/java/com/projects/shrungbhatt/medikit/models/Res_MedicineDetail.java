package com.projects.shrungbhatt.medikit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jigsaw on 22/2/18.
 */

public class Res_MedicineDetail extends BaseModel {

    @SerializedName("list")
    @Expose
    private ArrayList<List> list = null;

    public ArrayList<List> getList() {
        return list;
    }

    public void setList(ArrayList<List> list) {
        this.list = list;
    }

    public class List extends BaseModel {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("medicine_name")
        @Expose
        private String medicineName;
        @SerializedName("medicine_desc")
        @Expose
        private String medicineDesc;
        @SerializedName("medicine_use")
        @Expose
        private String medicineUse;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMedicineName() {
            return medicineName;
        }

        public void setMedicineName(String medicineName) {
            this.medicineName = medicineName;
        }

        public String getMedicineDesc() {
            return medicineDesc;
        }

        public void setMedicineDesc(String medicineDesc) {
            this.medicineDesc = medicineDesc;
        }

        public String getMedicineUse() {
            return medicineUse;
        }

        public void setMedicineUse(String medicineUse) {
            this.medicineUse = medicineUse;
        }

    }
}
