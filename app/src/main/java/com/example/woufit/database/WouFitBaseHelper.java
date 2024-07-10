package com.example.woufit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.woufit.model.PasswordHashing;

import java.security.NoSuchAlgorithmException;

public class WouFitBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "woufitBase.db";
    private static int VERSION = 1;

    public WouFitBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    //only calls onCreate if the database doesn't already exist
    //on first run will create tables and columns
    //on subsequent runs will not call onCreate
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + WouFitDbSchema.SaltTable.NAME + " (" +
                WouFitDbSchema.SaltTable.Columns.SALT_ID + " INTEGER PRIMARY KEY , " +
                WouFitDbSchema.SaltTable.Columns.SALT_STRING + " TEXT)"
        );

        try {
            ContentValues values = new ContentValues();
            values.put("saltString", PasswordHashing.computeSalt());
            db.insert(WouFitDbSchema.SaltTable.NAME, null, values);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    //onUpgrade is only called when it detects that the database version has been incremented
    //used when needing to change the database schema
    //such as adding a new table or altering an existing table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
