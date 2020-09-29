package com.dude.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_new_entrie.*

class NewEntrieActivity : AppCompatActivity() {

    private val database = DatabaseManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_entrie)

        saveBtn.setOnClickListener {
            val title = text_input_title.text.toString()
            if(title == "")
                textViewValidateTitle.setText("Muss ausgefüllt sein!")

            val description = text_input_description.text.toString()
            if(description == "")
                textViewValidateDescription.setText("Muss ausgefüllt werden")
            val isWriteToDataBaseInfo = database.writer(title, description)
            text_input_title.setText("")
            text_input_description.setText("")
            closeKeyboard()
            Snackbar.make(newEntrieLayout, isWriteToDataBaseInfo, Snackbar.LENGTH_LONG)
                .show();
        }
    }

    fun closeKeyboard() {
        val view = this.currentFocus
        if(view != null){
            val imm:InputMethodManager= getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

    }

    override fun onStop() {
        super.onStop()
        database.close()
    }


}