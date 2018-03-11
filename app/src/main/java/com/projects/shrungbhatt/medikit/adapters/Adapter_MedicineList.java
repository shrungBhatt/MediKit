package com.projects.shrungbhatt.medikit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projects.shrungbhatt.medikit.activities.Activity_MedicineDetail;
import com.projects.shrungbhatt.medikit.R;

import java.util.List;

/**
 * Created by jigsaw on 22/2/18.
 */

public class Adapter_MedicineList extends
        RecyclerView.Adapter<Adapter_MedicineList.MedicineViewHolder> {

    private Context mContext;
    private List<String> mMedicines;

    public Adapter_MedicineList(Context context,List<String> medicines){
        mContext = context;
        mMedicines = medicines;
    }

    @Override
    public MedicineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new MedicineViewHolder(inflater,parent);
    }

    @Override
    public void onBindViewHolder(MedicineViewHolder holder, final int position) {

        holder.mNameTextView.setText(mMedicines.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(Activity_MedicineDetail.newIntent(mContext,
                        mMedicines.get(position)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMedicines.size();
    }

    class MedicineViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTextView;

        MedicineViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_medicine, parent, false));

            mNameTextView = itemView.findViewById(R.id.list_item_medicine_name);
        }

    }
}
