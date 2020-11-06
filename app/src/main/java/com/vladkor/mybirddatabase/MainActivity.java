package com.vladkor.mybirddatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vladkor.mybirddatabase.Item.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> items = new ArrayList<>();
    RecyclerView rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc = (RecyclerView) findViewById(R.id.recycler);
        FloatingActionButton fab = findViewById(R.id.fabMA);
        rc.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rc.setLayoutManager(llm);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddItemsLayout.class);
                startActivity(i);
            }
        });
        getDataBase();
    }

    void getDataBase(){
        String KEY = "Bird";
        FirebaseDatabase dataBase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = dataBase.getReference(KEY);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(items.size() > 0) items.clear();
                try{
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        Item item = ds.getValue(Item.class);
                        items.add(item);
                    }
                }catch (Exception e){
                    Log.e("DataSnapshotERROR", "Error", e);
                }

                rc.setAdapter(new MyRecycler(items));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("testing","Error: ", databaseError.toException());
            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.fab:{
//                Intent i = new Intent(this, AddItemsLayout.class);
//                startActivity(i);
//            }
//        }
//    }
}