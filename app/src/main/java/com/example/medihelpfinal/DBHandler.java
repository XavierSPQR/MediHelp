package com.example.medihelpfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "medih";
    private static final String TABLE_NAME = "patient";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String EMAIL = "email";
    private static final String AGE = "age";

    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table when the database is created
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " " +
                "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT,"
                + ADDRESS + " TEXT,"
                + EMAIL + " TEXT,"
                + AGE + " TEXT"
                + ");";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing table and recreate it if the database version is upgraded
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addProfile(ToDo toDo) {
        // Add a new profile to the database
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, toDo.getName());
        contentValues.put(ADDRESS, toDo.getAddress());
        contentValues.put(EMAIL, toDo.getEmail());
        contentValues.put(AGE, toDo.getAge());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public List<ToDo> getAllProfiles() {
        // Retrieve all profiles from the database
        List<ToDo> allProfiles = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ToDo toDo = new ToDo();
                toDo.setId(cursor.getInt(0));
                toDo.setName(cursor.getString(1));
                toDo.setAddress(cursor.getString(2));
                toDo.setEmail(cursor.getString(3));
                toDo.setAge(cursor.getString(4));
                allProfiles.add(toDo);
            } while (cursor.moveToNext());
        }

        return allProfiles;
    }

    public void deleteProfile(int id) {
        // Delete a profile from the database based on its ID
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public ToDo getSingleProfile(int id) {
        // Retrieve a single profile from the database based on its ID
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID, NAME, ADDRESS, EMAIL, AGE},
                ID + " =?", new String[]{String.valueOf(id)}, null, null, null);
        ToDo toDo;
        if (cursor != null) {
            cursor.moveToFirst();
            toDo = new ToDo(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(0)
            );
            return toDo;
        }
        return null;
    }

    public int updateProfile(ToDo toDo) {
        // Update an existing profile in the database
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, toDo.getName());
        contentValues.put(ADDRESS, toDo.getAddress());
        contentValues.put(EMAIL, toDo.getEmail());
        contentValues.put(AGE, toDo.getAge());

        int status = db.update(TABLE_NAME, contentValues,
                ID + " =?", new String[]{String.valueOf(toDo.getId())});
        db.close();
        return status;
    }

    public List<ToDo> getProfileNames() {
        // Retrieve only the names of profiles from the database
        List<ToDo> allProfiles = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ToDo toDo = new ToDo();
                toDo.setName(cursor.getString(1));
                allProfiles.add(toDo);
            } while (cursor.moveToNext());
        }

        return allProfiles;
    }
}
