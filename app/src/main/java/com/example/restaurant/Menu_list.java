package com.example.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Menu_list extends AppCompatActivity {
    private Button mHistory;
    private Button mOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(bottomNavListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FoodFragment()).commit();

        mHistory = findViewById(R.id.btn_history);
        mHistory.setEnabled(true);
        mOrder = findViewById(R.id.btn_order);
        mOrder.setEnabled(true);
        mHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.totalharga = 0;
                mHistory.setEnabled(false);
                mOrder.setEnabled(false);
                Intent intent = new Intent(Menu_list.this, History.class);
                startActivity(intent);
            }
        });

        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.totalharga = 0;
                mHistory.setEnabled(false);
                mOrder.setEnabled(false);
                Intent intent = new Intent(Menu_list.this, Order.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mHistory = findViewById(R.id.btn_history);
        mHistory.setEnabled(true);
        mOrder = findViewById(R.id.btn_order);
        mOrder.setEnabled(true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_food:
                            selectFragment = new FoodFragment();
                            break;
                        case R.id.nav_drink:
                            selectFragment = new DrinkFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectFragment).commit();
                    return true;
                }
            };
}
