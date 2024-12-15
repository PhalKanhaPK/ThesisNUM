package com.example.numthesis

import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PdfViewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pdf_viewer)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve the PDF file name from the Intent
        val fileName = intent.getStringExtra("PDF_FILE_NAME")

        if (fileName != null) {
            // Load PDF from assets
            val webView: WebView = findViewById(R.id.pdfWebView)
            webView.settings.javaScriptEnabled = true
            webView.loadUrl("file:///android_asset/$fileName")
        } else {
            Toast.makeText(this, "File not found!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}