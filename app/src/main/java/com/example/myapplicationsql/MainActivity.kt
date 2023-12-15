package com.example.myapplicationsql

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var nomEditText: EditText
    lateinit var prenomEditText: EditText
    lateinit var phoneEditText: EditText
    lateinit var emailEditText: EditText
    lateinit var loginEditText: EditText
    lateinit var mdpEditText: EditText
    private var dbHelper: EtudiantDBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize EditText fields
        nomEditText = findViewById(R.id.Nom)
        prenomEditText = findViewById(R.id.Prenom)
        phoneEditText = findViewById(R.id.Phone)
        emailEditText = findViewById(R.id.Email)
        loginEditText = findViewById(R.id.Login)
        mdpEditText = findViewById(R.id.Mdp)

        val validateButton: Button = findViewById(R.id.btnValider)
        validateButton.setOnClickListener {
            if (isEditTextEmpty()) {
                // Show a message or handle the case where any EditText is empty
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Insert the student into the database
                insertStudentIntoDatabase()

                // Start ListEtudiant activity after inserting the student
                startActivity(Intent(this, ListEtudiant::class.java))
            }
        }
        val annulerButton: Button = findViewById(R.id.btnAnnuler)
        annulerButton.setOnClickListener {
            // Clear the text in EditText fields
            nomEditText.text.clear()
            prenomEditText.text.clear()
            phoneEditText.text.clear()
            emailEditText.text.clear()
            loginEditText.text.clear()
            mdpEditText.text.clear()
        }
    }

    private fun insertStudentIntoDatabase() {
        // Your insertion code here
        // Make sure to replace nomEditText, prenomEditText, etc. with your actual EditText fields
        val nomValue = nomEditText.text.toString()
        val prenomValue = prenomEditText.text.toString()
        val phoneValue = phoneEditText.text.toString()
        val emailValue = emailEditText.text.toString()
        val loginValue = loginEditText.text.toString()
        val mdpValue = mdpEditText.text.toString()

        val values = ContentValues().apply {
            put(EtudiantBC.EtudiantEntry.COLUMN_NAME_NOM, nomValue)
            put(EtudiantBC.EtudiantEntry.COLUMN_NAME_PRENOM, prenomValue)
            put(EtudiantBC.EtudiantEntry.COLUMN_NAME_PHONE, phoneValue)
            put(EtudiantBC.EtudiantEntry.COLUMN_NAME_EMAIL, emailValue)
            put(EtudiantBC.EtudiantEntry.COLUMN_NAME_LOGIN, loginValue)
            put(EtudiantBC.EtudiantEntry.COLUMN_NAME_MDP, mdpValue)
        }

        // Use the getDbHelper function to obtain the database helper instance
        val mDbHelper = getDbHelper()

        // Get the writable database
        val db = mDbHelper.writableDatabase

        // Insert the values into the database
        val newRowId = db.insert(
            EtudiantBC.EtudiantEntry.TABLE_NAME,
            null,
            values
        )

        // Close the database connections
        db.close()
        mDbHelper.close()
    }
    fun getDbHelper(): EtudiantDBHelper {
        if (dbHelper == null) {
            dbHelper = EtudiantDBHelper(this)
        }
        return dbHelper!!
    }
    private fun isEditTextEmpty(): Boolean {
        return nomEditText.text.isBlank() ||
                prenomEditText.text.isBlank() ||
                phoneEditText.text.isBlank() ||
                emailEditText.text.isBlank() ||
                loginEditText.text.isBlank() ||
                mdpEditText.text.isBlank()
    }
}