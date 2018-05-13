package com.projects.shrungbhatt.medikit.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.projects.shrungbhatt.medikit.R;
import com.projects.shrungbhatt.medikit.models.Res_DoctorDetails;
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

public class Activity_DoctorProfileDetails extends BaseActivity {


    private static String EXTRA_DOCTOR_NAME = "extra_doctor_name";
    @BindView(R.id.user_profile_close)
    ImageView mUserProfileClose;
    @BindView(R.id.doctor_profile_image)
    CircleImageView mDoctorProfileImage;
    @BindView(R.id.user_profile_user_name)
    TextView mUserProfileUserName;
    @BindView(R.id.doctor_qualification_tv)
    TextView mDoctorQualificationTv;
    @BindView(R.id.doctor_speciality_tv)
    TextView mDoctorSpecialityTv;
    @BindView(R.id.doctor_experience)
    TextView mDoctorExperience;
    @BindView(R.id.doctor_skills)
    TextView mDoctorSkills;


    private String mDoctorName;
    private String TAG = getClass().getSimpleName();
    private ArrayList<Res_DoctorDetails.List> mDoctorDetails;

    public static Intent newIntent(Context context, String doctorName) {
        Intent intent = new Intent(context, Activity_DoctorProfileDetails.class);
        intent.putExtra(EXTRA_DOCTOR_NAME, doctorName);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile_details);
        ButterKnife.bind(this);

        mDoctorName = getIntent().getStringExtra(EXTRA_DOCTOR_NAME);

        fetchDoctorDetails(mDoctorName);

        mUserProfileClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
                            mDoctorDetails = resDoctorDetails.getList();

                            mUserProfileUserName.setText(mDoctorName);
                            mDoctorExperience.setText(mDoctorDetails.get(0).getExperience());
                            Picasso.get().load(mDoctorDetails.get(0).getPhotoUrl()).into(mDoctorProfileImage);
                            mDoctorQualificationTv.setText(mDoctorDetails.get(0).getQualification());
                            mDoctorSpecialityTv.setText(mDoctorDetails.get(0).getDoctorSpeciality());
                            mDoctorSkills.setText(mDoctorDetails.get(0).getDoctorSkills());

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

    @Override
    protected void onResume() {
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    @OnClick(R.id.user_profile_close)
    public void onViewClicked() {
    }
}
