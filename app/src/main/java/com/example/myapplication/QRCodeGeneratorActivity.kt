package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix

class QRCodeGeneratorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qrcode_generator_activity)

        this.findViewById<AppCompatButton>(R.id.gerar).setOnClickListener {
            val editor = this.findViewById<AppCompatEditText>(R.id.editor)
            val textToEncode = editor.text.toString()

            val width = 500
            val height = 500

            val bitmap = generateQRCode(textToEncode, width, height)

            val imageView = findViewById<AppCompatImageView>(R.id.qrCodeImageView)
            imageView.setImageBitmap(bitmap)
        }



    }

    private fun generateQRCode(text: String, width: Int, height: Int): Bitmap? {
        try {
            val bitMatrix: BitMatrix =
                MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height)

            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) 0xFF000000.toInt() else 0xFFFFFFFF.toInt())
                }
            }

            return bitmap

        } catch (e: WriterException) {
            e.printStackTrace()
        }

        return null
    }
}
