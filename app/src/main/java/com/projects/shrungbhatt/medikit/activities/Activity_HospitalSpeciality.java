package com.projects.shrungbhatt.medikit.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.projects.shrungbhatt.medikit.R;
import com.projects.shrungbhatt.medikit.adapters.Adapter_HospitalSpeciality;
import com.projects.shrungbhatt.medikit.models.Res_HospitalList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jigsaw on 12/3/18.
 */

public class Activity_HospitalSpeciality extends BaseActivity {


    private static final String EXTRA_SPECIALITY_LIST = "speciality_list";
    private static final String EXTRA_HOSPITAL_SELECTED = "hospital_selected";
    @BindView(R.id.disease_list_recycler_view)
    RecyclerView mHospitalSpecialityRecyclerView;
    private ArrayList<Res_HospitalList.List> mHospitalList;
    private int mHospitalSelected;

    public static Intent newIntent(Context context, ArrayList<Res_HospitalList.List> arrayList,
                                   int hospitalSelected) {
        Intent intent = new Intent(context, Activity_HospitalSpeciality.class);
        intent.putExtra(EXTRA_SPECIALITY_LIST, arrayList);
        intent.putExtra(EXTRA_HOSPITAL_SELECTED, hospitalSelected);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_list);
        ButterKnife.bind(this);


        mHospitalList = (ArrayList<Res_HospitalList.List>) getIntent().
                getSerializableExtra(EXTRA_SPECIALITY_LIST);

        mHospitalSelected = getIntent().getIntExtra(EXTRA_HOSPITAL_SELECTED,0);
        mHospitalSpecialityRecyclerView.setLayoutManager(new GridLayoutManager(this,
                2));
        mHospitalSpecialityRecyclerView.setAdapter(new Adapter_HospitalSpeciality(this,
                mHospitalList,mHospitalSelected));
    }
}
