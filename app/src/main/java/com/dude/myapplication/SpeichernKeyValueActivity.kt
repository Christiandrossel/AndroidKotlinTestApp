package com.dude.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_speichern_key_value.*

class SpeichernKeyValueActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speichern_key_value)

        val store = this.getSharedPreferences("daten.pr", Context.MODE_PRIVATE)

        writeBtn.setOnClickListener {
            val writer = store.edit()
            writer.putInt("value01", 42)
            writer.putFloat("value02", 3.14159f)
            writer.putString("value03", "Das ist ein String")
            if(!writer.commit())
                textViewOutput.text = "Problem beim Schreiben"
        }

        readBtn.setOnClickListener {
            try {
                val val01 = if(store.contains("value01"))
                    store.getInt("value01", 0) else -1
                val val02 = if(store.contains("value02"))
                    store.getFloat("value02", 0.0f) else -1.0f
                val val03 = if(store.contains("value03"))
                    store.getString("value03", "") else "(leer)"
                textViewOutput.text = "%d \n%.2f \n%s".format(val01, val02, val03)
            }
            catch(ex:Exception) {
                textViewOutput.text = "Falsches Format"
            }
        }

        deleteOneBtn.setOnClickListener {
            val writer = store.edit()
            writer.remove("value01")
            if(!writer.commit())
                textViewOutput.text = "Problem beim Löschen"
        }

        deleteAllBtn.setOnClickListener {
            val writer = store.edit()
            writer.clear()
            if(!writer.commit())
                textViewOutput.text = "Problem beim Löschen"
        }
    }
}