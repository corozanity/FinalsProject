package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseLogin extends SQLiteOpenHelper  {
    public static final String db_name = "register.db";
    public static final String table_name = "registeruser";
    public static final String col_1 = "ID";
    public static final String col_2 = "Username";
    public static final String col_3 = "Password";

    public DatabaseLogin(Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registeruser (ID Integer, Username TEXT PRIMARY KEY, Password TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int l) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(sqLiteDatabase);

    }

    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user);
        contentValues.put("password", password);

        long res = db.insertWithOnConflict("registeruser", null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();

        return res;
    }

    //checks for username and password matching
    public boolean checkUser(String username, String password){
        String[] columns = { col_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = col_2 + "=?" + " and " + col_3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(table_name, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count > 0)
            return true;
        else
            return false;
    }

    //checks for username matching to avoid duplicates
    public boolean check(String username){
        String[] columns = { col_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = col_2 + "=?";
        String[] selectionArgs = { username};
        Cursor cursor = db.query(table_name, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count > 0)
            return true;
        else
            return false;
    }

    public boolean checkPass(String password){
        String[] columns = { col_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = col_2 + "=?";
        String[] selectionArgs = { password};
        Cursor cursor = db.query(table_name, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count > 0)
            return true;
        else
            return false;
    }


    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE from "+ table_name);
        db.close();
    }
}
