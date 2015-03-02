package de.bc.tobias.autodatenbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tobias on 27.02.2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_CARS = "cars";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MANUFACTURER = "manufacturer";
    public static final String COLUMN_MODEL = "model";
    public static final String COLUMN_CONSTRUCTIONYEAR = "constructionyear";
    public static final String COLUMN_HORSEPOWER = "horsepower";

    // Erstellt die Datenbank
    private static final String DATABASE_NAME = "cars.db";
    private static final int DATABASE_VERSION = 1;

    // Erstellt die Tabelle
    private static final String DATABASE_CREATE = "create table "
            + TABLE_CARS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_MANUFACTURER
            + " string, " + COLUMN_MODEL + " string, " + COLUMN_CONSTRUCTIONYEAR
            + " integer, " + COLUMN_HORSEPOWER + " integer);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARS);
        Log.d("Accuracy", "deleted");
        this.onCreate(db);
    }


    public List<String> getManufacturers(){
        ArrayList<String> manufacturers = new ArrayList<String>();
           //db generate only read
        SQLiteDatabase db = this.getReadableDatabase();
        //sql querry search for MANUFACTURERS
        Cursor result = db.query(true,TABLE_CARS, new String[] { COLUMN_MANUFACTURER}, null,
                null, null, null, null, null);
        //Write the result in Object car and return it
        while(result.moveToNext()){
            manufacturers.add(new String(result.getString(0)));
            System.out.println(result.getString(0));
            Log.d("Accuracy", "fährt");
        }
        result.close();
        return manufacturers;

    }

    public void addCar(Car car) {

        SQLiteDatabase db = this.getWritableDatabase();
        //No ID needed, because of autoincrement
        ContentValues values = new ContentValues();
        values.put(COLUMN_MANUFACTURER, car.getManufacturer());
        values.put(COLUMN_MODEL, car.getModel());
        values.put(COLUMN_CONSTRUCTIONYEAR, car.getConstructionyear());
        values.put(COLUMN_HORSEPOWER, car.getHorsepower());

        db.insert(TABLE_CARS, null, values);
        db.close();
    }
}
