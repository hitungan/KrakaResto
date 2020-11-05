package com.example.restaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCashier extends RecyclerView.Adapter<AdapterCashier.ViewHolder> {

    private ArrayList<Table> mTableList;
    private OnItemClickListener cListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        cListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mNomorBesar;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mNomorBesar = itemView.findViewById(R.id.nomorBesar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public AdapterCashier(ArrayList<Table> tableList) {
        mTableList = tableList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cashier, parent, false);
        ViewHolder vh = new ViewHolder(v, cListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Table currentTable = mTableList.get(position);
        holder.mNomorBesar.setText(currentTable.getTableId().trim());
    }

    @Override
    public int getItemCount() {
        return mTableList.size();
    }
}
