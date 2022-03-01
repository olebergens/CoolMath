package perlinnoise

import perlinnoise.particle.Particle
import processing.core.PApplet
import processing.core.PVector


open class PerlinNoiseFlowField : PApplet() {

    private var increment = 0.1f
    private var scale = 5f
    private var zOffset = 0f

    private var cols = floor((width / scale * 2))
    private var rows = floor((height / scale * 2))

    private var particles = mutableListOf<Particle>()
    private var flowFields = mutableListOf<PVector>()

    companion object Factory {
        fun run() {
            val art = PerlinNoiseFlowField()
            art.setSize(640, 480)
            art.runSketch()
        }
    }

    override fun setup() {
        for (i in 0 until 300)
            particles.add(Particle(width, height, cols.toFloat(), scale))
        background(51)
    }

    override fun draw() {
        noFill()
        var yOffset = 0f
        for (y in 0 until rows) {
            var xOffset = 0f
            for (x in 0 until cols) {
                val index = x + y * cols
                val angle = noise(xOffset, yOffset, zOffset) * TWO_PI * 2
                val v = PVector.fromAngle(angle)
                v.setMag(1f)
                flowFields.add(index, v)
                stroke(0f, 50f)
                xOffset += increment
            }
            yOffset += increment
            zOffset += 0.0003f
        }
        for (i in 0 until particles.size) {
            particles[i].follow(flowFields)
            particles[i].update()
            particles[i].edges()
            drawPointOutOfVector(particles[i])
        }
    }

    private fun drawPointOutOfVector(particle: Particle) {
        stroke(255f, 10f)
        strokeWeight(1f)
        line(particle.position.x, particle.position.y, particle.prevPos.x, particle.prevPos.y)
        particle.updatePrev()
    }

}

fun main() {
    PerlinNoiseFlowField.run()
}