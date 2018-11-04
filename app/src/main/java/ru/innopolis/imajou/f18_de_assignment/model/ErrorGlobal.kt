package ru.innopolis.imajou.f18_de_assignment.model

import com.jjoe64.graphview.series.DataPoint

object ErrorGlobal {

    var fromSteps = 1   // Predefined lower bound for steps
    var toSteps = 100   // Predefined higher bound for steps

    /**
     * Point array for global error for Euler method
     * @param variant Giver equation variant
     * @param fromX starting x value for equation
     * @param toX ending x value for equation
     */
    fun euler(variant: EquationVariant, fromX: Double, toX: Double): Array<DataPoint> {
        val list: MutableList<DataPoint> = mutableListOf()
        for (steps in fromSteps..toSteps) {
            list.add(DataPoint(steps.toDouble(), ErrorLocal.euler(variant, steps, fromX, toX).maxBy { it.y }!!.y))
        }
        return list.toTypedArray()
    }

    /**
     * Point array for global error for improved Euler method
     * @param variant Giver equation variant
     * @param fromX starting x value for equation
     * @param toX ending x value for equation
     */
    fun eulerImproved(variant: EquationVariant, fromX: Double, toX: Double): Array<DataPoint> {
        val list: MutableList<DataPoint> = mutableListOf()
        for (steps in fromSteps..toSteps) {
            list.add(DataPoint(steps.toDouble(), ErrorLocal.eulerImproved(variant, steps, fromX, toX).maxBy { it.y }!!.y))
        }
        return list.toTypedArray()
    }

    /**
     * Point array for global error for Runge-Kutta method
     * @param variant Giver equation variant
     * @param fromX starting x value for equation
     * @param toX ending x value for equation
     */
    fun rungeKutta(variant: EquationVariant, fromX: Double, toX: Double): Array<DataPoint> {
        val list: MutableList<DataPoint> = mutableListOf()
        for (steps in fromSteps..toSteps) {
            list.add(DataPoint(steps.toDouble(), ErrorLocal.rungeKutta(variant, steps, fromX, toX).maxBy { it.y }!!.y))
        }
        return list.toTypedArray()
    }

}