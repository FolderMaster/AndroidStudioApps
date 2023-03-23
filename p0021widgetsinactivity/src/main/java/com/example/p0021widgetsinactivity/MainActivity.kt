package com.example.p0021widgetsinactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

class MainActivity : AppCompatActivity() {
    private var mHelloTextView: TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelloTextView = findViewById(R.id.textView);
    }

    fun imageButton_onClick(view: View)
    {
        mHelloTextView?.setText("Hello!");
    }
}