package mandelbrot

import processing.core.PApplet


class Mandelbrot : PApplet() {


    companion object Factory {
        fun run() {
            val art = Mandelbrot()
            art.setSize(500, 500)
            art.runSketch()
        }
    }

    override fun setup() {
        pixelDensity = 1
        background(0)
        //noLoop()
    }

    override fun draw() {
        val w = 4f
        val h = (w * height) / width
        val xmin = -w/2
        val ymin = -h/2
        loadPixels()
        val maxIter = 100
        val xmax = xmin + w
        val ymax = ymin + h
        val dx: Float = (xmax - xmin) / width
        val dy: Float = (ymax - ymin) / height
        var y = ymin

        for (j in 0 until height) {
            var x = xmin
            for (i in 0 until width) {
                var a = x
                var b = y
                var n = 0
                val max = 4.0f
                var absOld = 0.0f
                var convergeNumber: Float = maxIter.toFloat()
                while (n < maxIter) {
                    val aa = a * a
                    val bb = b * b
                    val abs = sqrt(aa + bb)
                    if (abs > max) {
                        val diffLast: Float = abs - absOld
                        val diffMax: Float = max - absOld
                        convergeNumber = n + diffMax / diffLast
                        break
                    }
                    val twoab: Float = 2 * a * b
                    a = aa - bb + x
                    b = twoab + y
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

}

fun main() {
    Mandelbrot.run()
}

