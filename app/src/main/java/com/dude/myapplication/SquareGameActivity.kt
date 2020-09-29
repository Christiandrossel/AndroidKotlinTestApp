package com.dude.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_quadrate_spiel.*
import java.util.*
import kotlin.random.Random

class SquareGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quadrate_spiel)



        // Neues Quadrat erzeugen
        val newHandler = Handler()

        //Dialogmeldung
        val message = AlertDialog.Builder(this)
        message.setMessage("Kennen sie die Spielregeln?")
        message.setPositiveButton("Ja"){_,_ ->
            startGame(newHandler)
        }
        message.setNegativeButton("Nein"){_,_ ->
            val gameManual = AlertDialog.Builder(this)
            gameManual.setMessage("Oben Links wird das Quadrat in der Farbe angezeigt, welches Sie auswählen müssen\n\n" +
                    "Daneben sehen Sie ihre erreichten Punkte. Für jedes richtige gibt es +1 Punkt, für jedes falsche gibt es -1 Punkt\n\n" +
                    "Oben rechts sehen Sie die Spielzeit, die abläuft\n\n" +
                    "Darunter werden zufällig Quadrate in unterschiedlichen Farben auftauchen. Wählen Sie nur diese, mit der entsprechenden Farbe aus")
            gameManual.setPositiveButton("Ok"){ _,_ ->
                startGame(newHandler)
            }
            gameManual.show()
        }
        message.show()
    }

    private fun startGame(newHandler: Handler){
        // Alle Farben
        val colorArray = intArrayOf(
            R.drawable.rot, R.drawable.gelb,
            R.drawable.gruen, R.drawable.blau,
            R.drawable.cyan, R.drawable.ocker)

        // Einstellungen für Ziel
        var colorTarget = R.drawable.rot
        var target = ivZiel

        // Einstellungen für Zeit
        val timeStart = Calendar.getInstance().time.time
        var gameActive = true

        val flWidth = resources.displayMetrics.widthPixels
        val flHeight = resources.displayMetrics.heightPixels
        var points = 0

        val neuObjekt = object:Runnable {
            override fun run() {
                val ivNeu = ImageView(this@SquareGameActivity)
                val farbe = colorArray[Random.nextInt(colorArray.size)]
                ivNeu.tag = farbe
                ivNeu.setImageResource(farbe)

                val lp = FrameLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
                lp.leftMargin = (Random.
                nextDouble(0.05, 0.85) * flWidth).toInt()
                lp.topMargin = (Random.
                nextDouble(0.05, 0.8) * flHeight).toInt()
                ivNeu.layoutParams = lp
                flGesamt.addView(ivNeu)

                // Punkte zählen
                ivNeu.setOnClickListener {
                    flGesamt.removeView(ivNeu)
                    if(gameActive) {
                        points += if (ivNeu.tag == colorTarget) 1 else -1
                        tvPunkte.text = "Punkte: %d".format(points)
                    }
                }

                // Neues Quadrat in 5 sec. löschen
                val loeschHandler = Handler()
                val loeschObjekt = object:Runnable {
                    override fun run() {
                        flGesamt.removeView(ivNeu)
                    }
                }
                loeschHandler.postDelayed(loeschObjekt, 5000L)

                // Nächstes Quadrat erzeugen
                newHandler.postDelayed(this, 500L)
            }
        }
        // Erstes Quadrat erzeugen
        newHandler.postDelayed(neuObjekt, 500L)

        // Zielfarbe ändern
        val zielHandler = Handler()
        val zielObjekt = object:Runnable {
            override fun run() {
                val farbeZielAlt = colorTarget
                while(farbeZielAlt == colorTarget)
                    colorTarget = colorArray[Random.
                    nextInt(colorArray.size)]

                // Altes Ziel löschen
                flGesamt.removeView(target)

                // Neues Ziel erzeugen
                target = ImageView(this@SquareGameActivity)
                target.setImageResource(colorTarget)
                val lp = FrameLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
                target.layoutParams = lp
                flGesamt.addView(target)

                // Nächstes Ziel erzeugen
                zielHandler.postDelayed(this, 5000L)
            }
        }
        zielHandler.postDelayed(zielObjekt, 5000L)

        // Zeitanzeige aktualisieren
        val zeitHandler = Handler()
        val zeitObjekt = object:Runnable {
            override fun run() {
                val zeitAktuell = Calendar.getInstance().time.time
                val zeitDifferenz = zeitAktuell - timeStart
                val zeitAusgabe = 60 - zeitDifferenz / 1000
                tvZeit.text = "%d sec.".format(zeitAusgabe)

                if(zeitDifferenz > 60000L) {
                    gameActive = false
                    newHandler.removeCallbacks(neuObjekt)
                    zielHandler.removeCallbacks(zielObjekt)
                }
                else
                    zeitHandler.postDelayed(this, 1000L)
            }
        }
        zeitHandler.postDelayed(zeitObjekt, 1000L)
    }
}