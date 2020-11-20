package com.twinrock.sqliteeg

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName

    lateinit var editTextName: EditText
    lateinit var editTextPhone: EditText
    lateinit var editTextAddress: EditText

    lateinit var chipUserCount: Chip

    lateinit var btnSave: Button

    lateinit var dbHelper: MyDatabaseHelper;
    lateinit var db: SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextName = findViewById(R.id.edName)
        editTextPhone = findViewById(R.id.edPhone)
        editTextAddress = findViewById(R.id.edAddress)

        chipUserCount = findViewById(R.id.chipUserCount)
        chipUserCount.setOnClickListener {
            startActivity(Intent(MainActivity@this, UserListActivity::class.java))
        }

        // create database instance
        dbHelper = MyDatabaseHelper(this);
        db = dbHelper.writableDatabase;

        // retrieve users from DB
        var result = db.rawQuery("Select * from users", null)
        if (result != null && result.count > 0){
            UserListActivity.userList  = convertCursorToList(result)
        }

        btnSave = findViewById(R.id.buttonSave)
        btnSave.setOnClickListener {
            saveUserToDB()
            clearForm()
        }

        chipUserCount.text = "Saved User = ${UserListActivity.userList?.size}"
    }

    private fun saveUserToDB() {
        var cv = ContentValues()
        cv.put("name", editTextName.text.toString())
        cv.put("phone", editTextPhone.text.toString())
        cv.put("address", editTextAddress.text.toString())
        var res = db.insert(DatabaseSchema.TableNames.USER, null, cv)
        Toast.makeText(MainActivity@this, "Save Successfull", Toast.LENGTH_SHORT).show()

        if (res > 0) {
            // add to list
            UserListActivity.userList.add(
                User(name = editTextName.text.toString(),
                    phone = editTextPhone.text.toString(),
                    address = editTextAddress.text.toString())
            )

            // update list size
            chipUserCount.text = "Saved User = ${UserListActivity.userList?.size}"
        }
    }

    private fun convertCursorToList(result: Cursor): MutableList<User> {
        var userList = mutableListOf<User>()
        result.moveToFirst()
        do {
            var user = User(name = result.getString(result.getColumnIndexOrThrow("name")),
                phone = result.getString(result.getColumnIndexOrThrow("phone")),
                address = result.getString(result.getColumnIndexOrThrow("address")))
            userList.add(user)
            result.moveToNext()
        } while (!result.isAfterLast)
        return userList
    }

    private fun clearForm() {
        editTextAddress.text.clear()
        editTextName.text.clear()
        editTextPhone.text.clear()
    }
}