package com.example.gamebacklog.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.TypeConverters
import com.example.gamebacklog.R
import com.example.gamebacklog.database.Converters
import com.example.gamebacklog.model.Game


import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*
import java.lang.Exception
import java.text.SimpleDateFormat

const val EXTRA_GAME = "EXTRA_GAME"

@TypeConverters(Converters::class)
class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)

        initViews()
    }

    private fun initViews() {
        fab.setOnClickListener { onSaveClick() }
    }

    private fun validateEmptyFields(): Boolean {
        if (etTitle.text.toString().isBlank()) {
            Toast.makeText(this,"Please fill in a title"
                , Toast.LENGTH_SHORT).show()
            return false
        }

        if (etPlatform.text.toString().isBlank()) {
            Toast.makeText(this,"Please fill in a platform"
                , Toast.LENGTH_SHORT).show()
            return false
        }

        if (etDay.text.toString().isBlank() || etMonth.text.toString().isBlank() ||
            etYear.text.toString().isBlank()) {
            Toast.makeText(this,"Please fill in a date"
                , Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    @SuppressLint("SimpleDateFormat")
    private fun onSaveClick() {
        if (validateEmptyFields()) {
            val concatenatedString = (etDay.text.toString() + "-" + etMonth.text.toString() + "-" + etYear.text.toString())
            try {
                val date = SimpleDateFormat("dd-MM-yyyy")
                date.isLenient = false
                val parsedDate = date.parse(concatenatedString)

                val game = Game(etTitle.text.toString(), etPlatform.text.toString(), parsedDate)
                val resultIntent = Intent()

                resultIntent.putExtra(EXTRA_GAME, game)
                setResult(Activity.RESULT_OK, resultIntent)

                finish()
            } catch (e : Exception) {
                Toast.makeText(this,"Please fill in a valid date"
                    , Toast.LENGTH_SHORT).show()
            }
        }
    }

}