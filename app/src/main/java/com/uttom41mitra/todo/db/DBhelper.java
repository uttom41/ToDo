package com.uttom41mitra.todo.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.uttom41mitra.todo.db.Constan.COL_DATE;
import static com.uttom41mitra.todo.db.Constan.COL_ID;
import static com.uttom41mitra.todo.db.Constan.COL_TEXT;
import static com.uttom41mitra.todo.db.Constan.COL_TIME;
import static com.uttom41mitra.todo.db.Constan.DAILY_TABLE;
import static com.uttom41mitra.todo.db.Constan.DATABASE_NAME;
import static com.uttom41mitra.todo.db.Constan.DATABASE_VERSION;
import static com.uttom41mitra.todo.db.Constan.EXPENSES_TABLE;
import static com.uttom41mitra.todo.db.Constan.NOTES_TABLE;
import static com.uttom41mitra.todo.db.Constan.TASK_TABLE;

public class DBhelper extends SQLiteOpenHelper {

    // User table create
    public static final String CREATE_DAILY_TABLE = "CREATE TABLE " + DAILY_TABLE + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_DATE + " varchar NOT NULL," +
            COL_TIME + " varchar NOT NULL," +
            COL_TEXT + " varchar NOT NULL )";

    public static final String CREATE_EXPENSES_TABLE = "CREATE TABLE " + EXPENSES_TABLE + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_DATE + " varchar NOT NULL," +
            COL_TIME + " varchar NOT NULL," +
            COL_TEXT + " varchar NOT NULL )";


    public static final String CREATE_NOTES_TABLE = "CREATE TABLE " + NOTES_TABLE + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_DATE + " varchar NOT NULL," +
            COL_TIME + " varchar NOT NULL," +
            COL_TEXT + " varchar NOT NULL )";


    public static final String CREATE_TASK_TABLE = "CREATE TABLE " + TASK_TABLE + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_DATE + " varchar NOT NULL," +
            COL_TIME + " varchar NOT NULL," +
            COL_TEXT + " varchar NOT NULL )";



    private static DBhelper instance;

    public static synchronized DBhelper getHelper(Context context) {
        if (instance == null) {
            instance = new DBhelper(context);
        }
        return instance;
    }


    public DBhelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBhelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DAILY_TABLE);
        db.execSQL(CREATE_EXPENSES_TABLE);
        db.execSQL(CREATE_NOTES_TABLE);
        db.execSQL(CREATE_TASK_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DAILY_TABLE);
        db.execSQL("drop table if exists " + EXPENSES_TABLE);
        db.execSQL("drop table if exists " + NOTES_TABLE);
        db.execSQL("drop table if exists " + TASK_TABLE);
        onCreate(db);
    }
}
