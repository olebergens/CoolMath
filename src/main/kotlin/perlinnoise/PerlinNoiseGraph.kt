package perlinnoise

import processing.core.PApplet

class PerlinNoiseGraph : PApplet() {

    private var start = 0f
    private var increment = 0.02f

    companion object Factory {
        fun run() {
            val art = PerlinNoiseGraph()
            art.setSize(640, 480)
            art.runSketch()
        }
    }

    override fun draw() {
        background(0)
        stroke(255)
        noFill()
        beginShape()
        var xOffset = start
        for (x in 0 until width) {
            stroke(255)
            val y = noise(xOffset) * height
            vertex(x.toFloat(), y)
            xOffset += increment
        }
        endShape()
        start += increment
    }

}

fun main() {
    PerlinNoiseGraph.run()
}