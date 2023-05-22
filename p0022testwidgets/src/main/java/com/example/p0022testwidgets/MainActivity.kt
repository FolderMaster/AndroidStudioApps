package com.example.p0022testwidgets;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.*;

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText;

    private lateinit var button: Button;

    private lateinit var imageButton: ImageButton;

    private lateinit var  checkBox: CheckBox;

    private lateinit var  seekBar: SeekBar;

    private lateinit var textView: TextView;

    private lateinit var calendarView: CalendarView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        imageButton = findViewById(R.id.imageButton);
        checkBox = findViewById(R.id.checkBox);
        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        calendarView = findViewById(R.id.calendarView);
    }

    fun button_onClick(view: View) {
        val date: Date = Date(calendarView.date);
        textView.setText("${date.date}\t${date.month + 1}\t${date.year + 1900}");
    }

    fun imageButton_onClick(view: View) {
        textView.setText("${editText.text}\t${checkBox.isChecked}\t${seekBar.progress}");
    }
}