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

        var i = XLow + eps
        while (i <= XHigh) {
            list.add(DataPoint(i, list.last().y + (eps * getFunctionValue(list.last().x + eps / 2, list.last().y + eps * getFunctionValue(list.last().x, list.last().y) / 2))))
            i+= eps
        }
        return list.toTypedArray()
    }


}