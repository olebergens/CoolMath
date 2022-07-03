package sorting

import processing.core.PApplet

class QuickSort : PApplet() {

    private var values: FloatArray? = null

    private var t: Thread = object : Thread() {
        override fun run() {
            quicksort(values!!, 0, values!!.size - 1)
        }
    }

    companion object Factory {
        fun run() {
            val art = QuickSort()
            art.setSize(640, 480)
            art.runSketch()
        }
    }

    override fun setup() {
        values = FloatArray(width)
        for (i in values!!.indices)
            values!![i] = random(0f, height.toFloat())
        t.start()
    }

    override fun draw() {
        background(0)
        for (i in values!!.indices) {
            stroke(128f)
            line(i.toFloat(), height.toFloat(), i.toFloat(), height - values!![i])
        }
    }

    private fun quicksort(array: FloatArray, start: Int, end: Int) {
        if (start < end) {
            // verwende mot nicht, wenn nicht median-of-three genommen werden soll!
            val mot = mot(array, start, end)
            val index = partition(array, start, end, mot)
            quicksort(array, start, index - 1)
            quicksort(array, index + 1, end)
        }
    }

    private fun mot(array: FloatArray, start: Int, end: Int): Float {
        val mid = (start + end) / 2
        if (array[start] > array[mid]) swap(array, start, mid)
        if (array[start] > array[end]) swap(array, start, end)
        if (array[mid] > array[mid]) swap(array, mid, end)
        swap(array, mid, end - 1)
        return array[end - 1]
    }

    private fun partition(array: FloatArray, start: Int, end: Int, mot: Float): Int {
        var ptrL = start
        var ptrR = end - 1
        while (true) {
            while (array[++ptrL] < mot); // scan größerer wert
            while (array[--ptrR] > mot); // scan kleinerer wert
            if (ptrL >= ptrR) break // pointer kreuzen sich
            else swap(array, ptrL, ptrR)
        }
        swap(array, ptrL, end - 1)
        return ptrL
    }


    private fun partition(array: FloatArray, start: Int, end: Int): Int {
        var pivotIndex = start
        val pivotValue = array[end]
        for (i in start until end) {
            if (array[i] < pivotValue) {
                swap(array, i, pivotIndex++)
            }
        }
        swap(array, pivotIndex, end)
        return pivotIndex
    }

    private fun swap(array: FloatArray, a: Int, b: Int) {
        val temp = array[a]
        array[a] = array[b]
        array[b] = temp
        Thread.sleep(3)
    }


}

fun main() {
    QuickSort.run()
}