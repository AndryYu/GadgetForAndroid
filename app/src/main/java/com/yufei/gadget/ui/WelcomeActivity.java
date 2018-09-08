package com.yufei.gadget.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yufei.gadget.R;


public class WelcomeActivity extends AppCompatActivity {

    private ImageView ivGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ivGif = findViewById(R.id.iv_splash_gif);
        Glide.with(this).load(R.drawable.timg).into(ivGif);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                WelcomeActivity.this.finish();
            }
        }, 3000);
    }
}
