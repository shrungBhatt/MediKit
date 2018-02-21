package com.projects.shrungbhatt.medikit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiseaseListActivity extends AppCompatActivity {

    @BindView(R.id.disease_list_recycler_view)
    RecyclerView diseaseListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_list);
        ButterKnife.bind(this);



    }
}
