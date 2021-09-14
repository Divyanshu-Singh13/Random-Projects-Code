package com.example.to_do_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.nio.channels.SelectableChannel;

public class DataBaseTask extends SQLiteOpenHelper {
    public static final String DataBase_Name = "Tasks_Details.db";
    public static final String Table_Name = "Tasks_table";
    public static final String TABLE_NAME = "User_detail";

    public DataBaseTask(Context context) {

        super(context, DataBase_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table " + Table_Name + " (Task_ID INTEGER primary key AUTOINCREMENT , Task_Title TEXT , Task_Details TEXT , Username TEXT)");
        DB.execSQL("create table " + TABLE_NAME + "(Username Text PRIMARY KEY , Phone_No Integer , Email_id TEXT , Password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(DB);

    }
    public boolean insertData(String title, String desc, String id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Task_Title",title);
        contentValues.put("Task_Details",desc);
        contentValues.put("Task_ID",id);
        long res = DB.insert(Table_Name,null,contentValues);
        if (res==-1)
        {
            return false;
        }
        else
        {
            return  true;
        }
    }

    public boolean updateData (String title, String desc, String id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Task_Title",title);
        contentValues.put("Task_Details",desc);
        contentValues.put("Task_ID",id);
        Cursor cursor = DB.rawQuery("Select * from " + Table_Name + " where Task_ID = ? ",new String[]{id});
        DB.update(Table_Name,contentValues,"Task_ID = ?",new String[]{id});
        if (cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }


    }

    public Cursor viewData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor result = DB.rawQuery("Select * from " + Table_Name ,null);
        return result;

    }

    public int deleteData(String title, String desc, String id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.delete(Table_Name, "Task_ID = ?",new String[]{id});
    }

    public boolean insertValue(String usern, String passw, String Phone, String email){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", usern);
        contentValues.put("Password", passw);
        contentValues.put("Phone_No", email);
        contentValues.put("Email_id", Phone);
        long res = DB.insert(TABLE_NAME, null, contentValues);
        if (res == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public boolean checkuser(String username)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from User_detail where Username = ? ", new String[]{username});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean checkuserpass(String username, String password)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from User_detail where Username = ? and Password = ?", new String[]{username,password});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    public boolean checkother(String email, String phone)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from  User_detail where Phone_No = ? and  Email_id = ?" ,new String[]{email,phone} );
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return  false;
        }
    }
}



