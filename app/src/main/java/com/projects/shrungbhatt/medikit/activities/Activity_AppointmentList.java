package com.projects.shrungbhatt.medikit.activities;

import android.content.Context;
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
import com.projects.shrungbhatt.medikit.adapters.Adapter_AppointmentsList;
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
 * Created by jigsaw on 17/3/18.
 */

public class Activity_AppointmentList extends BaseActivity implements UpdateAppointmentStatus,
        CallBack {


    @BindView(R.id.appointment_list_recycler_view)
    RecyclerView mAppointmentListRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);
        ButterKnife.bind(this);

        mAppointmentListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchAppointments(MySharedPreferences.getStoredUsername(this), this);

    }

    public void fetchAppointments(final String userName, final Context context) {
        showProgressBar(this, "Activity_AppointmentList");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLGenerator.FETCH_APPOINTMENTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideProgressBar();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Gson gson = new Gson();
                            Res_AppointmentsList resAppointmentsList;
                            resAppointmentsList = gson.fromJson(jsonObject.toString(), Res_AppointmentsList.class);
                            mAppointmentListRecyclerView.setAdapter(new Adapter_AppointmentsList(context,
                                    resAppointmentsList.getList(),
                                    Activity_AppointmentList.this));
                        } catch (JSONException e) {
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
                params.put("user_name", userName);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void updateAppointmentStatus(String appointmentId, int statusId) {
        updateAppointmentStatus(appointmentId,
                String.valueOf(statusId), this);
    }

    @Override
    public void callBack() {
        fetchAppointments(MySharedPreferences.getStoredUsername(this), this);
    }
}
