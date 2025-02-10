package com.example.androidflashlightapp

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_layout)

        val seekBar = findViewById<View>(R.id.seekBar) as SeekBar
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int,
                fromUser: Boolean
            ) {
                openFlashLight(true, progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        seekBar.progress = 1
    }

    override fun onDestroy() {
        super.onDestroy()
        openFlashLight(false, 1)
    }

    override fun onStop() {
        super.onStop()
        openFlashLight(false, 1)
    }

    override fun onPause() {
        super.onPause()
        openFlashLight(false, 1)
    }

    override fun onRestart() {
        super.onRestart()
        openFlashLight(true,1)
    }

    override fun onStart() {
        super.onStart()
        openFlashLight(true,1)
    }

    override fun onResume() {
        super.onResume()
        openFlashLight(true, 1)
    }

    private fun openFlashLight(flashLightStatus: Boolean, rating: Int) {
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraId = cameraManager.cameraIdList[0]
        cameraManager.turnOnTorchWithStrengthLevel(cameraId, rating)
        cameraManager.setTorchMode(cameraId, flashLightStatus)
    }
}

