package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        this.findViewById<AppCompatButton>(R.id.gerar).setOnClickListener {
            startActivity(Intent(this, QRCodeGeneratorActivity::class.java))
        }

        this.findViewById<AppCompatButton>(R.id.ler).setOnClickListener {
            startActivity(Intent(this, QRCodeReaderActivity::class.java))
        }
    }

}
