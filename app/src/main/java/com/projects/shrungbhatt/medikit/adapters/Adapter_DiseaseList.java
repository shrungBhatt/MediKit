package com.projects.shrungbhatt.medikit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.shrungbhatt.medikit.activities.Activity_MedicineList;
import com.projects.shrungbhatt.medikit.R;
import com.projects.shrungbhatt.medikit.models.Res_DiseaseModel;

import java.util.ArrayList;

/**
 * Created by jigsaw on 22/2/18.
 */

public class Adapter_DiseaseList extends
        RecyclerView.Adapter<Adapter_DiseaseList.DiseaseViewHolder> {

    private Context mContext;
    private ArrayList<Res_DiseaseModel.List> mList;

    public Adapter_DiseaseList(Context context,ArrayList<Res_DiseaseModel.List> list){
        mContext = context;
        mList = list;
    }


    @Override
    public DiseaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new DiseaseViewHolder(inflater,parent);
    }

    @Override
    public void onBindViewHolder(DiseaseViewHolder holder, final int position) {
        holder.mTextView.setText(mList.get(position).getDiseaseName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(Activity_MedicineList.newIntent(
                        mContext,mList.get(position).getMedicinesUsed()
                ));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class DiseaseViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextView;

        DiseaseViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_diseases, parent, false));

            mImageView = itemView.findViewById(R.id.list_item_disease_image);
            mTextView = itemView.findViewById(R.id.list_item_disease_name);
        }
    }


}
