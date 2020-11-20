package com.twinrock.sqliteeg

import UserListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserListActivity : AppCompatActivity() {
    companion object {
        var userList = mutableListOf<User>()
    }
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        val dbHelper = MyDatabaseHelper(this);
        val db = dbHelper.writableDatabase;

        recyclerView = findViewById(R.id.recUserList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = UserListAdapter(userList, db)
        recyclerView.adapter = adapter
    }
}