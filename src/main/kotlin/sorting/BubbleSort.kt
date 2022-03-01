package sorting

import processing.core.PApplet

class BubbleSort : PApplet() {

    private var values: IntArray? = null
    private var i = 0

    companion object Factory {
        fun run() {
            val art = BubbleSort()
            art.setSize(640, 480)
            art.runSketch()
        }
    }

    override fun setup() {
        values = IntArray(width)
        for (i in values!!.indices) values!![i] = random(height.toFloat()).toInt()
    }

    override fun draw() {
        background(0)
        if (i < values!!.size)
            for (j in 0 until values!!.size - i - 1) {
                val a = values!![j]
                val b = values!![j + 1]
                if (a > b) swap(values!!, j, j + 1)
            }
        else noLoop()
        i++
        for (i in values!!.indices) {
            stroke(128)
            line(i.toFloat(), height.toFloat(), i.toFloat(), (height - values!![i]).toFloat())
        }
    }

    private fun swap(array: IntArray, a: Int, b: Int) {
        val temp = array[a]
        array[a] = array[b]
        array[b] = temp
    }

}

fun main() {
    BubbleSort.run()
}