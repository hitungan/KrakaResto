package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.widget.Toast;

public class Cashier extends AppCompatActivity {

    private RecyclerView mRecView;
    private AdapterCashier mAdapterCashier;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cashier_recyclerview);
        Data.listenerState=true;
        mRecView = findViewById(R.id.rv_cashier);
        mRecView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 4);
        mAdapterCashier = new AdapterCashier(Data.tableList);
        mRecView.setLayoutManager(mLayoutManager);
        mRecView.setAdapter(mAdapterCashier);

        mAdapterCashier.setOnItemClickListener(new AdapterCashier.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                if(Data.listenerState)
                {
                    Data.listenerState=false;
                    Data.tablePosition = position;
                    Data.totalharga = 0;

                    Intent intent = new Intent(Cashier.this, CashierDetail.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Data.listenerState=true;
    }
}
