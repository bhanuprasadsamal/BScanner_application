package com.example.bscaner
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize ZXing integrator
        val integrator = IntentIntegrator(this)
        integrator.setPrompt("Scan a barcode")
        integrator.setBeepEnabled(true)
        integrator.setOrientationLocked(false)

        // Attach scan event listener
        findViewById<Button>(R.id.scanButton).setOnClickListener {
            // Start scanning
            integrator.initiateScan()
        }
    }

    // Handle result from barcode scanner
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                // Barcode successfully scanned
                Toast.makeText(this, "Scanned: ${result.contents}", Toast.LENGTH_LONG).show()
            } else {
                // Scan canceled or failed
                Toast.makeText(this, "Scan canceled", Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
