package com.example.gamebacklog.ui

import android.util.Log
import android.view.View
import com.example.gamebacklog.model.Game

class UndoListener(private val itemsToBeDeleted : ArrayList<Game>, private val mainActivityViewModel: MainActivityViewModel) : View.OnClickListener {

    override fun onClick(v: View) {
        for (game in itemsToBeDeleted) {
            Log.i("Hello: ", game.title)
        }
    }
}