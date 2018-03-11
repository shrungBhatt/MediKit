package com.projects.shrungbhatt.medikit.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.projects.shrungbhatt.medikit.R;
import com.projects.shrungbhatt.medikit.adapters.Adapter_HospitalList;
import com.projects.shrungbhatt.medikit.models.Res_HospitalList;
import com.projects.shrungbhatt.medikit.util.URLGenerator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jigsaw on 12/3/18.
 */

public class Activity_HospitalList extends BaseActivity {

    private static final String TAG = "Activity_HospitalList";
    @BindView(R.id.activity_hospital_list_recycler_view)
    RecyclerView mActivityHospitalListRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);
        ButterKnife.bind(this);

        mActivityHospitalListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchHospitals(",",this);
    }

    private void fetchHospitals(final String query,final Context context){
        showProgressBar(this,TAG);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLGenerator.FETCH_HOSPITALS_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Gson gson = new Gson();
                            Res_HospitalList res_hospitalList;
                            res_hospitalList = gson.fromJson(jsonObject.toString(),Res_HospitalList.class);
                            ArrayList<Res_HospitalList.List> arrayList =
                                    res_hospitalList.getList();
                            mActivityHospitalListRecyclerView.setAdapter(new Adapter_HospitalList(context,
                                    arrayList));
                            hideProgressBar();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            hideProgressBar();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                hideProgressBar();

            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                if (query != null) {
                    params.put("query", query);
                }
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
}
