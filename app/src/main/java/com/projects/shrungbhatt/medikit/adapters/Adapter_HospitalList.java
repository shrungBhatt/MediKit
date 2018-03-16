package com.projects.shrungbhatt.medikit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projects.shrungbhatt.medikit.R;
import com.projects.shrungbhatt.medikit.activities.Activity_HospitalSpeciality;
import com.projects.shrungbhatt.medikit.models.Res_HospitalList;

import java.util.ArrayList;

/**
 * Created by jigsaw on 12/3/18.
 */

public class Adapter_HospitalList extends RecyclerView.Adapter<Adapter_HospitalList.HospitalViewHolder> {


    private Context mContext;
    private ArrayList<Res_HospitalList.List> mHospitalLists;

    public Adapter_HospitalList(Context context, ArrayList<Res_HospitalList.List> arrayList) {
        mContext = context;
        mHospitalLists = arrayList;
    }

    @Override
    public HospitalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new HospitalViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(HospitalViewHolder holder, final int position) {

        holder.mHospitalName.setText(mHospitalLists.get(position).getHospitalName().trim());
        holder.mHospitalAddress.setText(mHospitalLists.get(position)
                .getHospitalAddress().trim());
        holder.mHospitalContact.setText(mHospitalLists.get(position).getHospitalContact());
        String rating = mContext.getString(R.string.star) + " " + mHospitalLists.get(position)
                .getHospitalRating();
        holder.mHospitalRating.setText(rating);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(Activity_HospitalSpeciality.newIntent(mContext,
                        mHospitalLists,position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mHospitalLists.size();
    }

    class HospitalViewHolder extends RecyclerView.ViewHolder {

        private TextView mHospitalName;
        private TextView mHospitalAddress;
        private TextView mHospitalContact;
        private TextView mHospitalRating;

        HospitalViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_hospital, parent, false));

            mHospitalName = itemView.findViewById(R.id.list_item_hospital_hospital_name);
            mHospitalAddress = itemView.findViewById(R.id.list_item_hospital_address);
            mHospitalContact = itemView.findViewById(R.id.list_item_hospital_contact);
            mHospitalRating = itemView.findViewById(R.id.list_item_hospital_hospital_rating);


        }


    }

}
