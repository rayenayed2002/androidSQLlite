package com.example.myapplicationsql

import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ListEtudiant : AppCompatActivity() {
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_etudiant)

        listView = findViewById(R.id.idlistetu)

        // TODO: Replace this with actual SQL query to retrieve data from the database
        val query = "SELECT * FROM ${EtudiantBC.EtudiantEntry.TABLE_NAME}"

        // Use use block for better resource management
        getDbHelper().readableDatabase.use { db ->
            val cursor = db.rawQuery(query, null)
            val adapter = getAdapter(cursor)
            listView.adapter = adapter
        }
    }

    private fun getDbHelper(): EtudiantDBHelper {
        return EtudiantDBHelper(this)
    }

    private fun getAdapter(cursor: Cursor): SimpleCursorAdapter {
        val fromColumns = arrayOf(
            EtudiantBC.EtudiantEntry.COLUMN_NAME_NOM,
            EtudiantBC.EtudiantEntry.COLUMN_NAME_PRENOM
        )

        val toViews = intArrayOf(
            R.id.nometud,
            R.id.preetud
        )

        return SimpleCursorAdapter(
            this,
            R.layout.ligne_etudiant,
            cursor,
            fromColumns,
            toViews,
            0
        )
    }
}