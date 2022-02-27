package math.ulamspiral

import javafx.collections.FXCollections
import math.CMath
import java.util.logging.Level

data class UlamActor(val ux: Int, val uy: Int, val upx: Int, val upy: Int)

class UlamSpiral(width: Double, height: Double, private var stepSize: Int) : CMath(
    name = "Ulam Spiral",
    author = "Stanisław Marcin Ulam",
    description = "The Ulam spiral is a simple method to represent prime numbers graphically. This method was discovered in 1963 by a Polish mathematician."
) {
    private var x: Int
    private var y: Int
    private var px: Int
    private var py: Int

    private var cols: Double
    private var rows: Double

    private var currentState: Int
    private var turnCounter = 1
    private var totalSteps: Int
    private var numSteps = 1
    private var step = 1

    val actors = FXCollections.observableArrayList<UlamActor>()!!

    init {
        logger.log(Level.INFO, "INIT ULAM-SPIRAL")
        x = (width / 2).toInt()
        y = (height / 2).toInt()
        px = x
        py = y
        cols = width / stepSize
        rows = height / stepSize
        totalSteps = (cols * rows).toInt()
        currentState = 1
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
            this.currentState = (currentState + 1) % 4
            turnCounter++
            if (turnCounter % 2 == 0) numSteps++
        }
    }

    fun run() {
        logger.log(Level.INFO, "entering ulam spiral loop")
        do {
            if (prime(step)) actors.add(UlamActor(this.x, this.y, px, py))
            px = x
            py = y
            changeState(currentState)
            update()
            step++
        } while (step < totalSteps)
        logger.log(Level.INFO, "leaving ulam spiral loop")
    }

}