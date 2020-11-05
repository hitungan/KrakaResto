package com.example.restaurant;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Adapterkoki extends RecyclerView.Adapter<Adapterkoki.kokiViewHolder> {

    private Context nContext;
    private List<Upload_Restaurant> nUpload;
    private OnItemClickListener nListener;
private String a;

    public Adapterkoki(Context context, List<Upload_Restaurant> uploads) {
        nContext = context;
        nUpload = uploads;
    }

    @NonNull
    @Override
    public kokiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(nContext).inflate(R.layout.activity_chef, parent, false);
        return new kokiViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull kokiViewHolder holder, int position) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");


        Upload_Restaurant uploadCurrent = nUpload.get(position);
        holder.kuantitas.setText("" + uploadCurrent.getmQty() + "x");
        holder.nama.setText("" + uploadCurrent.getmName());
        holder.nomor.setText("" + uploadCurrent.getmTableNum());

        Date date = new Date(uploadCurrent.getmTime());
        a= simpleDateFormat.format(date);

        holder.waktu.setText(a);



    }


    @Override
    public int getItemCount() {
        return nUpload.size();
    }

    public class kokiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView kuantitas;
        public TextView nomor;
        public TextView waktu;
        public TextView nama;

        @Override
        public void onClick(View v) {
            if (nListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    nListener.onItemClick(position);
                }
            }
        }


        public kokiViewHolder(View itemView) {
            super(itemView);

            kuantitas = itemView.findViewById(R.id.kokitotal);
            nomor = itemView.findViewById(R.id.kokinomormeja);
            waktu = itemView.findViewById(R.id.kokiwaktu);
            nama = itemView.findViewById(R.id.kokinamamakan);

            itemView.setOnClickListener(this);

        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        nListener = listener;

    }
}
