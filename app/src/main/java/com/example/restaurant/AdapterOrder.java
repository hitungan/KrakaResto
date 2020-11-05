package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.AdapterOrderViewHolder> {
    private Context cContext;
    private List<Food> cUpload;
    private String a = "";

    public AdapterOrder(Context context, List<Food> Upload) {
        cContext = context;
        cUpload = Upload;
    }

    public static class AdapterOrderViewHolder extends RecyclerView.ViewHolder {
        public TextView cFoodQty;
        public TextView cFoodName;
        public TextView cFoodPrice;
        public TextView cFoodTotal;

        public AdapterOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            cFoodQty = itemView.findViewById(R.id.Cash_Detail_Qty);
            cFoodName = itemView.findViewById(R.id.Cash_Detail_Name);
            //cFoodPrice = itemView.findViewById(R.id.Cash_Detail_Price);
            cFoodTotal = itemView.findViewById(R.id.Cash_Detail_PriceSum);
        }

    }

    @NonNull
    @Override
    public AdapterOrder.AdapterOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(cContext).inflate(R.layout.cashier_detail_cardview, parent, false);
        return new AdapterOrder.AdapterOrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOrder.AdapterOrderViewHolder holder, int position) {
        Food curUpload = cUpload.get(position);
        holder.cFoodName.setText("" + curUpload.getFoodName());
        //holder.cFoodPrice.setText("@Rp " + curUpload.getFoodPrice()+"0");
        holder.cFoodQty.setText(curUpload.getFoodQty()+"x");
        a = String.format("%.2f",(curUpload.getFoodPrice() * curUpload.getFoodQty()));
        holder.cFoodTotal.setText("Rp "+ a);
    }

    @Override
    public int getItemCount() {
        return cUpload.size();
    }

}
