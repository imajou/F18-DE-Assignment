package ru.innopolis.imajou.f18_de_assignment.model

import com.jjoe64.graphview.series.DataPoint

object Solution {

    /**
     * Point array for exact solution
     * @param variant Giver equation variant
     * @param steps How many to parse
     * @param fromX starting x value
     * @param toX ending x value
     */
    fun exact(variant: EquationVariant, steps: Int, fromX: Double, toX: Double): Array<DataPoint> {
        val list: MutableList<DataPoint> = mutableListOf()

        val h = (toX - fromX) / steps
        var x = fromX
        for (i in 0..steps) {
            list.add(DataPoint(x, variant.getExactDifferentialSolution(x)))
            x += h
        }

        return list.toTypedArray()
    }

    /**
     * Point array for Euler method
     * @param variant Giver equation variant
     * @param steps How many to parse
     * @param fromX starting x value
     * @param toX ending x value
     */
    fun euler(variant: EquationVariant, steps: Int, fromX: Double, toX: Double): Array<DataPoint> {
        val list: MutableList<DataPoint> = mutableListOf()

        list.add(DataPoint(variant.x0, variant.y0))

        val h = (toX - fromX) / steps
        var x = fromX + h
        for (i in 0..steps) {
            val x_prev = list.last().x
            val y_prev = list.last().y
            list.add(DataPoint(x, variant.getEulerSolutionY(x_prev, y_prev, h)))
            x += h
        }
        return list.toTypedArray()
    }

    /**
     * Point array for improved Euler method
     * @param variant Giver equation variant
     * @param steps How many to parse
     * @param fromX starting x value
     * @param toX ending x value
     */
    fun eulerImproved(variant: EquationVariant, steps: Int, fromX: Double, toX: Double): Array<DataPoint> {
        val list: MutableList<DataPoint> = mutableListOf()

        list.add(DataPoint(variant.x0, variant.y0))

        val h = (toX - fromX) / steps
        var x = fromX + h
        for (i in 0..steps) {
            val x_prev = list.last().x
            val y_prev = list.last().y
            list.add(DataPoint(x, variant.getEulerImprSolutionY(x_prev, y_prev, h)))
            x += h
        }
        return list.toTypedArray()
    }

    /**
     * Point array for Runge-Kutta method
     * @param variant Giver equation variant
     * @param steps How many to parse
     * @param fromX starting x value
     * @param toX ending x value
     */
    fun rungeKutta(variant: EquationVariant, steps: Int, fromX: Double, toX: Double): Array<DataPoint> {
        val list: MutableList<DataPoint> = mutableListOf()

        list.add(DataPoint(variant.x0, variant.y0))

        val h = (toX - fromX) / steps
        var x = fromX + h
        for (i in 0..steps) {
            val x_prev = list.last().x
            val y_prev = list.last().y
            list.add(DataPoint(x, variant.getRungeKuttaSolutionY(x_prev, y_prev, h)))
            x += h
        }
        return list.toTypedArray()
    }
}