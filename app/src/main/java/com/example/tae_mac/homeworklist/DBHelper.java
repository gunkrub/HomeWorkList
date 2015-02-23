package com.example.tae_mac.homeworklist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {
    private static final String name = "courses.sqlite3";
    private static final int version = 2;

    public DBHelper(Context ctx) {
        super(ctx, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE homeWork (" +
                "id integer primary key autoincrement," +
                "inTitle text not null," +
                "inCourse text not null," +
                "dueDate text not null," +
                "dueTime text not null," +
                "inDes text not null);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS homeWork;";
        db.execSQL(sql);
        this.onCreate(db);
    }

    public long InsertData(String inTitle, String inCourse, String inDes) {

        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues Val = new ContentValues();
        Val.put("inTitle", inTitle);
        Val.put("inCourse", inCourse);
        Val.put("inDes", inDes);
        long rows = db.insert("homeWork", null, Val);
        db.close();
        return rows;
    }


}