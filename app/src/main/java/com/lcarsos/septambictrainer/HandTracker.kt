package com.lcarsos.septambictrainer

import android.graphics.Point
import android.graphics.Rect

class HandTracker {
    private val pinky = FingerTracker(1 shl 0)
    private val ring = FingerTracker(1 shl 1)
    private val middle = FingerTracker(1 shl 2)
    private val index = FingerTracker(1 shl 3)

    private val center = FingerTracker(1 shl 4)
    private val near = FingerTracker(1 shl 5)
    private val far = FingerTracker(1 shl 6)

//    private var _value = 0

    enum class TouchType {
        NEW_FINGER,
        EXISTING_FINGER,
        BAD_TOUCH
    }

    fun getValue(): KeyerValue {
        return KeyerValue.valueOf((if (pinky.active) pinky.bitmask else 0) and
                (if (ring.active) ring.bitmask else 0) and
                (if (middle.active) middle.bitmask else 0) and
                (if (index.active) index.bitmask else 0) and
                (if (center.active) center.bitmask else 0) and
                (if (near.active) near.bitmask else 0) and
                (if (far.active) far.bitmask else 0))
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
            near.sameFinger(point) -> {
                near.fingerDown(point)
                return near
            }
            far.sameFinger(point) -> {
                far.fingerDown(point)
                return far
            }
            pinky.setupOnce(point) -> return pinky
            ring.setupOnce(point) -> return ring
            middle.setupOnce(point) -> return middle
            index.setupOnce(point) -> return index
            center.setupOnce(point) -> return center
            near.setupOnce(point) -> return near
            far.setupOnce(point) -> return far
            else -> return null
        }
    }

    fun trackedFingers(): List<FingerTracker> {
        return listOf(pinky, ring, middle, index, near, center, far).filter { i -> i.fingerBounds != null }
    }
}

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