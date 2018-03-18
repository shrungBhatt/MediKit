package com.projects.shrungbhatt.medikit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.projects.shrungbhatt.medikit.R;
import com.projects.shrungbhatt.medikit.adapters.Adapter_DoctorAppointmentsList;
import com.projects.shrungbhatt.medikit.listeners.CallBack;
import com.projects.shrungbhatt.medikit.listeners.UpdateAppointmentStatus;
import com.projects.shrungbhatt.medikit.models.Res_AppointmentsList;
import com.projects.shrungbhatt.medikit.util.MySharedPreferences;
import com.projects.shrungbhatt.medikit.util.URLGenerator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jigsaw on 18/3/18.
 */

public class Activity_DoctorAppointmentList extends BaseActivity implements UpdateAppointmentStatus,
        CallBack {

    @BindView(R.id.doctor_appointment_list_recycler_view)
    RecyclerView mDoctorAppointmentListRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment_list);
        ButterKnife.bind(this);

        mDoctorAppointmentListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchAppointments(MySharedPreferences.getStoredUsername(this));

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.log_out_menu_item) {
            if(isNetworkAvailableAndConnected()){
                MySharedPreferences.setStoredLoginStatus(Activity_DoctorAppointmentList.this,false);
                MySharedPreferences.setIsAdminLoggedOn(this,false);
                Intent i = new Intent(Activity_DoctorAppointmentList.this, Activity_Login.class);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu, menu);

        return true;
    }


    private void fetchAppointments(final String doctorName) {
        showProgressBar(this, "");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLGenerator.FETCH_DOCTOR_APPOINTMENTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideProgressBar();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Gson gson = new Gson();
                            Res_AppointmentsList resAppointmentsList;
                            resAppointmentsList = gson.fromJson(jsonObject.toString(), Res_AppointmentsList.class);

                            mDoctorAppointmentListRecyclerView.setAdapter(new
                                    Adapter_DoctorAppointmentsList(Activity_DoctorAppointmentList.this
                                    ,resAppointmentsList.getList(),
                                    Activity_DoctorAppointmentList.this));
                        } catch (JSONException e) {
                            hideProgressBar();
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgressBar();
                error.printStackTrace();

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
    public void callBack() {
        fetchAppointments(MySharedPreferences.getStoredUsername(this));
    }

    @Override
    public void updateAppointmentStatus(String appointmentId, int statusId) {
        updateAppointmentStatus(appointmentId,
                String.valueOf(statusId), this);
    }
}
