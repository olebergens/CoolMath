package perlinnoise.particle

import processing.core.PVector
import kotlin.math.floor
import kotlin.random.Random

class Particle(var width: Int, var height: Int, var cols: Float, var scale: Float) {
    var position: PVector = PVector(Random.nextInt(width).toFloat(), Random.nextInt(height).toFloat())
    var prevPos: PVector = this.position.copy()

    private var velocity: PVector = PVector(0f, 0f)
    private var accelaration: PVector = PVector(0f, 0f)
    private var maxSpeed = 4f

    fun update() {
        velocity.add(accelaration)
        velocity.limit(maxSpeed)
        position.add(velocity)
        accelaration.mult(0f)
    }

    fun follow(vectors: MutableList<PVector>) {
        val x = floor(position.x / scale)
        val y = floor(position.y / scale)
        var index = (x - 1) + ((y - 1) * cols)
        index -= 1
        if (index > vectors.size || index < 0) index = (vectors.size - 1).toFloat()
        val force: PVector = vectors[index.toInt()]
        force(force)
    }

    private fun force(force: PVector) {
        accelaration.add(force)
    }

    fun updatePrev() {
        prevPos.x = position.x
        prevPos.y = position.y
    }

    fun edges() {
        if (position.x > width) {
            position.x = 0f
            updatePrev()
        }
        if (position.x < 0) {
            position.x = width.toFloat()
            updatePrev()
        }
        if (position.y > height) {
            position.y = 0f
            updatePrev()
        }
        if (position.y < 0) {
            position.y = height.toFloat()
            updatePrev()
        }
    }


}