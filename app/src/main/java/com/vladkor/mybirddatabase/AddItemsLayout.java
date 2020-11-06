package com.vladkor.mybirddatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vladkor.mybirddatabase.Item.Item;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class AddItemsLayout extends AppCompatActivity {
    TextView title, descr, l_descr;
    ImageView img;
    Bitmap bm;
    String KEY = "Bird";


    ImageView uploadImg;
    private static final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;

    FirebaseDatabase dataBase;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items_layout);
        title = findViewById(R.id.titlePlain);
        descr = findViewById(R.id.descriptPlain);
        l_descr = findViewById(R.id.ldescriptPlain);
        img = findViewById(R.id.imagePreview);
        uploadImg = findViewById(R.id.uploadImageBtn);
        ImageView fab = findViewById(R.id.addItemBtn);

        dataBase = FirebaseDatabase.getInstance();
        myRef = dataBase.getReference(KEY);
        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartUpload();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bm == null){
                    Toast.makeText(v.getContext(), "Отсутсвует фотография", Toast.LENGTH_LONG).show();
                    return;
                }
                if(title.getText() != "" && descr.getText() != "" && l_descr.getText() != ""){
                    AddItemToDB(title.getText().toString(), descr.getText().toString(), l_descr.getText().toString(), bm);
                }
            }
        });

    }



    public void AddItemToDB(String title, String descr, String lDescript, Bitmap bm){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(KEY);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] buffer = stream.toByteArray();
        String buffer_str = buffer.toString();

        Item myItem = new Item(title, descr, lDescript, buffer_str);

        myRef.push().setValue(myItem);


        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


    public void StartUpload(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select image"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            filePath = data.getData();
            try{
                bm = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                img.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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