package de.bc.tobias.autodatenbank;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Tobias on 27.02.2015.
 */
public class AddCarActivity extends Activity {

    MySQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        db = new MySQLiteHelper(this);
}

    /*
    TODO: Clear the editText or finish the activity
     */
    public void onClickAddCar (View button) {
        final Car car = new Car();

        final EditText editText_manufacturer = (EditText) findViewById(R.id.editText_manufacturer);
        final EditText editText_model = (EditText) findViewById(R.id.editText_model);
        final EditText editText_constructionyear = (EditText) findViewById(R.id.editText_constructionyear);
        final EditText editText_horsepower = (EditText) findViewById(R.id.editText_horsepower);

        car.setManufacturer(editText_manufacturer.getText().toString());
        car.setModel(editText_model.getText().toString());
        car.setConstructionyear(Integer.parseInt(editText_constructionyear.getText().toString()));
        car.setHorsepower(Integer.parseInt(editText_horsepower.getText().toString()));

        db.addCar(car);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_car, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.opt_abort_adding_car:
                finish();
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

}
