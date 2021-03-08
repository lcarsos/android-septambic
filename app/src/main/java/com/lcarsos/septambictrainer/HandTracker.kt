package com.lcarsos.septambictrainer

import android.graphics.Point
import android.graphics.Rect

class HandTracker {
    private val pinky = FingerTracker()
    private val ring = FingerTracker()
    private val middle = FingerTracker()
    private val index = FingerTracker()
    private val center = FingerTracker()

    enum class TouchType {
        NEW_FINGER,
        EXISTING_FINGER,
        BAD_TOUCH
    }

    fun registerTouch(point: Point): FingerTracker? {
        when {
            pinky.sameFinger(point) -> {
                pinky.fingerDown(point)
                return pinky
            }
            ring.sameFinger(point) -> {
                ring.fingerDown(point)
                return ring
            }
            middle.sameFinger(point) -> {
                middle.fingerDown(point)
                return middle
            }
            index.sameFinger(point) -> {
                index.fingerDown(point)
                return index
            }
            center.sameFinger(point) -> {
                center.fingerDown(point)
                return center
            }
            else -> {
                if (pinky.setupOnce(point)) {
                    return pinky
                } else if (ring.setupOnce(point)) {
                    return ring
                } else if (middle.setupOnce(point)) {
                    return middle
                } else if (index.setupOnce(point)) {
                    return index
                } else if (center.setupOnce(point)) {
                    return center
                } else {
                    return null
                }
            }
        }
    }

    fun trackedFingers(): List<FingerTracker> {
        return listOf(pinky, ring, middle, index, center).filter { i -> i.fingerBounds != null }
    }
}

class FingerTracker {
    var fingerBounds: Rect? = null
    lateinit var centroid: Point
    var active = false
    var touches = mutableListOf<Point>()

    fun sameFinger(point: Point): Boolean {
        return if (fingerBounds != null) fingerBounds!!.contains(point.x, point.y) else false
    }

    fun setupOnce(point: Point): Boolean {
        if (fingerBounds != null) {
            return false
        }
        fingerBounds = Rect(point.x - 50, point.y - 75, point.x + 50, point.y + 75)
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