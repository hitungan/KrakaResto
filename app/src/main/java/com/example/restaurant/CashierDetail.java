package com.example.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CashierDetail extends AppCompatActivity {
    private RecyclerView cRecyclerView;
    private AdapterCashierDetail cAdapter;
    private RecyclerView.LayoutManager cLayoutManager;
    public static TextView TotPrice;
    public Button ConfirmPay;
    private DatabaseReference cDatabaseRef;
    private List<Upload_Restaurant> cUpload;
    private String path;
    private ValueEventListener cDBListener;
    //private int x = 0;
    private String a = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_detail);
        Data.listenerState=false;
        TotPrice = findViewById(R.id.Total_Cash_Detail);
        ConfirmPay = findViewById(R.id.btn_confirm_pay);
        ConfirmPay.setEnabled(true);
        cUpload = new ArrayList<>();
        cAdapter = new AdapterCashierDetail(CashierDetail.this, cUpload);
        cLayoutManager = new LinearLayoutManager(this);

        cRecyclerView = findViewById(R.id.rv_cashier_detail);
        cRecyclerView.setHasFixedSize(true);
        cRecyclerView.setLayoutManager(cLayoutManager);
        cRecyclerView.setAdapter(cAdapter);

        path = "Cashier/Meja_" + (Data.tablePosition + 1);

        ConfirmPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cUpload.clear();
                Data.listenerState=true;
                ConfirmPay.setEnabled(false);
                if (cUpload.size() == 0) {

                } else {
                    Upload_Restaurant selectedItem = cUpload.get((0));
                    String selectedKey = selectedItem.getKey();
                    cDatabaseRef.child(selectedKey).getParent().removeValue();
                }
                finish();
            }
        });
        cDatabaseRef = FirebaseDatabase.getInstance().getReference(path);

        cDBListener = cDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cUpload.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload_Restaurant upload = postSnapshot.getValue(Upload_Restaurant.class);
                    upload.setKey(postSnapshot.getKey());
                    cUpload.add(upload);
                    Data.totalharga += (upload.getmQty() * upload.getmPrice());
                }
                TotPrice.setText("Rp " + Data.totalharga+"0");
                cAdapter.notifyDataSetChanged();
                Data.totalharga = 0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CashierDetail.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ConfirmPay = findViewById(R.id.btn_confirm_pay);
        ConfirmPay.setEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cDatabaseRef.removeEventListener(cDBListener);
    }

}