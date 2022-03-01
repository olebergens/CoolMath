package insects

import processing.core.PApplet

class Butterfly : PApplet() {

    private var a: Float = 0f
    private var b: Float = 0f
    private var t: Float = 0f

    companion object Factory {
        fun run() {
            val art = Butterfly()
            art.setSize(640, 480)
            art.runSketch()
        }
    }

    override fun settings() {
        size(640, 480, P3D)
    }

    override fun draw() {
        background(0)
        translate(width / 2f, height / 2f)
        rotateY((t % 200) * PI / 200)
        //scale(5f)
        for (i in 0 until 10000) {
            strokeWeight(1f)
            stroke(250f)
            a = processX((t + i))
            b = processY((t + i))
            point(a, b)
        }

        for (i in 0 until 5000) {
            strokeWeight(1f)
            stroke(250f)
            a = processX((t + i))
            b = processY((t + i))
            point(a, b)
        }

        for (i in 0 until 500) {
            strokeWeight(1f)
            stroke(250f)
            a = processX((t + i))
            b = processY((t + i))
            point(a, b)
        }

        for (i in 0 until 500) {
            strokeWeight(1f)
            stroke(250f)
            a = processX((t + i))
            b = processY((t + i))
            point(a, b)
        }

        for (i in 0 until 50) {
            strokeWeight(1f)
            stroke(250f)
            a = processX((t + i))
            b = processY((t + i))
            point(a, b)
        }
        t += 0.5f
    }

    private fun processX(input: Float): Float =
        40 * sin(input) * (exp(cos(input)) - 2 * cos(4 * input) - pow(sin(input / 12), 5f))

    private fun processY(input: Float): Float =
        -40 * cos(input) * (exp(cos(input)) - 2 * cos(4 * input) - pow(sin(input / 12), 5f))

}

fun main() {
    Butterfly.run()
}
