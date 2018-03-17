package com.projects.shrungbhatt.medikit.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projects.shrungbhatt.medikit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jigsaw on 17/3/18.
 */

public class Activity_BookAppointment extends BaseActivity {


    private static final String EXTRA_HOSPITAL_NAME = "hospital_name";
    private static final String EXTRA_DOCTOR_NAME = "doctor_name";
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
                break;
        }
    }
}
