package com.dude.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_progress_bar.*

class ProgressBarActivity : AppCompatActivity() {
    private var counter = 0
    private var output = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar)

        val control = Handler()
        startState()

        val event = object : Runnable {
            override fun run() {
                counter++
                progressBar.progress = counter
                output += "%d\n".format(counter)
                textViewOutput.text = output
                if (counter <= 15)
                    control.postDelayed(this, 500L)
                else
                    startState()
            }
        }

        StartBtn.setOnClickListener {
            StartBtn.isEnabled = false
            BreakBtn.isEnabled = true
            StopBtn.isEnabled = true
            control.postDelayed(event, 0L)
        }

        BreakBtn.setOnClickListener {
            StartBtn.isEnabled = true
            StartBtn.text = "Weiter"
            BreakBtn.isEnabled = false
            control.removeCallbacks(event)
        }

        StopBtn.setOnClickListener {
            StartBtn.isEnabled = true
            BreakBtn.isEnabled = false
            StopBtn.isEnabled = false
            control.removeCallbacks(event)
            startState()
        }
    }


    private fun startState() {
        counter = 0
        output = ""
        textViewOutput.text = output
        StartBtn.isEnabled = true
        BreakBtn.isEnabled = false
        StopBtn.isEnabled = false
        StartBtn.text = "Start"
        progressBar.progress = counter
    }
}