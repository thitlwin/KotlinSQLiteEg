package com.twinrock.sqliteeg

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    
    var TAG = "--MyDatabaseHelper--"

    override fun onCreate(db: SQLiteDatabase) {
        Log.i(TAG, "start onCreate database-----------")
        db.execSQL(DatabaseSchema.CreateTable.USER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.i(TAG, "start onUpgrade database-----------")
        db.execSQL("Drop Table If Exists " + DatabaseSchema.TableNames.USER)
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "my_test_db"
        const val DATABASE_VERSION = 1
    }
}