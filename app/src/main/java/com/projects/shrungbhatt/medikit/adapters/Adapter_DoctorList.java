package com.projects.shrungbhatt.medikit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.shrungbhatt.medikit.R;
import com.projects.shrungbhatt.medikit.activities.Activity_BookAppointment;
import com.projects.shrungbhatt.medikit.activities.Activity_DoctorProfileDetails;
import com.projects.shrungbhatt.medikit.models.Res_DoctorDetails;

import java.util.ArrayList;

/**
 * Created by jigsaw on 16/3/18.
 */

public class Adapter_DoctorList extends RecyclerView.Adapter<Adapter_DoctorList.DoctorViewHolder>{


    private Context mContext;
    private ArrayList<Res_DoctorDetails.List> mDoctorsList;

    public Adapter_DoctorList(Context context,ArrayList<Res_DoctorDetails.List> doctorsList){

        mContext = context;
        mDoctorsList = doctorsList;

    }



    @Override
    public DoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new DoctorViewHolder(inflater,parent);
    }

    @Override
    public void onBindViewHolder(DoctorViewHolder holder, final int position) {

        holder.mDoctorName.setText(mDoctorsList.get(position).getDoctorName());
        holder.mDoctorDescription.setText(mDoctorsList.get(position).getDoctorSkills());
        String speciality = mDoctorsList.get(position).getDoctorSpeciality() + " specialist";
        holder.mDoctorSpeciality.setText(speciality);

        holder.mBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(Activity_BookAppointment.newIntent(
                        mContext, mDoctorsList.get(position).getHospitalName(),
                        mDoctorsList.get(position).getDoctorName()));
            }
        });

        holder.mDoctorProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(Activity_DoctorProfileDetails.newIntent(mContext,mDoctorsList.get(position).getDoctorName()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDoctorsList.size();
    }

    class DoctorViewHolder extends RecyclerView.ViewHolder{

        private TextView mDoctorName;
        private Button mBookAppointment;
        private TextView mDoctorSpeciality;
        private TextView mDoctorDescription;
        private ImageButton mDoctorProfile;

        DoctorViewHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_doctor,parent,false));

            mDoctorName = itemView.findViewById(R.id.list_item_doctor_details_doctor_name);
            mBookAppointment = itemView.findViewById(R.id.list_item_doctor_book_appointment);
            mDoctorSpeciality = itemView.findViewById(R.id.list_item_doctor_speciality);
            mDoctorDescription = itemView.findViewById(R.id.list_item_doctor_description);
            mDoctorProfile = itemView.findViewById(R.id.doctor_profile_image);
        }

    }
}
