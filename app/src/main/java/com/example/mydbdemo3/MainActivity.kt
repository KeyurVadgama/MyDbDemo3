package com.example.mydbdemo3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var name = findViewById<EditText>(R.id.edName)
        var pwd = findViewById<EditText>(R.id.Edpassword)
        var btnlogin = findViewById<Button>(R.id.btnsubmit)

        var helper = MyDbHelper(applicationContext)
        var db = helper.readableDatabase

        btnlogin.setOnClickListener {
            var rs = db.rawQuery("SELECT * FROM LOGIN WHERE UNAME = ? AND PASSWORD = ?",
                arrayOf(name.text.toString(),pwd.text.toString()))
            if(rs.moveToFirst()) {
                var active = rs.getString(4)
                if(active.equals("yes")) {
                    if(name.text.toString().equals("ADMIN@au.com")){
                        var intent = Intent(applicationContext,AdminActivity::class.java)
                        startActivity(intent)
                    }
                    Toast.makeText(applicationContext,"Welcome!! you are authenticated user",Toast.LENGTH_LONG).show()
                }
                else
                    Toast.makeText(applicationContext,"Your profile is not approved by admin",Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(applicationContext,"You are not authenciated",Toast.LENGTH_LONG).show()
        }
    }
}