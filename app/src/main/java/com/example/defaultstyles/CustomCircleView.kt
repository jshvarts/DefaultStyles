package com.example.defaultstyles

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

private const val DEFAULT_STYLE_ATTR = R.attr.circleViewStyle
private const val DEFAULT_STYLE_RES = R.style.Widget_DefaultStyles_CustomCircleView

private const val DEFAULT_HAS_BORDER = false

class CustomCircleView : View {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, DEFAULT_STYLE_ATTR)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : this(context, attrs, defStyleAttr, DEFAULT_STYLE_ATTR)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        val ta = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomCircleView,
            DEFAULT_STYLE_ATTR,
            DEFAULT_STYLE_RES
        )
        hasBorder = ta.getBoolean(R.styleable.CustomCircleView_hasBorder, DEFAULT_HAS_BORDER)
        ta.recycle()
    }

    private var hasBorder: Boolean = DEFAULT_HAS_BORDER

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.LTGRAY
        style = Paint.Style.FILL
    }

    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        // circle border width in pixels
        strokeWidth = 10.0f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val size = width

        // draw the circle
        val radius = size / 2f
        canvas.drawCircle(size / 2f, size / 2f, radius, paint)

        if (hasBorder) {
            // draw the border around the circle
            canvas.drawCircle(
                size / 2f,
                size / 2f,
                radius - borderPaint.strokeWidth / 2f,
                borderPaint
            )
        }
    }
}
