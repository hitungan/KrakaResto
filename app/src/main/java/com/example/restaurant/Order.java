package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Order extends AppCompatActivity {

    private RecyclerView cRecyclerView;
    private AdapterOrder cAdapter;
    private RecyclerView.LayoutManager cLayoutManager;
    public static TextView TotPrice;

    int count = 0;
    int[] x = new int[100];
    int count2 = 0;
    int[] y = new int[100];

    private boolean isEmpty = true;
    //private String OrderList = "";

    private Button mFinalConfirm;
    private Button mBackButton;

    private ArrayList<Food> foodTemp = new ArrayList<>();

    private DatabaseReference mDatabaseRef;
    private DatabaseReference mDatabaseRef2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        TotPrice = findViewById(R.id.Total_Order);
        mFinalConfirm = findViewById(R.id.btn_finalConfirm);
        mFinalConfirm.setEnabled(true);

        mBackButton = findViewById(R.id.btn_back);
        mBackButton.setEnabled(true);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("orderQueue");

        mDatabaseRef2 = FirebaseDatabase.getInstance().getReference("Cashier/Meja_" + Data.tableNumber);


        for (int i = 0; i < Data.foodList.size(); i++) {

            if (Data.foodList.get(i).getFoodQty() > 0) {
                //ambil food masukin ke array food
                foodTemp.add(Data.foodList.get(i));
                Data.totalharga += (Data.foodList.get(i).getFoodPrice() * Data.foodList.get(i).getFoodQty());
                isEmpty = false;
                x[count] = i;
                count++;
                //OrderList += Data.foodList.get(i).getFoodQty() + " " + Data.foodList.get(i).getFoodName() + " " + (Data.foodList.get(i).getFoodQty())*(Data.foodList.get(i).getFoodPrice()) + "\n";
            }
        }
        for (int i = 0; i < Data.drinkList.size(); i++) {

            if (Data.drinkList.get(i).getFoodQty() > 0) {
                //ambil food masukin ke array food
                foodTemp.add(Data.drinkList.get(i));
                Data.totalharga += (Data.drinkList.get(i).getFoodPrice() * Data.drinkList.get(i).getFoodQty());
                isEmpty = false;
                y[count2] = i;
                count2++;
                //OrderList += Data.foodList.get(i).getFoodQty() + " " + Data.foodList.get(i).getFoodName() + " " + (Data.foodList.get(i).getFoodQty())*(Data.foodList.get(i).getFoodPrice()) + "\n";
            }
        }

        cAdapter = new AdapterOrder(Order.this, foodTemp);

        cLayoutManager = new LinearLayoutManager(this);

        cRecyclerView = findViewById(R.id.rv_order);
        cRecyclerView.setHasFixedSize(true);
        cRecyclerView.setLayoutManager(cLayoutManager);
        cRecyclerView.setAdapter(cAdapter);

        TotPrice.setText("Rp " + Data.totalharga+"0");

        if (isEmpty) {
            Toast.makeText(this, "No Order!", Toast.LENGTH_SHORT).show();
        } else {
            //

        }

        mFinalConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFinalConfirm.setEnabled(false);
                mBackButton.setEnabled(false);
                uploadData();
                uploadData2();
                cleanData();
                foodTemp.clear();
                finish();
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFinalConfirm.setEnabled(false);
                mBackButton.setEnabled(false);
                finish();
            }
        });

    }

    private void uploadData() {
        for (int i = 0; i < count; i++) {
            Upload_Restaurant upload_restaurant = new Upload_Restaurant(Data.tableNumber,
                    Data.foodList.get(x[i]).getFoodName(),
                    Data.foodList.get(x[i]).getFoodQty(),
                    Data.foodList.get(x[i]).getFoodPrice(),
                    System.currentTimeMillis());
            String uploadId = mDatabaseRef.push().getKey();
            mDatabaseRef.child(uploadId).setValue(upload_restaurant);
        }
        for (int i = 0; i < count2; i++) {
            Upload_Restaurant upload_restaurant = new Upload_Restaurant(Data.tableNumber,
                    Data.drinkList.get(y[i]).getFoodName(),
                    Data.drinkList.get(y[i]).getFoodQty(),
                    Data.drinkList.get(y[i]).getFoodPrice(),
                    System.currentTimeMillis());
            String uploadId = mDatabaseRef.push().getKey();
            mDatabaseRef.child(uploadId).setValue(upload_restaurant);
        }
    }

    private void uploadData2() {
        for (int i = 0; i < count; i++) {
            Upload_Restaurant upload_restaurant = new Upload_Restaurant(Data.tableNumber,
                    Data.foodList.get(x[i]).getFoodName(),
                    Data.foodList.get(x[i]).getFoodQty(),
                    Data.foodList.get(x[i]).getFoodPrice(),
                    System.currentTimeMillis());
            String uploadId = mDatabaseRef.push().getKey();
            mDatabaseRef2.child(uploadId).setValue(upload_restaurant);
        }
        for (int i = 0; i < count2; i++) {
            Upload_Restaurant upload_restaurant = new Upload_Restaurant(Data.tableNumber,
                    Data.drinkList.get(y[i]).getFoodName(),
                    Data.drinkList.get(y[i]).getFoodQty(),
                    Data.drinkList.get(y[i]).getFoodPrice(),
                    System.currentTimeMillis());
            String uploadId = mDatabaseRef.push().getKey();
            mDatabaseRef2.child(uploadId).setValue(upload_restaurant);
        }
    }

    private void cleanData() {
        for (int i = 0; i < count; i++) {
            Data.foodList.get(x[i]).setFoodQty(0);
        }
        for (int i = 0; i < count2; i++) {
            Data.drinkList.get(y[i]).setFoodQty(0);
        }
        count = 0;
        count2 = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFinalConfirm = findViewById(R.id.btn_finalConfirm);
        mFinalConfirm.setEnabled(true);
        mBackButton = findViewById(R.id.btn_back);
        mBackButton.setEnabled(true);
    }
}
