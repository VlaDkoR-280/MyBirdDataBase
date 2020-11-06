package com.vladkor.mybirddatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vladkor.mybirddatabase.Item.Item;

public class AddItemsLayout extends AppCompatActivity {
    TextView title, descr, l_descr;
    ImageView img;
    String KEY = "Bird";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items_layout);
        title = findViewById(R.id.titlePlain);
        descr = findViewById(R.id.descriptPlain);
        l_descr = findViewById(R.id.ldescriptPlain);
        img = findViewById(R.id.imagePreview);
        ImageView fab = findViewById(R.id.addItemBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getText() != "" && descr.getText() != "" && l_descr.getText() != ""){
                    AddItemToDB(title.getText().toString(), descr.getText().toString(), l_descr.getText().toString());
                }
            }
        });

    }



    public void AddItemToDB(String title, String descr, String lDescript){
        Item myItem = new Item(title, descr, lDescript);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(KEY);

        myRef.push().setValue(myItem);

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

//    public void checkPermission(){
////        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED &&
////                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
////
////            ActivityCompat.requestPermissions(this,
////                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, TAKE_PERM_REQUEST_CODE);
////        }else if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
////            ActivityCompat.requestPermissions(this,
////                    new String[]{Manifest.permission.CAMERA}, TAKE_PERM_REQUEST_CODE);
////
////        }else{
////            dispatchTakePictureIntent();
////        }
//    }
}