package com.example.androidflashlightapp

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.widget.RatingBar
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_layout)
        val myRatingBar: RatingBar = findViewById(R.id.ratingBar)
        myRatingBar.setOnRatingBarChangeListener{ _, rating, _ ->
            openFlashLight(true, rating.toInt())
        }
        val ratingToSet = 1
        myRatingBar.rating = ratingToSet.toFloat()
    }

    override fun onDestroy() {
        super.onDestroy()
        openFlashLight(false, 1)
    }

    private fun openFlashLight(flashLightStatus: Boolean, rating: Int) {
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraId = cameraManager.cameraIdList[0]
        cameraManager.turnOnTorchWithStrengthLevel(cameraId, rating)
        cameraManager.setTorchMode(cameraId, flashLightStatus)
    }
}

