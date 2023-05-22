package com.example.p0042animation

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        val personImageView: ImageView = findViewById(R.id.person);
        val movePersonAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.move);
        personImageView.startAnimation(movePersonAnimation);
    }
}