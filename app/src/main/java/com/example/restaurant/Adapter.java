package com.example.restaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Food> mFoodList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onPlusClick(int position);
        void onMinClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mFoodName;
        public TextView mFoodDesc;
        public TextView mFoodQty;
        public TextView mFoodPrice;
        public ImageView mFoodImg;
        public ImageButton Add_Qty;
        public ImageButton Sub_Qty;
        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mFoodImg = itemView.findViewById(R.id.iv_foodImg);
            mFoodName = itemView.findViewById(R.id.tv_foodName);
            mFoodDesc = itemView.findViewById(R.id.tv_foodDesc);
            mFoodQty = itemView.findViewById(R.id.tv_foodQty);
            mFoodPrice = itemView.findViewById(R.id.tv_foodPrice);
            Add_Qty = itemView.findViewById(R.id.btn_add);
            Sub_Qty = itemView.findViewById(R.id.btn_sub);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            Add_Qty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onPlusClick(position);
                        }
                    }
                }
            });
            Sub_Qty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onMinClick(position);
                        }
                    }
                }
            });
        }

    }

    public Adapter(ArrayList<Food> foodList) {
        mFoodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_customer, parent, false);
        ViewHolder vh = new ViewHolder(v, mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food currentData = mFoodList.get(position);
        holder.mFoodImg.setImageResource(currentData.getFoodImg());
        holder.mFoodName.setText(currentData.getFoodName());
        holder.mFoodDesc.setText(currentData.getFoodDesc());
        holder.mFoodQty.setText("" + currentData.getFoodQty());
        holder.mFoodPrice.setText("Rp " + currentData.getFoodPrice()+"0");
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }


}
