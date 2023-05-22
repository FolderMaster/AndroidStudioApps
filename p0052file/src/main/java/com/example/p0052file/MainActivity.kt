package com.example.p0052file;

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.p0052file.model.Country
import com.example.p0052file.model.Monarchy
import com.example.p0052file.model.MonarchyType
import com.example.p0052file.model.Republic
import com.example.p0052file.model.RepublicType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    companion object {
        private val fileName: String = "save.json";
    }

    private lateinit var adapter: ArrayAdapter<Country>;

    private lateinit var listView: ListView;

    private lateinit var addMonarchyButton: Button;

    private lateinit var addRepublicButton: Button;

    private lateinit var removeButton: Button;

    private lateinit var nameTextView: TextView;

    private lateinit var nameEditText: EditText;

    private lateinit var capitalTextView: TextView;

    private lateinit var capitalEditText: EditText;

    private lateinit var monarchyTypeTextView: TextView;

    private lateinit var monarchyTypeSpinner: Spinner;

    private lateinit var republicTypeTextView: TextView;

    private lateinit var republicTypeSpinner: Spinner;

    private var Countries: ArrayList<Country> = ArrayList<Country>()
        get() = field;
        set(value) {
            field = value;
            adapter = ArrayAdapter<Country>(this, android.R.layout.simple_list_item_1,
                field);
            listView.adapter = adapter;
        }

    private var selectedCountry: Country? = null
        get() = field;
        set(value) {
            field = value;
            setInfo(field);
            selectIndex(adapter.getPosition(field));
        }

    private fun selectIndex(index: Int) {
        if(index != -1 && index < adapter.count) {
            listView.requestFocusFromTouch();
            listView.setSelection(index);
        }
    }

    private fun setInfo(country: Country?) {
        if(country != null) {
            if(country is Republic) {
                monarchyTypeTextView.visibility = View.GONE;
                monarchyTypeSpinner.visibility = View.GONE;
                republicTypeTextView.visibility = View.VISIBLE;
                republicTypeSpinner.visibility = View.VISIBLE;
                republicTypeSpinner.setSelection(country.republicType.ordinal);
            }
            else if(country is Monarchy) {
                monarchyTypeTextView.visibility = View.VISIBLE;
                monarchyTypeSpinner.visibility = View.VISIBLE;
                monarchyTypeSpinner.setSelection(country.monarchyType.ordinal);
                republicTypeTextView.visibility = View.GONE;
                republicTypeSpinner.visibility = View.GONE;
            }
            nameTextView.visibility = View.VISIBLE;
            nameEditText.visibility = View.VISIBLE;
            nameEditText.setText(country.name);
            capitalTextView.visibility = View.VISIBLE;
            capitalEditText.visibility = View.VISIBLE;
            capitalEditText.setText(country.capital);
        }
        else {
            nameTextView.visibility = View.GONE;
            nameEditText.visibility = View.GONE;
            capitalTextView.visibility = View.GONE;
            capitalEditText.visibility = View.GONE;
            monarchyTypeTextView.visibility = View.GONE;
            monarchyTypeSpinner.visibility = View.GONE;
            republicTypeTextView.visibility = View.GONE;
            republicTypeSpinner.visibility = View.GONE;
        }
    }

    private fun save(countries: ArrayList<Country>) {
        var fos: FileOutputStream? = null;
        try {
            val text: String = Json.encodeToString(countries);
            fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(text.toByteArray());
            Toast.makeText(this, "File saved.", Toast.LENGTH_SHORT).show();
        }
        catch(ex: IOException) {
            Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show();
        }
        finally {
            try {
                fos?.close();
            }
            catch(ex: IOException) {
                Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun load(): ArrayList<Country> {
        var countries: ArrayList<Country> = ArrayList<Country>();
        var fin: FileInputStream? = null;
        try {
            fin = openFileInput(fileName);
            val bytes: ByteArray = ByteArray(fin.available());
            fin.read(bytes);
            val text: String = String(bytes);
            countries = Json.decodeFromString(text);
            Toast.makeText(this, "File loaded.", Toast.LENGTH_SHORT).show();
        }
        catch(ex: IOException) {
            Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show();
        }
        finally {
            try {
                fin?.close();
            }
            catch(ex: IOException) {
                Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show();
            }
        }
        return countries;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        addRepublicButton = findViewById(R.id.addRepublicButton);
        addMonarchyButton = findViewById(R.id.addMonarchyButton);
        removeButton = findViewById(R.id.removeButton);
        nameTextView = findViewById(R.id.nameTextView);
        nameEditText = findViewById(R.id.nameEditText);
        capitalTextView = findViewById(R.id.capitalTextView);
        capitalEditText = findViewById(R.id.capitalEditText);
        monarchyTypeTextView = findViewById(R.id.monarchyTypeTextView);
        monarchyTypeSpinner = findViewById(R.id.monarchyTypeSpinner);
        republicTypeTextView = findViewById(R.id.republicTypeTextView);
        republicTypeSpinner = findViewById(R.id.republicTypeSpinner);

        Countries = load();
        listView.onItemClickListener = object: AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, itemClicked: View?, position: Int,
                                     id: Long) {
                selectedCountry = adapter.getItem(position);
            }
        };
        nameEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val country: Country? = selectedCountry;
                if(country != null) {
                    country.name = s.toString();
                    adapter.notifyDataSetInvalidated();
                }
            }
        });
        capitalEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val country: Country? = selectedCountry;
                if(country != null) {
                    country.capital = s.toString();
                    adapter.notifyDataSetInvalidated();
                }
            }
        });
        republicTypeSpinner.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, RepublicType.values());
        republicTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int,
                                        id: Long) {
                val country: Country? = selectedCountry;
                if(country is Republic) {
                    country.republicType = RepublicType.values()[position];
                    adapter.notifyDataSetInvalidated();
                }
            }
        };
        monarchyTypeSpinner.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, MonarchyType.values());
        monarchyTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int,
                                        id: Long) {
                val country: Country? = selectedCountry;
                if(country is Monarchy) {
                    country.monarchyType = MonarchyType.values()[position];
                    adapter.notifyDataSetInvalidated();
                }
            }
        };
    }

    override fun onStop() {
        super.onStop();

        save(Countries);
    }

    fun addRepublicButton_onClick(view: View) {
        val country: Country = Republic();
        adapter.add(country);
        selectedCountry = country;
    }

    fun addMonarchyButton_onClick(view: View) {
        val country: Country = Monarchy();
        adapter.add(country);
        selectedCountry = country;
    }

    fun removeButton_onClick(view: View) {
        if(selectedCountry != null) {
            val selectedIndex: Int = adapter.getPosition(selectedCountry);
            adapter.remove(selectedCountry);
            selectedCountry = if(adapter.count > 0) {
                if(selectedIndex >= adapter.count) {
                    adapter.getItem(adapter.count - 1);
                } else {
                    adapter.getItem(selectedIndex);
                }
            } else {
                null;
            }
        }
    }
}