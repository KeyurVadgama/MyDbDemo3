package com.example.mydbdemo3

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Add_User : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        var ed1 = findViewById<EditText>(R.id.ed1)
        var ed2 = findViewById<EditText>(R.id.ed2)
        var ed3 = findViewById<EditText>(R.id.ed3)
        var b1 = findViewById<Button>(R.id.btn)

        var helper = MyDbHelper(applicationContext)
        var db = helper.writableDatabase

        b1.setOnClickListener {
            var cv = ContentValues()
            cv.put("NAME",ed1.text.toString())
            cv.put("UNAME",ed2.text.toString())
            cv.put("PASSWORD",ed3.text.toString())
            cv.put("ISACTIVE","yes")
            db.insert("LOGIN",null,cv)
            startActivity(Intent(applicationContext,AdminActivity::class.java))
            finish()
        }
    }
}