package com.dude.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseManager(context: Context):
    SQLiteOpenHelper(context, "entrie.db", null, 1) {


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE [entrie]" +
                " ([id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "[title] TEXT NOT NULL, [description] TEXT NOT NULL);" +
                "CREATE TABLE [pictureList]" +
                "([id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "[entrieID] INTEGER," +
                "FOREIGN KEY (entrieID) REFERENCES entrie (id))")
    }

    override fun onUpgrade(db: SQLiteDatabase,
                           versionAlt: Int, versionNeu: Int) {
    }

    fun writer(title: String, description: String): String {

        val output:String
        if (title != "" || description != "")
        {
            val writer = this.writableDatabase
            val record = ContentValues()

            record.put("title", title)
            record.put("description", description)
            output = if(writer.insert("entrie", null, record) == -1L)
                "Fehler beim Hinzufügen"
            else
                "Datensatz hinzugefügt"

            writer.close()
        }
        else
        {
            output = "Fehler beim Hinzufügen: Leerer Datensatz"
        }
        return output
    }

    fun readAll(): ArrayList<Entry> {
        val reader = this.readableDatabase
        var data = ""
        val entryList = ArrayList<Entry>()
        val result = reader.rawQuery(
            "SELECT * FROM entrie", null)
        if (result.count == 0)
            data += "Keine Datensätze"
        else {
            while(result.moveToNext()) {
                val entry = Entry(
                result.getInt(
                    result.getColumnIndex("id")),
                result.getString(
                    result.getColumnIndex("title")),
                result.getString(
                    result.getColumnIndex("description")))
                entryList.add(entry)

                data += "id %d: %s %s\n"
                    .format(entry.id, entry.title, entry.description)
            }
        }

        // text_input_description.text = data
        result.close()
        reader.close()
        return entryList
    }

    fun readTitle(): MutableList<String> {
        val reader = this.readableDatabase
        var data = ""
        var titleList = mutableListOf<String>("")

        val result = reader.rawQuery(
            "SELECT title FROM entrie", null)
        if (result.count == 0)
            data += "Keine Datensätze"
        else {
            while(result.moveToNext()) {
                val title = result.getString(
                    result.getColumnIndex("title"))
                titleList.add(title)
            }
        }

        // text_input_description.text = data
        result.close()
        reader.close()
        return titleList
    }

    fun readDescription(): MutableList<String> {
        val reader = this.readableDatabase
        var data = ""
        var descriptionList = mutableListOf<String>("")

        val result = reader.rawQuery(
            "SELECT description FROM entrie", null)
        if (result.count == 0)
            data += "Keine Datensätze"
        else {
            while(result.moveToNext()) {
                val description = result.getString(
                    result.getColumnIndex("description"))
                descriptionList.add(description)
            }
        }

        // text_input_description.text = data
        result.close()
        reader.close()
        return descriptionList
    }
}