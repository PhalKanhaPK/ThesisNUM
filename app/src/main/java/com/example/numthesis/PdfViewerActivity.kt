package com.example.numthesis

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream

class PdfViewerActivity : AppCompatActivity() {
    private lateinit var pdfRenderer: PdfRenderer
    private lateinit var parcelFileDescriptor: ParcelFileDescriptor
    private lateinit var imageView: ImageView
    private lateinit var prevPageButton: Button
    private lateinit var nextPageButton: Button
    private lateinit var pageIndicator: TextView

    private var currentPage: PdfRenderer.Page? = null
    private var pageIndex = 0
    private var totalPageCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pdfview)

        imageView = findViewById(R.id.pdfImageView)
        prevPageButton = findViewById(R.id.prevPageButton)
        nextPageButton = findViewById(R.id.nextPageButton)
        pageIndicator = findViewById(R.id.pageIndicator)

        // Copy the PDF from assets
        val file = copyPdfFromAssetsToFile("pdf.pdf")

        // Initialize PdfRenderer
        parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        pdfRenderer = PdfRenderer(parcelFileDescriptor)
        totalPageCount = pdfRenderer.pageCount

        // Display the first page
        displayPage(pageIndex)

        // Button Listeners
        prevPageButton.setOnClickListener {
            if (pageIndex > 0) {
                pageIndex--
                displayPage(pageIndex)
            }
        }

        nextPageButton.setOnClickListener {
            if (pageIndex < totalPageCount - 1) {
                pageIndex++
                displayPage(pageIndex)
            }
        }
    }

    private fun displayPage(index: Int) {
        // Close current page if already open
        currentPage?.close()

        // Open the new page
        currentPage = pdfRenderer.openPage(index)

        // Create a bitmap to render the page
        val bitmap = Bitmap.createBitmap(
            currentPage!!.width,
            currentPage!!.height,
            Bitmap.Config.ARGB_8888
        )
        currentPage!!.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)

        // Display the bitmap in ImageView
        imageView.setImageBitmap(bitmap)

        // Update page indicator
        pageIndicator.text = "Page ${index + 1} of $totalPageCount"
    }

    private fun copyPdfFromAssetsToFile(assetName: String): File {
        val file = File(cacheDir, assetName)
        if (!file.exists()) {
            assets.open(assetName).use { inputStream ->
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        }
        return file
    }

    override fun onDestroy() {
        super.onDestroy()
        // Clean up resources
        currentPage?.close()
        pdfRenderer.close()
        parcelFileDescriptor.close()
    }
}