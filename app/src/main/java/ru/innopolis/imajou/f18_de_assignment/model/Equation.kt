package ru.innopolis.imajou.f18_de_assignment.model

import com.jjoe64.graphview.series.DataPoint
import ru.innopolis.imajou.f18_de_assignment.model.Equation.Companion.getEulerImprSolutionY
import ru.innopolis.imajou.f18_de_assignment.model.Equation.Companion.getEulerSolutionY
import ru.innopolis.imajou.f18_de_assignment.model.Equation.Companion.getExactDifferentialSolution

object Equation {

    var eps = 0.1

    var dataExactSolution: Array<DataPoint> = exactSolution()
    var dataEulerSolution: Array<DataPoint> = eulerMethod()
    var dataEulerImprSolution: Array<DataPoint> = eulerImprMethod()

    var dataEulerError: Array<DataPoint> = eulerError()
    var dataEulerImprError: Array<DataPoint> = eulerImprError()

    const val XLow = 1.0
    const val XHigh = 2.3

    fun updateData() {
        dataExactSolution = exactSolution()
        dataEulerSolution = eulerMethod()
        dataEulerImprSolution = eulerImprMethod()

        dataEulerError = eulerError()
        dataEulerImprError = eulerImprError()
    }


    internal object Companion {
        fun getExactDifferentialSolution(x: Double): Double = x * Math.sqrt(2 * Math.log(x) + 1)

        fun getFunctionValue(x: Double, y: Double): Double = ((x / y) + (y / x))

        fun getEulerSolutionY(x_prev: Double, y_prev: Double, eps: Double): Double =
            y_prev + eps * getFunctionValue(x_prev, y_prev)

        fun getEulerImprSolutionY(x_prev: Double, y_prev: Double, x: Double, eps: Double): Double =
            y_prev + eps / 2 * (getFunctionValue(x_prev, y_prev) + getFunctionValue(
                x,
                y_prev + eps * getFunctionValue(x_prev, y_prev)
            ))
    }

    private fun exactSolution(): Array<DataPoint> {
        val list: MutableList<DataPoint> = mutableListOf()

        var x = XLow
        while (x <= XHigh) {
            list.add(DataPoint(x, getExactDifferentialSolution(x)))
            x += eps
        }

        return list.toTypedArray()
    }

    private fun eulerMethod(): Array<DataPoint> {
        val list: MutableList<DataPoint> = mutableListOf()

        list.add(DataPoint(1.0, 1.0))

        var x = XLow + eps
        while (x <= XHigh) {
            val x_prev = list.last().x
            val y_prev = list.last().y
            list.add(DataPoint(x, getEulerSolutionY(x_prev, y_prev, eps)))
            x += eps
        }
        return list.toTypedArray()
    }

    private fun eulerImprMethod(): Array<DataPoint> {
        val list: MutableList<DataPoint> = mutableListOf()

        list.add(DataPoint(1.0, 1.0))

        var x = XLow + eps
        while (x <= XHigh) {
            val x_prev = list.last().x
            val y_prev = list.last().y
            list.add(DataPoint(x, getEulerImprSolutionY(x_prev, y_prev, x, eps)))
            x += eps
        }
        return list.toTypedArray()
    }

    private fun eulerError(): Array<DataPoint> {
        val list: MutableList<DataPoint> = mutableListOf()
        for (i in 0 until dataExactSolution.size) {
            list.add(DataPoint(dataExactSolution[i].x, dataExactSolution[i].y - dataEulerSolution[i].y))
        }
        return list.toTypedArray()
    }

    private fun eulerImprError(): Array<DataPoint> {
        val list: MutableList<DataPoint> = mutableListOf()
        for (i in 0 until dataExactSolution.size) {
            list.add(DataPoint(dataExactSolution[i].x, dataExactSolution[i].y - dataEulerImprSolution[i].y))
        }
        return list.toTypedArray()
    }

}