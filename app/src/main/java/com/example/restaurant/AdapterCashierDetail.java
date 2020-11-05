package com.example.restaurant;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterCashierDetail extends RecyclerView.Adapter<AdapterCashierDetail.AdapterCashierDetailViewHolder> {
    private Context cContext;
    private List<Upload_Restaurant> cUpload;

    public AdapterCashierDetail(Context context, List<Upload_Restaurant> Upload) {
        cContext = context;
        cUpload = Upload;
    }

    public static class AdapterCashierDetailViewHolder extends RecyclerView.ViewHolder {
        public TextView cFoodQty;
        public TextView cFoodName;
        public TextView cFoodPrice;
        public TextView cFoodTotal;

        public AdapterCashierDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            cFoodQty = itemView.findViewById(R.id.Cash_Detail_Qty);
            cFoodName = itemView.findViewById(R.id.Cash_Detail_Name);
            //cFoodPrice = itemView.findViewById(R.id.Cash_Detail_Price);
            cFoodTotal = itemView.findViewById(R.id.Cash_Detail_PriceSum);
        }

    }

    @NonNull
    @Override
    public AdapterCashierDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(cContext).inflate(R.layout.cashier_detail_cardview, parent, false);
        return new AdapterCashierDetailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCashierDetailViewHolder holder, int position) {
        Upload_Restaurant curUpload = cUpload.get(position);
        holder.cFoodName.setText("" + curUpload.getmName());
        //holder.cFoodPrice.setText("@Rp " + curUpload.getmPrice()+"0");
        holder.cFoodQty.setText("" + curUpload.getmQty()+"x");
        holder.cFoodTotal.setText("Rp " + (curUpload.getmPrice() * curUpload.getmQty())+"0");
    }

    @Override
    public int getItemCount() {
        return cUpload.size();
    }

}
