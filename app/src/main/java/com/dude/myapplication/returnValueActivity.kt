package com.dude.myapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_return_value.*

class returnValueActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_return_value)



        setResult(Activity.RESULT_CANCELED, intent)

        okBtn.setOnClickListener {
            val returnValue = editTextNumberDecimal.text.toString().toInt()
            intent.putExtra("returnValue", returnValue)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        breakBtn.setOnClickListener {
            finish()
        }
    }
}