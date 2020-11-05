package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PickTotalTable extends AppCompatActivity {

    private EditText mInputTotalTable;
    private Button mConfirmTable;
    String InputTotal;
    int x=0;
    private DatabaseReference mDatabaseRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_total_table);
        mInputTotalTable = findViewById(R.id.et_inputTotalTable);
        mConfirmTable = findViewById(R.id.btn_confirmTable);
        mConfirmTable.setEnabled(true);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("restaurant_info");

        mConfirmTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputTotal = mInputTotalTable.getText().toString().trim();
                if (!InputTotal.equals(""))
                    x = Integer.parseInt(InputTotal);
                if (InputTotal.equals("")) {
                    Toast.makeText(PickTotalTable.this, "Please input a number", Toast.LENGTH_SHORT).show();
                } else if (x > 1000) {
                    Toast.makeText(PickTotalTable.this, "Please input number under 1000", Toast.LENGTH_SHORT).show();

                } else {
                    Data.tableList.clear();
                    int foo = Integer.parseInt(mInputTotalTable.getText().toString());
                    for (int i = 0; i < foo; i++) {
                        Data.tableList.add(new Table("" + (i + 1)));
                    }
                    Upload_Table upload_table = new Upload_Table(mInputTotalTable.getText().toString());
                    String uploadId = "MaxTable";
                    mDatabaseRef.child(uploadId).setValue(upload_table);
//                Toast.makeText(PickTotalTable.this,uploadId,Toast.LENGTH_LONG).show();
                    mConfirmTable.setEnabled(false);
                    Intent intent = new Intent(PickTotalTable.this, Cashier.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mConfirmTable = findViewById(R.id.btn_confirmTable);
        mConfirmTable.setEnabled(true);
    }
}