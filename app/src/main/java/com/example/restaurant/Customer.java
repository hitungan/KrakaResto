package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Customer extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button mHistory;
    private Button mOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_recyclerview);

        mHistory = findViewById(R.id.btn_history);
        mOrder = findViewById(R.id.btn_order);

        buildRecyclerView();

        mHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.totalharga =0;
                Intent intent = new Intent(Customer.this, History.class);
                startActivity(intent);
            }
        });

        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.totalharga =0;
                Intent intent = new Intent(Customer.this, Order.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    //    @Override
//    public void onBackPressed() {
//    // super.onBackPressed();
//    // Not calling **super**, disables back button in current screen.
//    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.rv_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Adapter(Data.foodList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //adapter.notifyItemChanged(position);
            }

            @Override
            public void onPlusClick(int position) {
                int x = Data.foodList.get(position).getFoodQty();
                Data.foodList.get(position).changeQuantity(x + 1);
                //adapter.notifyItemChanged(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onMinClick(int position) {
                int x = Data.foodList.get(position).getFoodQty();
                if (x != 0)
                    Data.foodList.get(position).changeQuantity(x - 1);
                //adapter.notifyItemChanged(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

}

