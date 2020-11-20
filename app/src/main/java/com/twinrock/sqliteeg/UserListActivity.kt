package com.twinrock.sqliteeg

import UserListAdapter
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserListActivity : AppCompatActivity() {
    var TAG = javaClass.name
    lateinit var db: SQLiteDatabase

    companion object {
        var userList = mutableListOf<User>()
    }

    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        val dbHelper = MyDatabaseHelper(this);
        db = dbHelper.writableDatabase;

        recyclerView = findViewById(R.id.recUserList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = UserListAdapter(userList, db)
        recyclerView.adapter = adapter

        setRecyclerViewItemSwipeListener()
    }

    private fun setRecyclerViewItemSwipeListener() {

        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, viewHolder1: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                var user = userList.get(position)
                var res = db.delete("users", "name=?", arrayOf("${user.name}"))
                userList.removeAt(position)
                recyclerView.adapter!!.notifyItemRemoved(position)

                Toast.makeText(this@UserListActivity, "Delete Successful", Toast.LENGTH_SHORT).show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
