package com.dude.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_progress_bar.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val entriesButton = findViewById<Button>(R.id.entriesButton)
        entriesButton.setOnClickListener {
            val intent = Intent(this, ListEntriesActivity::class.java)
            startActivity(intent)
        }
        val newEntriesButton = findViewById<Button>(R.id.newEntriesButton)
        newEntriesButton.setOnClickListener {
            val intent = Intent(this, NewEntrieActivity::class.java)
            startActivity(intent)
        }


        progressBarBtn.setOnClickListener {
            val intent = Intent(this, ProgressBarActivity::class.java)
            startActivity(intent)
        }

        returnValueBtn.setOnClickListener {
            val returnValueIntent = Intent(this, returnValueActivity::class.java)
            startActivityForResult(returnValueIntent, 1)
        }

        dynamicSquareBtn.setOnClickListener {
            val intent = Intent(this, DynamicSquareActivity::class.java)
            startActivity(intent)
        }

        SquareGameBtn.setOnClickListener {
            val intent = Intent(this, SquareGameActivity::class.java)
            startActivity(intent)
        }

        keyValueBtn.setOnClickListener {
            val intent = Intent(this, SpeichernKeyValueActivity::class.java)
            startActivity(intent)
        }

        addElementActivityBtn.setOnClickListener {
            val intent = Intent(this, AddElementActivity::class.java)
            startActivity(intent)
        }

        val exitButton = findViewById<Button>(R.id.exitButton)
        exitButton.setOnClickListener {
            finishAffinity()
        }
    }

    override fun onActivityResult( requestCode: Int,
                                   resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK &&
                data != null
            ) {
                if (data.hasExtra("returnValue")) {
                    val rueckgabe =
                        data.getIntExtra("returnValue", 0)
                    textView_returnValue.text = "Rückgabe aus Activity: %d"
                        .format(rueckgabe)
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                textView_returnValue.text = "Keine Rückgabe aus Activity"
            }
        }
    }
}