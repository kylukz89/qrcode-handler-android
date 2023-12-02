package com.example.myapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView

class QRCodeReaderActivity: AppCompatActivity() {

    private lateinit var barcodeView: DecoratedBarcodeView

    private val cameraPermissionCode = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qrcode_reader_activity)

        barcodeView = findViewById(R.id.barcodeScannerViewwww)

        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            setupBarcodeScanner()
        } else {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), cameraPermissionCode)
        }
    }

    private fun setupBarcodeScanner() {
        barcodeView.decodeContinuous(object : BarcodeCallback {

            override fun barcodeResult(result: BarcodeResult?) {
                result?.let {
                    val text = it.text
                    showToast("Código QR lido: $text")
                }
            }

            override fun possibleResultPoints(resultPoints: List<ResultPoint>?) {
                // Lógica adicional, se necessário
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == cameraPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupBarcodeScanner()
            } else {
                showToast("Permissão da câmera negada. O aplicativo não pode funcionar corretamente.")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }
}
