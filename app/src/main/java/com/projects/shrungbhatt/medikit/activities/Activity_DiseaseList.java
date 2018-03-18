package com.projects.shrungbhatt.medikit.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import com.projects.shrungbhatt.medikit.adapters.Adapter_DiseaseList;
import com.projects.shrungbhatt.medikit.models.Res_DiseaseModel;
import com.projects.shrungbhatt.medikit.util.ItemDecorationAlbumColumns;
import com.projects.shrungbhatt.medikit.util.MySharedPreferences;
import com.projects.shrungbhatt.medikit.util.URLGenerator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activity_DiseaseList extends BaseActivity {

    @BindView(R.id.disease_list_recycler_view)
    RecyclerView diseaseListRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_list);
        ButterKnife.bind(this);


        diseaseListRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        diseaseListRecyclerView.addItemDecoration(new ItemDecorationAlbumColumns(1, 2));

        fetchDiseases(",");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.log_out_menu_item) {
            if(isNetworkAvailableAndConnected()){
                MySharedPreferences.setStoredLoginStatus(Activity_DiseaseList.this,false);
                Intent i = new Intent(Activity_DiseaseList.this, Activity_Login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show();
            }
            return true;
        }else if(id == R.id.hospitals){
            startActivity(new Intent(this,Activity_HospitalList.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                fetchDiseases(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }
        });
        return true;
    }

    private void fetchDiseases(final String query) {
        showProgressBar(Activity_DiseaseList.this,"TAGG");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLGenerator.FETCH_DISEASES_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Gson gson = new Gson();
                            Res_DiseaseModel res_diseaseModel;
                            res_diseaseModel = gson.fromJson(jsonObject.toString(), Res_DiseaseModel.class);
                            ArrayList<Res_DiseaseModel.List> list = res_diseaseModel.getList();
                            hideProgressBar();
                            diseaseListRecyclerView.setAdapter(new
                                    Adapter_DiseaseList(Activity_DiseaseList.this,
                                    list));

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
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                if (query != null) {
                    params.put("query", query);
                }
                return params;
            }

        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




}
