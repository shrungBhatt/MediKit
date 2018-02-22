package com.projects.shrungbhatt.medikit;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jigsaw on 22/2/18.
 */

public class MedicineDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MEDICINE_NAME = "medicine_name";

    @BindView(R.id.medicine_detail_medicine_name_tv)
    TextView medicineDetailMedicineNameTv;
    @BindView(R.id.medicine_detail_description_tv)
    TextView medicineDetailDescriptionTv;
    @BindView(R.id.medicine_detail_how_to_use_tv)
    TextView medicineDetailHowToUseTv;

    private Dialog mProgressDialog;

    private String mMedicineName;

    public static Intent newIntent(Context context,String medicineName){
        Intent intent = new Intent(context,MedicineDetailActivity.class);
        intent.putExtra(EXTRA_MEDICINE_NAME,medicineName);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail);
        ButterKnife.bind(this);

        mMedicineName = getIntent().getStringExtra(EXTRA_MEDICINE_NAME);

        medicineDetailMedicineNameTv.setText(mMedicineName);

        fetchMedicineDetails(mMedicineName);
    }

    private void fetchMedicineDetails(final String medicineName){
        showProgressBar(MedicineDetailActivity.this,"TAG");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://ersnexus.esy.es/fetch_medicine_detail.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            Gson gson = new Gson();
                            Res_MedicineDetail res_medicineDetail;
                            res_medicineDetail = gson.fromJson(jsonObject.toString(), Res_MedicineDetail.class);
                            ArrayList<Res_MedicineDetail.List> list = res_medicineDetail.getList();
                            medicineDetailDescriptionTv.setText(list.get(0).getMedicineDesc());
                            medicineDetailHowToUseTv.setText(list.get(0).getMedicineUse());
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
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                if (medicineName != null) {
                    params.put("medicine_name", medicineName);
                }
                return params;
            }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void showProgressBar(Context context, String TAG) {
        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.setContentView(R.layout.circleprogress);
        mProgressDialog.setCancelable(false);

        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mProgressDialog.getWindow().setGravity(Gravity.CENTER);
        try {
            mProgressDialog.show();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public void hideProgressBar() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        } catch (Exception e) {
            Log.e("BaseClassForInterface", "Error in hideProgressBar");
        }
    }
}
