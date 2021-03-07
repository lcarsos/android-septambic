package com.lcarsos.septambictrainer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

val COLORS = listOf(
    Color.parseColor("#FF33B5E5"),
    Color.parseColor("#FFAA66CC"),
    Color.parseColor("#FF99CC00"),
    Color.parseColor("#FFFFBB33"),
    Color.parseColor("#FFFF4444"),
    Color.parseColor("#FF0099CC"),
    Color.parseColor("#FF9933CC"),
    Color.parseColor("#FF669900"),
    Color.parseColor("#FFFF8800"),
    Color.parseColor("#FFCC0000"),
)

class SeptambicView(context: Context, attrs: AttributeSet): View(context, attrs) {

    val paint: Paint = Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        val BLACK = Color.parseColor("#FFFF0000")
        val DARKBLUE = -0x559934
        val BLUE = Color.parseColor("#AA66CCFF")
        val color = 0x00_ff_ff
        paint.setColor(DARKBLUE)
        canvas.drawCircle(100f, 150f, 50f, paint)
        paint.color = BLACK
        canvas.drawCircle(225f, 225f, 10f, paint)
        paint.color = BLUE
        canvas.drawCircle(250f, 250f, 55f, paint)


    }
}