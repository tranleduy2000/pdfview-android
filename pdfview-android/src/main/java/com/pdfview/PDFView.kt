package com.pdfview

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.util.AttributeSet
import androidx.annotation.ColorInt
import com.pdfview.pdf.R
import com.pdfview.subsamplincscaleimageview.ImageSource
import com.pdfview.subsamplincscaleimageview.SubsamplingScaleImageView
import java.io.File

class PDFView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    SubsamplingScaleImageView(context, attrs) {

    private var pdfFile: File? = null
    private var password: String? = null

    private var mScale: Float = 8f
    private var pageSpacing: Int = 0

    @ColorInt
    private var spacingColor: Int = 0

    init {
        setMinimumTileDpi(120)
        setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_START)

        attrs.let {
            val a = context.obtainStyledAttributes(it, R.styleable.PDFView)
            this.pageSpacing = a.getDimensionPixelSize(R.styleable.PDFView_pageSpacing, 0)
            this.spacingColor = a.getColor(R.styleable.PDFView_pageSpacingColor, Color.WHITE)
            a.recycle()
        }
    }

    fun fromAsset(assetFileName: String): PDFView {
        pdfFile = FileUtils.fileFromAsset(context, assetFileName)
        return this
    }

    fun fromFile(file: File): PDFView {
        pdfFile = file
        return this
    }

    @Throws(Exception::class)
    fun fromUri(uri: Uri): PDFView {
        pdfFile = FileUtils.fileFromUri(context, uri)
        return this
    }

    fun fromFile(filePath: String): PDFView {
        pdfFile = File(filePath)
        return this
    }

    fun password(password: String?): PDFView {
        this.password = password
        return this
    }

    fun scale(scale: Float): PDFView {
        mScale = scale
        return this
    }

    fun show(): Boolean {
        val pdfFile = pdfFile ?: return false;
        val source: ImageSource = ImageSource.uri(pdfFile.path)

        setRegionDecoderFactory {
            PDFRegionDecoder(
                view = this,
                file = pdfFile,
                scale = mScale,
                pageSpacing = pageSpacing,
                backgroundColorPdf = spacingColor
            )
        }
        setImage(source)
        return true;
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        this.recycle()
    }
}