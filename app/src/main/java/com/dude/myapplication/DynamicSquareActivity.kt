package com.dude.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_dynamic_square.*
import kotlin.random.Random

class DynamicSquareActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_square)

        val breite = resources.displayMetrics.widthPixels
        val hoehe = resources.displayMetrics.heightPixels
        var zaehler = 0

        buNeu.setOnClickListener {
            val ivNeu = ImageView(this)
            flGesamt.addView(ivNeu)
            ivNeu.setImageResource(R.drawable.gruen)
            ivNeu.setOnClickListener { view ->
                tvAusgabe.text = view.tag.toString()
            }

            zaehler++
            ivNeu.tag = zaehler

            val lp = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT)
            lp.leftMargin = (Random.nextDouble(
                breite * 0.9)).toInt()
            lp.topMargin = (Random.nextDouble(
                hoehe * 0.05, hoehe * 0.85)).toInt()
            ivNeu.layoutParams = lp
        }

        ivBild0.setOnClickListener { view ->
            tvAusgabe.text = view.tag.toString()
        }
    }
}