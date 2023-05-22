package com.example.p0041animationtest

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        val sunImageView: ImageView = findViewById(R.id.sun);
        val sunRiseAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.rise);
        sunImageView.startAnimation(sunRiseAnimation);
    }
}