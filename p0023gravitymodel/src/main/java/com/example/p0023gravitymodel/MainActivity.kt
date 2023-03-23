package com.example.p0023gravitymodel;

import android.os.Bundle;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import kotlin.math.pow;

class MainActivity : AppCompatActivity() {
    private val gConst : Double = 6.67384 * 10.0.pow(-11);

    private lateinit var weight1EditTextNumber : EditText;

    private lateinit var weight2EditTextNumber : EditText;

    private lateinit var distanceEditTextNumber : EditText;

    private lateinit var forceEditTextNumber : EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weight1EditTextNumber = findViewById(R.id.weight1EditTextNumber);
        weight2EditTextNumber = findViewById(R.id.weight2EditTextNumber);
        distanceEditTextNumber = findViewById(R.id.distanceEditTextNumber);
        forceEditTextNumber = findViewById(R.id.forceEditTextNumber);
    }

    fun calculateButton_onClick(view: View) {
        try
        {
            val weight1 : Double = getDoubleFromControl(weight1EditTextNumber);
            val weight2 : Double = getDoubleFromControl(weight2EditTextNumber);
            val distance : Double = getDoubleFromControl(distanceEditTextNumber);
            setDoubleIntoControl(forceEditTextNumber, (gConst * weight1 * weight2) /
                    (distance * distance));
        }
        catch (ex: NumberFormatException)
        {
            onCreateDialog("Error!", "Empty text input!").show();
        }
    }

    fun onCreateDialog(title: String, message: String): AlertDialog {
        return this.let {
            val builder = AlertDialog.Builder(it);
            builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("ОК") {
                        dialog, id ->  dialog.cancel();
                }
            builder.create();
        } ?: throw IllegalStateException("Activity cannot be null");
    }

    fun getDoubleFromControl(editText: EditText?) : Double {
        return editText?.getText().toString().toDouble();
    }

    fun setDoubleIntoControl(editText: EditText?, value: Double) {
        editText?.setText(value.toString());
    }
}