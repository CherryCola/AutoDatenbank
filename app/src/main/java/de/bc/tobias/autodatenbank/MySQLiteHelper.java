package de.bc.tobias.autodatenbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Tobias on 27.02.2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_CARS = "positions";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MANUFACTURER = "manufacturer";
    public static final String COLUMN_MODEL = "model";
    public static final String COLUMN_CONSTRUCTIONYEAR = "constructionyear";
    public static final String COLUMN_HORSEPOWER = "horsepower";

    // Erstellt die Datenbank
    private static final String DATABASE_NAME = "positions.db";
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


    public Car getManufactures(String name){

           //db generate only read
        SQLiteDatabase db = this.getReadableDatabase();
        //sql querry search for MANUFACTURER
        Cursor result = db.query(false,TABLE_CARS, new String[] { COLUMN_ID,COLUMN_MANUFACTURER,
                        COLUMN_MODEL, COLUMN_CONSTRUCTIONYEAR,COLUMN_HORSEPOWER }, COLUMN_MANUFACTURER + "=?",
                new String[] { (name) }, null, null, null, null);
        //Write the result in Object car and return it
        Car car = new Car((result.getLong(0)), result.getString(1), result.getString(2),result.getLong(3),result.getLong(4));

        return car;
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
