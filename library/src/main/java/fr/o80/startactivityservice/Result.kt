package fr.o80.startactivityservice

import android.content.Intent

data class Result(
    val requestCode: Int,
    val resultCode: Int,
    val data: Intent?
)
