package lorenz

import processing.core.PApplet
import me.altered.math.*

class LorenzAttractor : PApplet() {
    private var x = 0.01
    private var y = 0.0
    private var z = 0.0
    private val sigma = 10
    private val beta = 28
    private val rho = 8 / 3f

    private val points = mutableListOf<Vector>()

    companion object Factory {
        fun run() {
            val art = LorenzAttractor()
            art.setSize(640, 480)
            art.runSketch()
        }
    }

    override fun settings() {
        size(640, 480, P3D)
    }

    override fun draw() {
        background(0)
        val dt = 0.01
        val dx = sigma * (y - x) * dt
        val dy = (x * (beta - z) - y) * dt
        val dz = (x * y - rho * z) * dt
        x += dx
        y += dy
        z += dz

        points.add(Vector(x, y, z))
        translate(0f, 0f, -80f)
        val camX = map(mouseX.toFloat(), 0f, width.toFloat(), -200f, 200f)
        val camY = map(mouseY.toFloat(), 0f, height.toFloat(), -200f, 200f)
        camera(camX, camY, height / 2f / tan((PI * 30) / 180), 0f, 0f, 0f, 0f, 1f, 0f)

        scale(4f)
        stroke(255)
        noFill()
        var hu = 0
        beginShape()
        for (v in points) {
            stroke(hu.toFloat(), 255f, 255f)
            vertex(v.x.toFloat(), v.y.toFloat(), v.z.toFloat())
            hu++
            if (hu > 255) hu = 0
        }
        endShape()
    }

}

fun main() {
    LorenzAttractor.run()
}