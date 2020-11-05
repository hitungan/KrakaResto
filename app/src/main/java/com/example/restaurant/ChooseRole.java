package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseRole extends AppCompatActivity {

    private Button mCustomer;
    private Button mChef;
    private Button mCashier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);
        mCustomer = findViewById(R.id.btn_customer);
        mCustomer.setEnabled(true);
        mChef = findViewById(R.id.btn_chef);
        mChef.setEnabled(true);
        mCashier = findViewById(R.id.btn_cashier);
        mCashier.setEnabled(true);

        mCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomer.setEnabled(false);
                mCashier.setEnabled(false);
                mChef.setEnabled(false);
                if (Data.foodList.isEmpty()) {
                    Data.foodList.add(new Food("A1", "Ayam Goreng Rocky", "Rocky yang buat", 0, R.drawable.ayamgoreng, 20000));
                    Data.foodList.add(new Food("A2", "Indomie", "Ada telor dan kornet", 0, R.drawable.indomie, 10000));
                    Data.foodList.add(new Food("A3", "Sayur Asem", "Pakai keringat abang spesial", 0, R.drawable.sayurasem, 30000));
                    Data.foodList.add(new Food("A4", "Kwetiau 69", "Bumbu Rahasia", 0, R.drawable.kwetiaugoreng, 25000));
                    Data.foodList.add(new Food("A5", "Pizza", "Khas Italia buatan Mafia Chef", 0, R.drawable.pizza, 70000));

                    Data.drinkList.add(new Food("B1", "SPIRIT 6 rasa", "Nano Nano rasanya", 0, R.drawable.sprite, 10000));
                    Data.drinkList.add(new Food("B2", "Cucu Cula 8 rasa", "Pusing rasanya", 0, R.drawable.cocacola, 5000));
                    Data.drinkList.add(new Food("B3", "Aqua 9 rasa", "Kerad rasanya", 0, R.drawable.aqua, 8000));
                    Data.drinkList.add(new Food("B4", "Nü 10 rasa", "Asique rasanya", 0, R.drawable.niu, 7000));
                    Data.drinkList.add(new Food("B5", "Kopi Kopiko 11 rasa", "HUEHUE rasanya", 0, R.drawable.kopiko, 12000));

                    Intent intent = new Intent(ChooseRole.this, PickSeat.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ChooseRole.this, PickSeat.class);
                    startActivity(intent);
                }
            }
        });

        mChef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomer.setEnabled(false);
                mCashier.setEnabled(false);
                mChef.setEnabled(false);
                Intent intent = new Intent(ChooseRole.this, Chef.class);
                startActivity(intent);
            }
        });

        mCashier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomer.setEnabled(false);
                mCashier.setEnabled(false);
                mChef.setEnabled(false);
                Intent intent = new Intent(ChooseRole.this, PickTotalTable.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mCustomer = findViewById(R.id.btn_customer);
        mCustomer.setEnabled(true);
        mChef = findViewById(R.id.btn_chef);
        mChef.setEnabled(true);
        mCashier = findViewById(R.id.btn_cashier);
        mCashier.setEnabled(true);
    }
}
