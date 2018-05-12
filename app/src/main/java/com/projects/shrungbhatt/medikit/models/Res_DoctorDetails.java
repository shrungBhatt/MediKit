package com.projects.shrungbhatt.medikit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jigsaw on 16/3/18.
 */

public class Res_DoctorDetails extends BaseModel {


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
        @SerializedName("doctor_name")
        @Expose
        private String doctorName;
        @SerializedName("photo_url")
        @Expose
        private String photoUrl;
        @SerializedName("doctor_speciality")
        @Expose
        private String doctorSpeciality;
        @SerializedName("hospital_name")
        @Expose
        private String hospitalName;
        @SerializedName("doctor_skills")
        @Expose
        private String doctorSkills;
        @SerializedName("qualification")
        @Expose
        private String qualification;
        @SerializedName("experience")
        @Expose
        private String experience;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public String getDoctorSpeciality() {
            return doctorSpeciality;
        }

        public void setDoctorSpeciality(String doctorSpeciality) {
            this.doctorSpeciality = doctorSpeciality;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getDoctorSkills() {
            return doctorSkills;
        }

        public void setDoctorSkills(String doctorSkills) {
            this.doctorSkills = doctorSkills;
        }

        public String getQualification() {
            return qualification;
        }

        public void setQualification(String qualification) {
            this.qualification = qualification;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

    }
}
