package fractalsets

import processing.core.PApplet
import processing.event.MouseEvent

class Julia : PApplet() {


    private var angle = 0f
    private var zoom: Float = 4f

    companion object Factory {
        fun run() {
            val art = Julia()
            art.setSize(640, 480)
            art.runSketch()
        }
    }

    override fun setup() {
        background(0)
    }

    override fun draw() {
        val ca = cos(angle * PI)
        val cb = sin(angle)
        angle += 0.008f

        val w = zoom // zoom
        val h = (w * height) / width
        val minX = -w / 2
        val minY = -h / 2
        loadPixels()
        val maxIter = 100
        val maxX = minX + w
        val maxY = minY + h
        val dx: Float = (maxX - minX) / width
        val dy: Float = (maxY - minY) / height
        var y = minY

        for (j in 0 until height) {
            var x = minX
            for (i in 0 until width) {
                var a = x
                var b = y
                var n = 0
                val max = 4.0f
                var absOld = 0.0f
                var convergeNumber: Float = maxIter.toFloat()
                while (n < maxIter) {
                    val aa = sq(a)
                    val bb = sq(b)
                    val abs = sqrt(aa + bb)
                    if (abs > max) {
                        val diffLast: Float = abs - absOld
                        val diffMax: Float = max - absOld
                        convergeNumber = n + diffMax / diffLast
                        break
                    }
                    val twoAB: Float = 2 * a * b
                    a = aa - bb + ca
                    b = twoAB + cb
                    n++
                    absOld = abs
                }
                if (n == maxIter) pixels[i + j * width] = color(0)
                else {
                    val norm = map(convergeNumber, 0f, maxIter.toFloat(), 0f, 1f)
                    pixels[i + j * width] = color(map(sqrt(norm), 0f, 1f, 0f, 255f))
                }
                x += dx
            }
            y += dy
        }
        updatePixels()
    }


    override fun mouseWheel(event: MouseEvent) {
        val direction: Int = event.count
        if (direction < 0) zoom += .05.toFloat()
        if (direction > 0) zoom -= .05.toFloat()
    }


}

fun main() {
    Julia.run()
}