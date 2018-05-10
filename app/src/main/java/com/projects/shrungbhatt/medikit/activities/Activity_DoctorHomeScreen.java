package com.projects.shrungbhatt.medikit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.shrungbhatt.medikit.R;
import com.projects.shrungbhatt.medikit.util.MySharedPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_DoctorHomeScreen extends BaseActivity {

    @BindView(R.id.doctor_name_tv)
    TextView mDoctorNameTv;
    @BindView(R.id.doctor_profile)
    CardView mDoctorProfile;
    @BindView(R.id.doctor_appointments)
    CardView mDoctorAppointments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_homescree);
        ButterKnife.bind(this);


        mDoctorNameTv.setText(MySharedPreferences.getStoredUsername(this));
    }

    @OnClick({R.id.doctor_profile, R.id.doctor_appointments})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.doctor_profile:
                startActivity(new Intent(this,Activity_DoctorProfile.class));
                break;
            case R.id.doctor_appointments:
                startActivity(new Intent(this,Activity_DoctorAppointmentList.class));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.log_out_menu_item) {
            if(isNetworkAvailableAndConnected()){
                MySharedPreferences.setStoredLoginStatus(
                        this,false);
                MySharedPreferences.setIsAdminLoggedOn(this,false);
                Intent i = new Intent(this, Activity_Login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
