package com.dude.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_add_element.*

class AddElementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_element)

        var counter = 0
        val faktorDpToPx = resources.displayMetrics.density

        addTextViewBtn.setOnClickListener {
            val tvNeu = TextView(this)
            LinearLayoutTotal.addView(tvNeu)

            counter++
            tvNeu.tag = counter.toString()
            tvNeu.text = "TextView mit Tag %s"
                .format(tvNeu.tag.toString())
            tvNeu.textSize = 20.0f

            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            val mg = (5 * faktorDpToPx).toInt()
            lp.setMargins(mg, mg, mg, mg)
            tvNeu.layoutParams = lp
        }

        addButtonBtn.setOnClickListener {
            val buNeu = Button(this)
            LinearLayoutTotal.addView(buNeu)

            counter++
            buNeu.tag = counter.toString()
            buNeu.text = "Button mit Tag %s"
                .format(buNeu.tag.toString())
            buNeu.textSize = 20.0f
            buNeu.isAllCaps = false
            buNeu.setOnClickListener { view ->
                textViewAusgabe.text = "Klick auf Button mit Tag %s"
                    .format(view.tag.toString())
            }

            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            buNeu.layoutParams = lp

        }
    }
}
