package com.lcarsos.septambictrainer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
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
    val touches: MutableList<Pair<Float, Float>> = mutableListOf()

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)
        val action = event!!.actionMasked

        when (action) {
            MotionEvent.ACTION_DOWN -> touches.add(Pair(event.getX(0), event.getY(0)))
//            MotionEvent.ACTION_UP ->
        }

        this.postInvalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = COLORS[0]
        canvas.drawCircle(250f, 250f, 55f, paint)

        for(point in touches) {
            drawCircle(canvas, point)
        }
    }

    fun drawCircle(canvas: Canvas, touch: Pair<Float, Float>) {
        canvas.drawCircle(touch.first, touch.second, 50f, paint)
    }
}