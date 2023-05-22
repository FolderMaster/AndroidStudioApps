package com.example.p0051filetest;

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    companion object {
        private val FILE_NAME: String = "content.txt";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun saveText(view: View) {
        var fos: FileOutputStream? = null;
        try {
            val textBox: EditText = findViewById(R.id.editor);
            val text: String = textBox.text.toString();
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.toByteArray());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(ex: IOException) {
            Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show();
        }
        finally {
            try {
                if(fos != null) {
                    fos.close();
                }
            }
            catch(ex: IOException) {
                Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    fun openText(view: View) {
        var fin: FileInputStream? = null;
        val textView: TextView = findViewById(R.id.text);
        try {
            fin = openFileInput(FILE_NAME);
            val bytes: ByteArray = ByteArray(fin.available());
            fin.read(bytes);
            val text: String = String(bytes);
            textView.text = text;
        }
        catch(ex: IOException) {
            Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show();
        }
        finally {
            try {
                if(fin != null) {
                    fin.close();
                }
            }
            catch(ex: IOException) {
                Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}