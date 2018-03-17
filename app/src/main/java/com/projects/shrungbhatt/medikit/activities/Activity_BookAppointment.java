package com.projects.shrungbhatt.medikit.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.projects.shrungbhatt.medikit.R;
import com.projects.shrungbhatt.medikit.util.MySharedPreferences;
import com.projects.shrungbhatt.medikit.util.URLGenerator;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jigsaw on 17/3/18.
 */

public class Activity_BookAppointment extends BaseActivity implements Validator.ValidationListener {


    private static final String EXTRA_HOSPITAL_NAME = "hospital_name";
    private static final String EXTRA_DOCTOR_NAME = "doctor_name";
    @NotEmpty
    @BindView(R.id.appointment_description)
    AutoCompleteTextView mAppointmentDescription;
    @BindView(R.id.tv_setdate)
    TextView mTvSetdate;
    @BindView(R.id.appointment_Date)
    LinearLayout mAppointmentDate;
    @BindView(R.id.tv_settimeform)
    TextView mTvSettimeform;
    @BindView(R.id.appointment_from)
    CardView mAppointmentFrom;
    @BindView(R.id.appointment_to)
    CardView mAppointmentTo;
    @BindView(R.id.add_task_fab_button)
    FloatingActionButton mAddTaskFabButton;
    @BindView(R.id.appointment_to_tv)
    TextView mAppointmentToTv;
    private String mHospitalName;
    private String mDoctorName;
    private Validator mValidator;


    public static Intent newIntent(Context context,String hospitalName,String doctorName){
        Intent intent = new Intent(context,Activity_BookAppointment.class);
        intent.putExtra(EXTRA_HOSPITAL_NAME,hospitalName);
        intent.putExtra(EXTRA_DOCTOR_NAME,doctorName);
        return intent;

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        ButterKnife.bind(this);

        mValidator = new Validator(this);
        mValidator.setValidationListener(this);

        mHospitalName = getIntent().getStringExtra(EXTRA_HOSPITAL_NAME);
        mDoctorName = getIntent().getStringExtra(EXTRA_DOCTOR_NAME);

    }

    @OnClick({R.id.appointment_Date, R.id.appointment_from, R.id.appointment_to, R.id.add_task_fab_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.appointment_Date:
                datePickerDialog(mTvSetdate);
                break;
            case R.id.appointment_from:
                fromTimePickerDialog(mTvSettimeform);
                break;
            case R.id.appointment_to:
                toTimePickerDialog(mAppointmentToTv);
                break;
            case R.id.add_task_fab_button:
                mValidator.validate();
                break;
        }
    }

    private void addAppointment(Context context,
                                final String userName,
                                final String doctorName,
                                final String hospitalName,
                                final String description,
                                final int statusId,
                                final String timeFrom,
                                final String timeTo,
                                final String date){
        showProgressBar(context,"tag");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLGenerator.ADD_APPOINTMENT_DETAILS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideProgressBar();
                        if(response.equalsIgnoreCase("Booked Appointment")){
                            showToastMessage("Booked Appointment");
                        }else{
                            showToastMessage("Something went wrong");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgressBar();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_name",userName);
                params.put("doctor_name",doctorName);
                params.put("hospital_name",hospitalName);
                params.put("description",description);
                params.put("appointment_status_id",String.valueOf(statusId));
                params.put("appointment_from",timeFrom);
                params.put("appointment_to",timeTo);
                params.put("appointment_date",date);

                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onValidationSucceeded() {
        if(TimeValidationwith12(mTvSettimeform.getText().toString(),
                mAppointmentToTv.getText().toString(),
                "Time slot for appointment",this)){
            addAppointment(this, MySharedPreferences.getStoredUsername(this),
                    mDoctorName,
                    mHospitalName,
                    mAppointmentDescription.getText().toString(),
                    1,
                    mTvSettimeform.getText().toString(),
                    mAppointmentToTv.getText().toString(),
                    mTvSetdate.getText().toString());
        }else {
            showErrorDialog("Please select proper time slot");
        }

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
