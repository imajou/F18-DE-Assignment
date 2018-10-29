package ru.innopolis.imajou.f18_de_assignment.model

import com.jjoe64.graphview.series.DataPoint

object Equation {

    var eps = 0.1

    const val XLow = 1.0
    const val XHigh = 2.3

    fun exactSolution(): Array<DataPoint> {
        val list: MutableList<DataPoint> = mutableListOf()

        var i = XLow
        while (i <= XHigh) {
            list.add(DataPoint(i, getExactDifferentialSolution(i)))
            i += eps
        }

        return list.toTypedArray()
    }

    private fun getExactDifferentialSolution(x: Double): Double {
        return x * Math.sqrt(2 * Math.log(x) + 1)
    }

    private fun getFunctionValue(x: Double, y: Double): Double {
        return ((x / y) + (y / x))
    }

    fun eulerMethod(): Array<DataPoint> {
        val list: MutableList<DataPoint> = mutableListOf()

        list.add(DataPoint(1.0, 1.0))

        var i = XLow + eps
        while (i <= XHigh) {
            list.add(DataPoint(i, list.last().y + eps * getFunctionValue(list.last().x, list.last().y)))
            i += eps
        }
        return list.toTypedArray()
    }

    fun improvedEulerMethod(): Array<DataPoint> {
        val list: MutableList<DataPoint> = mutableListOf()

        list.add(DataPoint(1.0, 1.0))

        var x = XLow + eps
        while (x <= XHigh) {
            val x_prev = list.last().x
            val y_prev = list.last().y
            list.add(DataPoint(x, y_prev + eps / 2 * (getFunctionValue(x_prev, y_prev) + getFunctionValue(x, y_prev + eps * getFunctionValue(x_prev, y_prev)))))
            x+= eps
        }
        return list.toTypedArray()
    }

}