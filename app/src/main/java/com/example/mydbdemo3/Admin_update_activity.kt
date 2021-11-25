package com.example.mydbdemo3

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Admin_update_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_update)
        var id = intent.getStringExtra("id")
        var helper = MyDbHelper(applicationContext)
        var db = helper.writableDatabase

        var rs = db.rawQuery("select * from LOGIN where _id=?", arrayOf(id))
        var ed1 = findViewById<EditText>(R.id.ed1)
        var ed2 = findViewById<EditText>(R.id.ed2)
        var ed3 = findViewById<EditText>(R.id.ed3)
        var btn = findViewById<Button>(R.id.button)
        if(rs.moveToNext()) {
            ed1.setText(rs.getString(1))
            ed2.setText(rs.getString(2))
            ed3.setText(rs.getString(4))
        }
        btn.setOnClickListener {
            var cv = ContentValues()
            cv.put("NAME",ed1.text.toString())
            cv.put("UNAME",ed2.text.toString())
            cv.put("ISACTIVE",ed3.text.toString())
            db.update("LOGIN",cv,"_id=?", arrayOf(id))
            rs.requery()
            startActivity(Intent(applicationContext,AdminActivity::class.java))
            finish()
        }
    }
}