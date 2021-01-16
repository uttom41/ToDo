package com.uttom41mitra.todo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static com.uttom41mitra.todo.db.Constan.COL_DATE;
import static com.uttom41mitra.todo.db.Constan.COL_ID;
import static com.uttom41mitra.todo.db.Constan.COL_TEXT;
import static com.uttom41mitra.todo.db.Constan.COL_TIME;
import static com.uttom41mitra.todo.db.Constan.NOTES_TABLE;
import static com.uttom41mitra.todo.db.Constan.TASK_TABLE;

public class TaskDAO {
    private DBhelper sqlDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public TaskDAO(Context context){
        sqlDatabaseHelper = new DBhelper(context);
    }

    public void open(){
        sqLiteDatabase = sqlDatabaseHelper.getWritableDatabase();
    }
    public void close(){
        sqLiteDatabase.close();
    }

    public boolean AddTask(ModelValue model){
        this.open();
        ContentValues values = new ContentValues();
        values.put(COL_TEXT,model.getText());
        values.put(COL_DATE,model.getDate());
        values.put(COL_TIME,model.getTime());

        long id =   sqLiteDatabase.insert(TASK_TABLE,null,values);

        this.close();
        return id>0 ;
    }
    public ArrayList<ModelValue> GetAllValue(){
        ArrayList<ModelValue> dailylist = new ArrayList<>();
        this.open();
        Cursor cursor = sqLiteDatabase.query(TASK_TABLE,null,null,null,null,null,null);


        cursor.moveToFirst();
        if (cursor !=null && cursor.getCount()>0){
            for (int i=0;i<cursor.getCount();i++){
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
                String text = cursor.getString(cursor.getColumnIndex(COL_TEXT));
                String date = cursor.getString(cursor.getColumnIndex(COL_DATE));
                String time = cursor.getString(cursor.getColumnIndex(COL_TIME));


                ModelValue userModel = new ModelValue();
                userModel.setId(id);
                userModel.setText(text);
                userModel.setDate(date);
                userModel.setTime(time);


                dailylist.add(userModel);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return dailylist;
    }

    public boolean DeleteDaily(int ibd){
        this.open();
        int delete=  sqLiteDatabase.delete(TASK_TABLE,COL_ID +"=?",new String[] {Integer.toString(ibd)} );

        return delete > 0;
    }
}
