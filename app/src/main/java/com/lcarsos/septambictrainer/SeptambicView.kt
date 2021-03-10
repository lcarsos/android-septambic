package com.lcarsos.septambictrainer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
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

    val bubblePaint = Paint()
    val blackPaint = Paint()
    val greenPaint = Paint()
    val hand = HandTracker()

    val actionIndexToFingerMap = HashMap<Int, FingerTracker?>()

    init {
        bubblePaint.color = COLORS[0]
        blackPaint.color = Color.BLACK
        blackPaint.style = Paint.Style.STROKE
        greenPaint.color = Color.GREEN
        greenPaint.style = Paint.Style.STROKE
    }

    /**
     * Note to all brain damaged people out there:
     * Every time a motion event occurs, you get all of the data for all of the touch points, not
     * just the one that caused this event. Because of that, there is a "pointer" indirection which
     * is a little bit confusing because "pointer" is a term for memory management, but in this case
     * it means the meat sausages poking the screen.
     *
     * actionIndex is the position in the array of pointer data. Array is the key word here.
     * actionIndex === pointerIndex
     * pointerId is the ID number of the finger on the screen. They use a real array to map to the
     * sparse array they want you to think of.
     *
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)
        val action = event!!.actionMasked
        val actionIndex = event.actionIndex
        val pointerId = event.getPointerId(actionIndex)

        when (action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val touch = Point(event.getX(actionIndex).toInt(), event.getY(actionIndex).toInt())
                actionIndexToFingerMap[pointerId] = hand.registerTouch(touch)
                this.postInvalidate()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                actionIndexToFingerMap[pointerId]?.fingerUp()
                actionIndexToFingerMap[pointerId] = null
                this.postInvalidate()
            }
            else -> return false
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val fingers = hand.trackedFingers()
        for ((index, finger) in fingers.withIndex()) {
            for (touch in finger.touches) {
                drawCircle(canvas, touch, COLORS[index])
            }
        }
        for (finger in fingers) {
            canvas.drawRect(finger.fingerBounds!!, if (finger.active) greenPaint else blackPaint)
        }
    }

    fun drawCircle(canvas: Canvas, touch: Point, color: Int) {
        bubblePaint.color = color
        canvas.drawCircle(touch.x.toFloat(), touch.y.toFloat(), 30f, bubblePaint)
    }
}