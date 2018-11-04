package ru.innopolis.imajou.f18_de_assignment.model

interface EquationVariant {

    var x0: Double
    var y0: Double

    fun getExactDifferentialSolution(x: Double): Double
    fun getFunctionValue(x: Double, y: Double): Double
    fun getEulerSolutionY(x_prev: Double, y_prev: Double, h: Double): Double
    fun getEulerImprSolutionY(x_prev: Double, y_prev: Double, h: Double): Double
    fun getRungeKuttaSolutionY(x_prev: Double, y_prev: Double, h: Double): Double
    fun updateInitialValues(x0: Double, y0: Double)
}
