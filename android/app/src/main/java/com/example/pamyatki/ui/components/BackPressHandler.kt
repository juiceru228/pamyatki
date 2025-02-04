package com.example.pamyatki.ui.components
import android.widget.Toast
import androidx.activity.OnBackPressedCallback

class BackPressHandler(private val onBackPressedCallback: OnBackPressedCallback) {
    private var lastPressTime: Long = 0
    fun handleBackPress(context: android.content.Context){
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastPressTime < 2000){
            onBackPressedCallback.handleOnBackPressed()
        }else{
            Toast.makeText(context,"Please click BACK ahain to exit", Toast.LENGTH_SHORT).show()
        }
        lastPressTime = currentTime
    }
}