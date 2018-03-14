package com.projects.shrungbhatt.medikit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
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
import com.projects.shrungbhatt.medikit.models.Res_HospitalList;
import com.projects.shrungbhatt.medikit.models.Res_SpecialityImage;
import com.projects.shrungbhatt.medikit.util.URLGenerator;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jigsaw on 12/3/18.
 */

public class Adapter_HospitalSpeciality extends
        RecyclerView.Adapter<Adapter_HospitalSpeciality.HospitalSpecialityViewHolder> {

    private Context mContext;
    private ArrayList<Res_HospitalList.List> mSpecialities;
    private List<String> mSpecialityArrayList = new ArrayList<>();//ArrayList for fetching images.

    public Adapter_HospitalSpeciality(Context context, ArrayList<Res_HospitalList.List> specialities) {
        mContext = context;
        mSpecialities = specialities;

        for (int i = 0; i < mSpecialities.size(); i++) {
            mSpecialityArrayList = Arrays.asList(mSpecialities.get(i).getHospitalSpecialities().
                    split(","));
        }

    }

    @Override
    public HospitalSpecialityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new HospitalSpecialityViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(HospitalSpecialityViewHolder holder, int position) {

        holder.mSpeciality.setText(mSpecialityArrayList.get(position));
        fetchSpecialityImage(holder.mHospitalSpeciality,
                mSpecialityArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return mSpecialityArrayList.size();
    }


    private void fetchSpecialityImage(final ImageView target, final String speciality) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLGenerator.FETCH_SPECIALITY_IMAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i = 0;i< jsonArray.length();i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Gson gson = new Gson();
                                Res_SpecialityImage res_specialityImage;
                                res_specialityImage = gson.fromJson(jsonObject.toString(), Res_SpecialityImage.class);
                                Picasso.get().load(res_specialityImage.getSpecialityImageUrl()).
                                        into(target);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                if (speciality != null) {
                    params.put("query", speciality);
                }
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(stringRequest);

    }

    class HospitalSpecialityViewHolder extends RecyclerView.ViewHolder {

        private ImageView mHospitalSpeciality;
        private TextView mSpeciality;

        HospitalSpecialityViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_diseases, parent, false));

            mHospitalSpeciality = itemView.findViewById(R.id.list_item_disease_image);

            mSpeciality = itemView.findViewById(R.id.list_item_disease_name);

        }


    }
}
