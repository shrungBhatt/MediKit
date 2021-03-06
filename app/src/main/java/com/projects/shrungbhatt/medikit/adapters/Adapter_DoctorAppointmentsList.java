package com.projects.shrungbhatt.medikit.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projects.shrungbhatt.medikit.R;
import com.projects.shrungbhatt.medikit.listeners.UpdateAppointmentStatus;
import com.projects.shrungbhatt.medikit.models.Res_AppointmentsList;
import com.projects.shrungbhatt.medikit.util.Const;
import com.projects.shrungbhatt.medikit.util.HashMapGenerator;

import java.util.ArrayList;


/**
 * Created by jigsaw on 17/3/18.
 */

public class Adapter_DoctorAppointmentsList extends
        RecyclerView.Adapter<Adapter_DoctorAppointmentsList.DoctorAppointmentViewHolder>{


    private Context mContext;
    private ArrayList<Res_AppointmentsList.List> mAppointments;
    private UpdateAppointmentStatus mUpdateAppointmentStatus;

    public Adapter_DoctorAppointmentsList(Context context,
                                          ArrayList<Res_AppointmentsList.List> appointments,
                                          UpdateAppointmentStatus updateAppointmentStatus){
        mContext = context;
        mAppointments = appointments;
        mUpdateAppointmentStatus = updateAppointmentStatus;
    }

    @Override
    public DoctorAppointmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new DoctorAppointmentViewHolder(inflater,parent);
    }

    @Override
    public void onBindViewHolder(DoctorAppointmentViewHolder holder, final int position) {

        appointmentStatusDisplay(holder,position,mContext);
        String userAppointment = "Appointment with " + mAppointments.get(position).getUserName();
        holder.mListItemAppointmentDoctorName.setText(userAppointment);

        holder.mListItemAppointmentDate.setText(mAppointments.get(position).getAppointmentDate());

        String time = mAppointments.get(position).getAppointmentFrom()+ " to " +
                mAppointments.get(position).getAppointmentTo();
        holder.mListItemAppointmentTime.setText(time);

        holder.mListItemAppointmentDescription.setText(mAppointments.get(position).getDescription());

        holder.mListItemConfirmAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUpdateAppointmentStatus.updateAppointmentStatus(mAppointments.get(position).getId(),
                        Const.Confirmed);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mAppointments.size();
    }

    private void appointmentStatusDisplay(DoctorAppointmentViewHolder holder, int position, Context context) {

        Drawable icon = ContextCompat.getDrawable(context, R.drawable.token_textview_background).mutate();

        int statusId = Integer.valueOf(mAppointments.get(position).getAppointmentStatusId());

        switch (statusId) {
            case Const.Pending:
                holder.mListItemConfirmAppointment.setVisibility(View.VISIBLE);
                setData(holder,context.getResources().getColor(R.color.pending),
                        context.getResources().getColor(R.color.pending),
                                HashMapGenerator.getStatusFromHashMap(statusId),icon);
                break;
            case Const.Cancelled:
                holder.mListItemConfirmAppointment.setVisibility(View.GONE);
                setData(holder,context.getResources().getColor(R.color.rejected),
                        context.getResources().getColor(R.color.rejected),
                        HashMapGenerator.getStatusFromHashMap(statusId),icon);
                break;
            case Const.Confirmed:
                holder.mListItemConfirmAppointment.setVisibility(View.GONE);
                setData(holder,context.getResources().getColor(R.color.approved),
                        context.getResources().getColor(R.color.approved),
                        HashMapGenerator.getStatusFromHashMap(statusId),icon);
                break;
        }


    }

    private void setData(DoctorAppointmentViewHolder holder, int colorBg, int colorText, String stringId, Drawable icon) {
        icon.setColorFilter(new PorterDuffColorFilter(colorBg, PorterDuff.Mode.SRC_IN));
        holder.mListItemAppointmentStatusBackground.setBackground(icon);
        holder.mListItemAppointmentStatusBackground.setVisibility(View.VISIBLE);
        holder.mListItemAppointmentStatus.setText(stringId);
        holder.mListItemAppointmentStatus.setTextColor(colorText);
    }

    class DoctorAppointmentViewHolder extends RecyclerView.ViewHolder {

        private TextView mListItemAppointmentDoctorName;
        private Button mListItemConfirmAppointment;
        private TextView mListItemAppointmentDate;
        private TextView mListItemAppointmentTime;
        private TextView mListItemAppointmentDescription;
        private TextView mListItemAppointmentStatus;
        private LinearLayout mListItemAppointmentStatusBackground;

        DoctorAppointmentViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_doctor_appointments, parent, false));

            mListItemAppointmentDoctorName = itemView.
                    findViewById(R.id.list_item_doctor_appointment_user_name);

            mListItemConfirmAppointment = itemView.
                    findViewById(R.id.list_item_doctor_appointment_confirm_appointment);

            mListItemAppointmentDate = itemView.
                    findViewById(R.id.list_item_doctor_appointment_date);

            mListItemAppointmentTime = itemView.
                    findViewById(R.id.list_item_doctor_appointment_time);

            mListItemAppointmentDescription = itemView.
                    findViewById(R.id.list_item_doctor_appointment_description);

            mListItemAppointmentStatus  = itemView.
                    findViewById(R.id.list_item_doctor_appointment_status);

            mListItemAppointmentStatusBackground = itemView.
                    findViewById(R.id.list_item_doctor_appointment_status_background);

        }

    }
}
