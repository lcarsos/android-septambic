package com.lcarsos.septambictrainer

import android.graphics.Point
import android.graphics.Rect

class FingerTracker(val bitmask: Int) {
    var fingerBounds: Rect? = null
    lateinit var centroid: Point
    var active = false
    var touches = mutableListOf<Point>()

    fun sameFinger(point: Point): Boolean {
        return fingerBounds?.contains(point.x, point.y) ?: false
    }

    fun setupOnce(point: Point): Boolean {
        if (fingerBounds != null) {
            return false
        }
        fingerBounds = Rect(point.x - 50,
            point.y - 75,
            point.x + 50,
            point.y + 75)
        centroid = point

        fingerDown(point)
        return true
    }

    fun fingerDown(point: Point) {
        touches.add(point)
        active = true
    }

    fun fingerUp() {
        active = false
    }
}