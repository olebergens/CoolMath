package perlinnoise

import processing.core.PApplet

class PerlinNoise2D : PApplet() {

    private val increment = 0.02f

    companion object Factory {
        fun run() {
            val art = PerlinNoise2D()
            art.setSize(640, 480)
            art.runSketch()
        }
    }

    override fun setup() {
        pixelDensity(1)
    }

    override fun draw() {
        var yOffset = 0f
        loadPixels()
        for (y in 0 until height) {
            var xOffset = 0f
            for (x in 0 until width) {
                val r: Float = noise(xOffset, yOffset)
                pixels[x + y * width] = color(r * 255)
                xOffset += increment
            }
            yOffset += increment
        }
        updatePixels()
        noLoop()
    }

}

fun main() {
    PerlinNoise2D.run()
}
