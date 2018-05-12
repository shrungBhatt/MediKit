package com.projects.shrungbhatt.medikit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.projects.shrungbhatt.medikit.R;
import com.projects.shrungbhatt.medikit.models.Res_DoctorDetails;
import com.projects.shrungbhatt.medikit.util.MySharedPreferences;
import com.projects.shrungbhatt.medikit.util.URLGenerator;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class Activity_DoctorProfile extends BaseActivity {

    @BindView(R.id.doctor_profile_image)
    CircleImageView mDoctorProfileImage;
    @BindView(R.id.doctor_name_edit_text)
    EditText mDoctorNameEditText;
    @BindView(R.id.doctor_qualification_edit_text)
    EditText mDoctorQualificationEditText;
    @BindView(R.id.doctor_experience_edit_text)
    EditText mDoctorExperienceEditText;
    @BindView(R.id.edit_btn)
    Button mEditBtn;
    private String TAG = getClass().getSimpleName();
    private ArrayList<Res_DoctorDetails.List> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
        ButterKnife.bind(this);

        fetchDoctorDetails(MySharedPreferences.getStoredUsername(this));

    }

    private void fetchDoctorDetails(final String doctorName) {
        showProgressBar(this, TAG);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLGenerator.FETCH_DOCTOR_USER_PROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideProgressBar();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Gson gson = new Gson();
                            Res_DoctorDetails resDoctorDetails;
                            resDoctorDetails = gson.fromJson(jsonObject.toString(),
                                    Res_DoctorDetails.class);
                            list = resDoctorDetails.getList();

                            mDoctorNameEditText.setText(MySharedPreferences.
                                    getStoredUsername(Activity_DoctorProfile.this));
                            Picasso.get().load(list.get(0).getPhotoUrl()).into(mDoctorProfileImage);
                            mDoctorExperienceEditText.setText(list.get(0).getExperience());
                            mDoctorQualificationEditText.setText(list.get(0).getQualification());

                        } catch (JSONException e) {
                            hideProgressBar();
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgressBar();
                error.getMessage();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("doctor_name", doctorName);
                return params;
            }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void updateDocProfile(final String docName,
                                  final String expChange,
                                  final String qualificationChange,
                                  final String docChangeName) {

        showProgressBar(this, TAG);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLGenerator.UPDATE_DOCTOR_USER_PROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideProgressBar();
                        if (response.equalsIgnoreCase("Updated status")) {
                            Toast.makeText(Activity_DoctorProfile.this, "Changes Made", Toast.LENGTH_SHORT).show();
                            MySharedPreferences.setStoredUsername(Activity_DoctorProfile.this, docChangeName);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgressBar();


            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("doctor_name", docName);
                params.put("doctor_exp", expChange);
                params.put("doctor_qualification", qualificationChange);
                params.put("change_doc_name", docChangeName);
                return params;
            }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    @OnClick(R.id.edit_btn)
    public void onViewClicked() {
        updateDocProfile(MySharedPreferences.getStoredUsername(this),
                mDoctorExperienceEditText.getText().toString(),
                mDoctorQualificationEditText.getText().toString(),
                mDoctorNameEditText.getText().toString());
    }
}
