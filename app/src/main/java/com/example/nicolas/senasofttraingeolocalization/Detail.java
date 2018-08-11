package com.example.nicolas.senasofttraingeolocalization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity {
    static String lat, lon, name, description, image;
    TextView txtDesc, txtName;
    ImageView imv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imv = findViewById(R.id.detailImage);
        txtDesc = findViewById(R.id.detailDesc);
        txtName = findViewById(R.id.detailName);

        Picasso.get().load(image).into(imv);
        txtName.setText(name);
        txtDesc.setText(description);
        }

    public void go(View view) {
        Intent i = new Intent(Detail.this, MapsActivity2.class);
        startActivity(i);
    }
}
