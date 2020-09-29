package com.dude.myapplication

import ItemAdapters
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.content_list_entries.*

class ListEntriesActivity : AppCompatActivity(), View.OnClickListener,
    AdapterView.OnItemClickListener {

    private val database = DatabaseManager(this)
    private var listView: ListView? = null
    private var itemAdapters: ItemAdapters? = null
    private var entryList: ArrayList<Entry>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_entries)
        setSupportActionBar(findViewById(R.id.toolbar))

        entryList = database.readAll();

        listView = findViewById(R.id.card_view_list_view)
        itemAdapters = ItemAdapters(applicationContext, entryList!!)
        listView?.adapter = itemAdapters
        listView?.onItemClickListener = this

        val factorDpToPx = resources.displayMetrics.density

//        for (entry in entryList)
//        {
//            val entryCard = CardView(this)
//            val outerLinearLayout = LinearLayout(this)
//            val imageView = ImageView(this)
//            val innerLinearLayout = LinearLayout(this)
//            val textViewTitle = TextView(this)
//            val textViewDescription = TextView(this)
//
//            textViewTitle.text = entry.title
//            textViewDescription.text = entry.description
//
//
//
//            outerLinearLayout.orientation = LinearLayout.HORIZONTAL
//            innerLinearLayout.orientation = LinearLayout.VERTICAL
//
//            OverviewLinearLayout.addView(entryCard)
//            counter++
//            entryCard.tag = counter.toString()
//
//            entryCard.addView(outerLinearLayout)
//            outerLinearLayout.addView(imageView)
//            outerLinearLayout.addView(innerLinearLayout)
//            innerLinearLayout.addView(textViewTitle)
//            innerLinearLayout.addView(textViewDescription)
//        }
    }

    override fun onClick(view: View?) {

        Toast.makeText(applicationContext, "onClick", Toast.LENGTH_LONG).show()
        // TODO("Not yet implemented")
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val entry: Entry = entryList?.get(position)!!
        Toast.makeText(applicationContext, entry.title, Toast.LENGTH_LONG).show()
        // TODO("open here a new Activity")
    }
}