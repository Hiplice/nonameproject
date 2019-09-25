package com.bes.tao.freedge;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static String[] columnNames = {"productID", "productName", "productWeight", "amount", "categoryID", "addDate"};
    private static final String DATABASE_NAME = "fridge.db";
    public static final String TABLE_NAME = "localproductlist";
    private static final int DATABASE_VERSION = 1;

    DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE localproductlist (" +
                "  productID int NOT NULL," +
                "  productName text NOT NULL," +
                "  productWeight int NOT NULL," +
                "  amount text NOT NULL," +
                "  categoryID int NOT NULL," +
                "  addDate text NOT NULL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE `localproductlist`");
        onCreate(db);
    }
}