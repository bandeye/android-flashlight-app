package com.example.androidflashlightapp

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.widget.RatingBar
import android.widget.Switch
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    private var flashLightStatus: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_layout)
        val mySwitch: Switch = findViewById(R.id.switch1)
        mySwitch.setOnCheckedChangeListener { _, _ ->
            openFlashLight()
        }
        mySwitch.isChecked = true
    }

    private fun openFlashLight() {
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraId = cameraManager.cameraIdList[0]
        if (!flashLightStatus) {
            try {
                val myRatingBar: RatingBar = findViewById(R.id.ratingBar)
                cameraManager.turnOnTorchWithStrengthLevel(cameraId, myRatingBar.rating.toInt())
                flashLightStatus = true

            } catch (e: CameraAccessException) {
                println(e.toString())
            }
        } else {
            try {
                cameraManager.setTorchMode(cameraId, false)
                flashLightStatus = false
            } catch (e: CameraAccessException) {
                println(e.toString())
            }
        }
    }
}

