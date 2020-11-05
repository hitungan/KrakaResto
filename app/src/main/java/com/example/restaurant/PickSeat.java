package com.example.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PickSeat extends AppCompatActivity {

    private Button mConfirm;
    private EditText mInputSeatNumber;
    private String getTextTotalTable;

    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_seat);

        mConfirm = findViewById(R.id.btn_confirm);
        mConfirm.setEnabled(true);
        mInputSeatNumber = findViewById(R.id.et_inputTableNumber);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("restaurant_info");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload_Table upload_table = postSnapshot.getValue(Upload_Table.class);
                    upload_table.setmKey(postSnapshot.getKey());
                    getTextTotalTable = upload_table.getmTotalTable();
//                    Toast.makeText(PickSeat.this, getTextTotalTable, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PickSeat.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInputSeatNumber.getText().toString().isEmpty()) {
                    Toast.makeText(PickSeat.this, "Please input a number", Toast.LENGTH_SHORT).show();
                } else {
                    int getTotalTable = Integer.parseInt(getTextTotalTable);
                    Data.maxTable = getTotalTable;
                    int inputSeatNum = Integer.parseInt(mInputSeatNumber.getText().toString());
                    if (getTotalTable == 0) {
                        Toast.makeText(PickSeat.this, "Ask the cashier to do the job!", Toast.LENGTH_SHORT).show();
                    }
                    if (inputSeatNum <= 0 || inputSeatNum > getTotalTable) {
                        Toast.makeText(PickSeat.this, "Input only 1 to " + getTotalTable, Toast.LENGTH_SHORT).show();
                    } else {
                        Data.tableNumber = "" + mInputSeatNumber.getText().toString().trim();
                        mConfirm.setEnabled(false);
                        Intent intent = new Intent(PickSeat.this, Menu_list.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mConfirm = findViewById(R.id.btn_confirm);
        mConfirm.setEnabled(true);
    }
}
