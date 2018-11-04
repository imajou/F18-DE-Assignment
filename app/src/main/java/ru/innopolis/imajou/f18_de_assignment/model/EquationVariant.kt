package ru.innopolis.imajou.f18_de_assignment.model

interface EquationVariant {

    var x0: Double
    var y0: Double

    fun getExactDifferentialSolution(x: Double): Double
    fun getFunctionValue(x: Double, y: Double): Double
    fun getEulerSolutionY(xi: Double, yi: Double, h: Double): Double
    fun getEulerImprSolutionY(xi: Double, yi: Double, h: Double): Double
    fun getRungeKuttaSolutionY(xi: Double, yi: Double, h: Double): Double
}
