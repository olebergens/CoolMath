package sorting

import processing.core.PApplet

class QuickSort : PApplet() {

    private var values = ArrayList<Float>(width)

    private val thread = Thread {
        quicksort(values, 0, values.size - 1)
    }

    companion object Factory {
        fun run() {
            val art = QuickSort()
            art.setSize(640, 480)
            art.runSketch()
        }
    }

    override fun setup() {
        for (i in 0 until values.size)
            values[i] = random(0f, height.toFloat())
        thread.start()
    }

    override fun draw() {
        background(0f)
        for (i in 0 until values.size) {
            stroke(255f)
            line(i.toFloat(), height.toFloat(), i.toFloat(), height - values[i])
        }
    }

    fun swap(array: ArrayList<Float>, a: Int, b: Int) {
        val temp = array[a]
        array[a] = array[b]
        array[b] = temp
        Thread.sleep(10)
        redraw()
    }

    fun partition(array: ArrayList<Float>, start: Int, end: Int): Int {
        var pivotIndex = start
        val pivotValue = array[end]
        for (i in start until end)
            if (array[i] < pivotValue)
                swap(array, i, pivotIndex++)
        swap(array, pivotIndex, end)
        return pivotIndex
    }

    fun quicksort(array: ArrayList<Float>, start: Int, end: Int) {
        if (start < end) {
            val index = partition(array, start, end)
            quicksort(array, start, index - 1)
            quicksort(array, index + 1, end)
        }
    }

}

fun main() {
    QuickSort.run()
}