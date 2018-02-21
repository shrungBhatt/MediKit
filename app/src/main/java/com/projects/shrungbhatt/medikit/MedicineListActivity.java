package com.projects.shrungbhatt.medikit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jigsaw on 22/2/18.
 */

public class MedicineListActivity extends AppCompatActivity {

    private static final String EXTRA_MEDICINE = "medicine_string";
    @BindView(R.id.medicine_list_recycler_view)
    RecyclerView medicineListRecyclerView;

    private String mMedicineListString;
    private List<String> mMedicines;

    public static Intent newIntent(Context context, String medicines) {
        Intent intent = new Intent(context, MedicineListActivity.class);
        intent.putExtra(EXTRA_MEDICINE, medicines);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);
        ButterKnife.bind(this);

        mMedicineListString = getIntent().getStringExtra(EXTRA_MEDICINE);

        mMedicines = Arrays.asList(mMedicineListString.trim().split(","));
        medicineListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        medicineListRecyclerView.setAdapter(new Adapter_MedicineList(this,mMedicines));

    }
}
