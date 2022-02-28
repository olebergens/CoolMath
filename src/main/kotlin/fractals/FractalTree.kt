package fractals

import processing.core.PApplet


class FractalTree : PApplet() {
    private var start = 200f
    private var angleBetweenBranches = 0f
    private var angleOfCenterBranch = 0f

    companion object Factory {
        fun run() {
            val art = FractalTree()
            art.setSize(640, 480)
            art.runSketch()
        }
    }

    override fun setup() {
        stroke(255)
    }

    override fun draw() {
        background(0)
        translate((width / 2).toFloat(), height / 2 + start)
        angleBetweenBranches = map(mouseY.toFloat(), 0f, height.toFloat(), 0f, PI) //angle between branches
        angleOfCenterBranch = map(mouseX.toFloat(), 0f, width.toFloat(), -HALF_PI, HALF_PI) //angle of center branch from center
        fractal(start)
    }

    private fun fractal(depth: Float) {
        if (depth > 1) {

            //rotates only branches after initial "trunk"
            if (depth != start) {
                rotate(angleOfCenterBranch)
            }
            translate(0f, -depth)
            line(0f, 0f, 0f, depth)
            rotate(-angleBetweenBranches)
            for (i in 0..2) {
                pushMatrix()
                fractal(depth / 2)
                popMatrix()
                rotate(angleBetweenBranches)
            }
        }
    }

}

fun main() {
    FractalTree.run()
}
