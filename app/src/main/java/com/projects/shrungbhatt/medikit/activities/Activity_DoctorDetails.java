package com.projects.shrungbhatt.medikit.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.projects.shrungbhatt.medikit.R;
import com.projects.shrungbhatt.medikit.adapters.Adapter_DoctorList;
import com.projects.shrungbhatt.medikit.models.Res_DoctorDetails;
import com.projects.shrungbhatt.medikit.util.URLGenerator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jigsaw on 16/3/18.
 */

public class Activity_DoctorDetails extends BaseActivity {

    private static final String EXTRA_DOCTOR_SPECIALITY = "doctor_speciality";
    private static final String EXTRA_HOSPITAL_NAME = "hospital_name";
    @BindView(R.id.activity_doctor_details_recycler_view)
    RecyclerView mActivityDoctorDetailsRecyclerView;

    public static Intent newIntent(Context context,String hospitalName,
                                   String doctorSpeciality){
        Intent intent = new Intent(context,Activity_DoctorDetails.class);
        intent.putExtra(EXTRA_HOSPITAL_NAME,hospitalName);
        intent.putExtra(EXTRA_DOCTOR_SPECIALITY,doctorSpeciality);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        ButterKnife.bind(this);

        String hospitalName = getIntent().getStringExtra(EXTRA_HOSPITAL_NAME);
        String doctorSpeciality = getIntent().getStringExtra(EXTRA_DOCTOR_SPECIALITY);

        mActivityDoctorDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchDoctorDetails(hospitalName, doctorSpeciality);

    }

    private void fetchDoctorDetails(final String hospitalName,final String doctorSpeciality){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLGenerator.FETCH_DOCTOR_DETAILS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Gson gson = new Gson();
                            Res_DoctorDetails resDoctorDetails;
                            resDoctorDetails = gson.fromJson(jsonObject.toString(),
                                    Res_DoctorDetails.class);
                            ArrayList<Res_DoctorDetails.List> list = resDoctorDetails.getList();
                            mActivityDoctorDetailsRecyclerView.setAdapter(new
                                    Adapter_DoctorList(Activity_DoctorDetails.this,list));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("hospital_name", hospitalName);
                params.put("doctor_speciality", doctorSpeciality);
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
