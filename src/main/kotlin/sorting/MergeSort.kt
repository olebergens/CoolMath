package sorting

import processing.core.PApplet

class MergeSort : PApplet() {

    private var values: ArrayList<Int>? = null

    private var t: Thread = object : Thread() {
        override fun run() {
            mergeSort(values!!)
        }
    }

    companion object Factory {
        fun run() {
            val art = MergeSort()
            art.setSize(640, 480)
            art.runSketch()
        }
    }

    override fun setup() {
        values = ArrayList(width)
        for (i in values!!.indices)
            values!![i] = random(0f, height.toFloat()).toInt()
        t.start()
    }

    override fun draw() {
        background(0)
        for (i in values!!.indices) {
            stroke(128f)
            line(i.toFloat(), height.toFloat(), i.toFloat(), (height - values!![i]).toFloat())
        }
    }

    fun mergeSort(values: MutableList<Int>): MutableList<Int> {
        if (values.size <= 1) return values
        val mid = values.size / 2
        val left = values.subList(0, mid)
        val right = values.subList(mid, values.size)
        return merge(mergeSort(left), mergeSort(right))
    }

    private fun merge(left: MutableList<Int>, right: MutableList<Int>): MutableList<Int> {
        var iLeft = 0
        var iRight = 0
        val new: MutableList<Int> = mutableListOf()
        while (iLeft < left.count() && iRight < right.count()) {
            if (left[iLeft] <= right[iRight]) {
                new.add(left[iLeft])
                iLeft++
            } else {
                new.add(right[iRight])
                iRight++
            }
        }
        while (iLeft < left.size) {
            new.add(left[iLeft])
            iLeft++
        }

        while (iRight < right.size) {
            new.add(right[iRight])
            iRight++
        }
        return new
    }

}

fun main() {
    MergeSort.run()
}