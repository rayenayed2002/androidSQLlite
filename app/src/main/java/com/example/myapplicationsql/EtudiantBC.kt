package com.example.myapplicationsql

import android.provider.BaseColumns
import com.example.myapplicationsql.EtudiantBC.EtudiantEntry.Companion._ID

class EtudiantBC {
    constructor()

    abstract class EtudiantEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "etudiant"
            const val COLUMN_NAME_NOM = "nom"
            const val COLUMN_NAME_PRENOM = "prenom"
            const val COLUMN_NAME_PHONE = "phone"
            const val COLUMN_NAME_EMAIL = "email"
            const val COLUMN_NAME_LOGIN = "login"
            const val COLUMN_NAME_MDP = "mdp"

            // Additional suggested constants
            const val _COUNT = "count"
            const val _ID = BaseColumns._ID
        }
    }

    private val TEXT_TYPE = " TEXT"
    private val INT_TYPE = " INTEGER"
    private val COMMA_SEP = ","

    val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${EtudiantEntry.TABLE_NAME} (" +
                "$_ID INTEGER PRIMARY KEY$COMMA_SEP" +
                "${EtudiantEntry.COLUMN_NAME_NOM}$TEXT_TYPE$COMMA_SEP" +
                "${EtudiantEntry.COLUMN_NAME_PRENOM}$TEXT_TYPE$COMMA_SEP" +
                "${EtudiantEntry.COLUMN_NAME_PHONE}$TEXT_TYPE$COMMA_SEP" +
                "${EtudiantEntry.COLUMN_NAME_EMAIL}$TEXT_TYPE$COMMA_SEP" +
                "${EtudiantEntry.COLUMN_NAME_LOGIN}$TEXT_TYPE$COMMA_SEP" +
                "${EtudiantEntry.COLUMN_NAME_MDP}$TEXT_TYPE)"


    val SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS ${EtudiantEntry.TABLE_NAME}"
}