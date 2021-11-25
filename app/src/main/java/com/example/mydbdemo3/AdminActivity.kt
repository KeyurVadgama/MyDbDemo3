package com.example.mydbdemo3

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.SearchView
import android.widget.SimpleCursorAdapter

class AdminActivity : AppCompatActivity() {
    lateinit var db :SQLiteDatabase
    lateinit var lv :ListView
    lateinit var rs:Cursor
    lateinit var adapter:SimpleCursorAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        var helper = MyDbHelper(applicationContext)
        db = helper.writableDatabase
//        var search = findViewById<SearchView>(R.id.searchView)
        lv = findViewById<ListView>(R.id.listview1)
        rs = db.rawQuery("select _id,NAME,UNAME,PASSWORD,ISACTIVE FROM LOGIN where NAME != 'ADMIN' ",null)
        adapter = SimpleCursorAdapter(
                        applicationContext,
                        R.layout.mylayout1,
                        rs,
                        arrayOf("NAME","UNAME","ISACTIVE"),
                        intArrayOf(R.id.textView3,R.id.textView4,R.id.textView5),
                        0)
        lv.adapter = adapter
        registerForContextMenu(lv)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(111,11,1,"Add User")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            11->{
                startActivity(Intent(applicationContext,Add_User::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.add(1111,11,1,"Update User")
        menu?.add(1111,12,2,"Delete User")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            11->{
                var intent = Intent(applicationContext,Admin_update_activity::class.java)
                intent.putExtra("id",rs.getString(0))
                startActivity(intent)
            }
            12->{
                db.delete("LOGIN","_id=?", arrayOf(rs.getString(0)))
                rs.requery()
                adapter.notifyDataSetChanged()
            }
        }
        return super.onContextItemSelected(item)
    }
}