package de.bc.tobias.autodatenbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    MySQLiteHelper db = new MySQLiteHelper(this);
    Spinner spinner_manufacturer;
    Spinner spinner_model;
    Spinner spinner_constructionyear;
    Spinner spinner_horsepower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinner_manufacturer = (Spinner) findViewById(R.id.spinner_manufacturer);
        spinner_model = (Spinner) findViewById(R.id.spinner_model);
        spinner_constructionyear = (Spinner) findViewById(R.id.spinner_constructionyear);
        spinner_horsepower = (Spinner) findViewById(R.id.spinner_horsepower);

        loadSpinnerManufacturer();

        spinner_manufacturer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String result = spinner_manufacturer.getItemAtPosition(position).toString();
                Log.d("Accuracy", "Listener aktiv");
                loadSpinnerModel(result);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String result = spinner_model.getItemAtPosition(position).toString();
                Log.d("Accuracy", "Listener aktiv");
                loadSpinnerConstructionyear(result);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        spinner_constructionyear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String result = spinner_constructionyear.getItemAtPosition(position).toString();
                Log.d("Accuracy", "Listener aktiv");
                loadSpinnerHorsepower(result);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onClickShowCar (View button) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.opt_exit:
                finish();
                break;
            case R.id.opt_addCar:
                final Intent intent = new Intent(this, AddCarActivity.class);
                startActivity(intent);
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadSpinnerHorsepower(String search_word) {
        List<String> list = db.getHorsepower(search_word);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_horsepower.setAdapter(dataAdapter);
    }

    public void loadSpinnerConstructionyear(String search_word) {
        List<String> list = db.getConstructionyear(search_word);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_constructionyear.setAdapter(dataAdapter);
    }

    public void loadSpinnerModel(String search_word) {
        List<String> list = db.getModels(search_word);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_model.setAdapter(dataAdapter);
    }

    public void loadSpinnerManufacturer() {
        List<String> list = db.getManufacturers();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_manufacturer.setAdapter(dataAdapter);
    }
}
