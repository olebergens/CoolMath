package ulam

import processing.core.PApplet

class UlamSpiral : PApplet() {

    private var x = 0f
    private var y = 0f
    private var currentState = 0
    private var turnCounter = 1
    private var totalSteps = 0
    private var numSteps = 1
    private var step = 1
    private var stepSize = 2

    companion object Factory {
        fun run() {
            val art = UlamSpiral()
            art.setSize(600, 600)
            art.runSketch()
        }
    }

    override fun setup() {
        val cols = width / stepSize
        val rows = height / stepSize
        totalSteps = cols * rows
        x = (width / 2).toFloat()
        y = (height / 2).toFloat()
        background(0)
    }

    override fun draw() {
        // while -> "instant" drawing of points
        while (step <= totalSteps) {
            if (prime(step)) {
                fill(255)
                stroke(255)
                point(x, y)
            }
            changeState(currentState)
            update()
            step++
        }
        // this thing does an "animation" while drawing
        //if (step > totalSteps) noLoop()
    }

    private fun changeState(state: Int) {
        when (state) {
            0 -> x += stepSize
            1 -> y -= stepSize
            2 -> x -= stepSize
            3 -> y += stepSize
        }
    }

    private fun update() {
        if (step % numSteps == 0) {
            currentState = (currentState + 1) % 4
            turnCounter++
            if (turnCounter % 2 == 0) numSteps++
        }
    }

    private fun prime(value: Int): Boolean {
        if (value == 1) return false
        for (i in 2..sqrt(value.toFloat()).toInt() step 1) if (value % i == 0) return false
        return true
    }
}

fun main() {
    UlamSpiral.run()
}