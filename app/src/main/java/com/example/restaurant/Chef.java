package com.example.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Chef extends AppCompatActivity{
    private RecyclerView nRecyclerView;
    private Adapterkoki nAdapter;


    private DatabaseReference nDatabaseRef;
    private ValueEventListener nDBListener;

    private List<Upload_Restaurant> nUpload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chef_recyclerview);

        nRecyclerView = findViewById(R.id.rv_chef);
        nRecyclerView.setHasFixedSize(true);
        nRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        nUpload = new ArrayList<>();

        nAdapter = new Adapterkoki(Chef.this,nUpload);
        nRecyclerView.setAdapter(nAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //Toast.makeText(Chef.this,"delete",Toast.LENGTH_SHORT).show();

                Upload_Restaurant selectedItem = nUpload.get(viewHolder.getAdapterPosition());
                String selectedKey = selectedItem.getKey();
                nDatabaseRef.child(selectedKey).removeValue();
            }
        }).attachToRecyclerView(nRecyclerView);


        nDatabaseRef = FirebaseDatabase.getInstance().getReference("orderQueue");

        nDBListener = nDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nUpload.clear();

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Upload_Restaurant upload = postSnapshot.getValue(Upload_Restaurant.class);
                    upload.setKey(postSnapshot.getKey());
                    nUpload.add(upload);
                }

                nAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Chef.this, databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nDatabaseRef.removeEventListener(nDBListener);
    }

}
