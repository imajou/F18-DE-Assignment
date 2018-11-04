package ru.innopolis.imajou.f18_de_assignment.model

import com.jjoe64.graphview.series.DataPoint

object ErrorLocal {

    /**
     * Point array for Euler method local error
     * @param variant Giver equation variant
     * @param steps How many to parse
     * @param fromX starting x value
     * @param toX ending x value
     */
    fun euler(variant: EquationVariant, steps: Int, fromX: Double, toX: Double): Array<DataPoint> {
        val list: MutableList<DataPoint> = mutableListOf()
        val solutionExact = Solution.exact(variant, steps, fromX, toX)
        val solutionEuler = Solution.euler(variant, steps, fromX, toX)
        for (i in 0 until solutionExact.size) {
            list.add(DataPoint(solutionExact[i].x, solutionExact[i].y - solutionEuler[i].y))
        }
        return list.toTypedArray()
    }

    /**
     * Point array for improved Euler method local error
     * @param variant Giver equation variant
     * @param steps How many to parse
     * @param fromX starting x value
     * @param toX ending x value
     */
    fun eulerImproved(variant: EquationVariant, steps: Int, fromX: Double, toX: Double): Array<DataPoint> {
        val errorEulerImproved: MutableList<DataPoint> = mutableListOf()
        val solutionExact = Solution.exact(variant, steps, fromX, toX)
        val solutionEulerImproved = Solution.eulerImproved(variant, steps, fromX, toX)
        for (i in 0 until solutionExact.size) {
            errorEulerImproved.add(DataPoint(solutionExact[i].x, solutionExact[i].y - solutionEulerImproved[i].y))
        }
        return errorEulerImproved.toTypedArray()
    }

    /**
     * Point array for Runge-Kutta method local error
     * @param variant Giver equation variant
     * @param steps How many to parse
     * @param fromX starting x value
     * @param toX ending x value
     */
    fun rungeKutta(variant: EquationVariant, steps: Int, fromX: Double, toX: Double): Array<DataPoint> {
        val list: MutableList<DataPoint> = mutableListOf()
        val solutionExact = Solution.exact(variant, steps, fromX, toX)
        val solutionRungeKutta = Solution.rungeKutta(variant, steps, fromX, toX)
        for (i in 0 until solutionExact.size) {
            list.add(DataPoint(solutionExact[i].x, solutionExact[i].y - solutionRungeKutta[i].y))
        }
        return list.toTypedArray()
    }
}