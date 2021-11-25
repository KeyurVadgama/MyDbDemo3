package com.example.mydbdemo3

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context: Context):SQLiteOpenHelper(context,"LoginDb",null,1){
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE LOGIN(_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, UNAME TEXT, PASSWORD TEXT,ISACTIVE TEXT)")
        p0?.execSQL("INSERT INTO LOGIN(NAME,UNAME,PASSWORD,ISACTIVE) VALUES('ADMIN','ADMIN@au.com','AU123','yes')")
        p0?.execSQL("INSERT INTO LOGIN(NAME,UNAME,PASSWORD,ISACTIVE) VALUES('Keyur','keyur096@gmail.com','keps096','yes')")
        p0?.execSQL("INSERT INTO LOGIN(NAME,UNAME,PASSWORD,ISACTIVE) VALUES('Rohit','rohitm@gmail.com','romi@123','no')")
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}