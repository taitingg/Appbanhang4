package com.example.appbanhang.Activity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appbanhang.R;

public class KhuyenMaiActivity extends AppCompatActivity {

    TextView noidungkh;
    ImageView imagekh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_khuyen_mai);
        inirview();
        initdata();

    }

    private void initdata() {
        String nd = getIntent().getStringExtra("noidung");
        String url = getIntent().getStringExtra("url");
        noidungkh.setText(nd);
        Glide.with(this).load(url).into(imagekh);
    }

    private void inirview() {
        noidungkh = findViewById(R.id.noidungkh);
        imagekh = findViewById(R.id.imagekh);
    }
}