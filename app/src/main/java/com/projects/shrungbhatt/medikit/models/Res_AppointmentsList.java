package com.projects.shrungbhatt.medikit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jigsaw on 17/3/18.
 */

public class Res_AppointmentsList extends BaseModel {


    @SerializedName("list")
    @Expose
    private ArrayList<List> list = null;

    public ArrayList<List> getList() {
        return list;
    }

    public void setList(ArrayList<List> list) {
        this.list = list;
    }



    public class List extends BaseModel{

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("user_name")
        @Expose
        private String userName;
        @SerializedName("doctor_name")
        @Expose
        private String doctorName;
        @SerializedName("hospital_name")
        @Expose
        private String hospitalName;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("appointment_status_id")
        @Expose
        private String appointmentStatusId;
        @SerializedName("appointment_from")
        @Expose
        private String appointmentFrom;
        @SerializedName("appointment_to")
        @Expose
        private String appointmentTo;
        @SerializedName("appointment_date")
        @Expose
        private String appointmentDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAppointmentStatusId() {
            return appointmentStatusId;
        }

        public void setAppointmentStatusId(String appointmentStatusId) {
            this.appointmentStatusId = appointmentStatusId;
        }

        public String getAppointmentFrom() {
            return appointmentFrom;
        }

        public void setAppointmentFrom(String appointmentFrom) {
            this.appointmentFrom = appointmentFrom;
        }

        public String getAppointmentTo() {
            return appointmentTo;
        }

        public void setAppointmentTo(String appointmentTo) {
            this.appointmentTo = appointmentTo;
        }

        public String getAppointmentDate() {
            return appointmentDate;
        }

        public void setAppointmentDate(String appointmentDate) {
            this.appointmentDate = appointmentDate;
        }

    }
}
