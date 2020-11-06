package com.vladkor.mybirddatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class my_item_layout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_item_activity);
        Bundle arguments = getIntent().getExtras();

        TextView titleTV = findViewById(R.id.titleTextItem);
        TextView descrTV = findViewById(R.id.descriptionTextItem);
        TextView ldescrTV = findViewById(R.id.LDescriptionTextItem);

        String title = arguments.get("TITLE").toString();
        String discript = arguments.get("DESCRIPTION").toString();
        String l_discript = arguments.get("LARGE_DESCRIPTION").toString();

        titleTV.setText(title);
        descrTV.setText(discript);
        ldescrTV.setText(l_discript);
    }
}
