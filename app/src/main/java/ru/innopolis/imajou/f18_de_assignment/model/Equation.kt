package ru.innopolis.imajou.f18_de_assignment.model

import com.jjoe64.graphview.series.DataPoint

class Equation(var variant: EquationVariant) {

    var fromX = 1.0     // Updated through initial value problem
    var toX = 2.3       // Updated by user input

    var steps = 10      // Updated by user

    // Base init of all solution arrays
    var solutionExact: Array<DataPoint> = Solution.exact(variant, steps, fromX, toX)
    var solutionEuler: Array<DataPoint> = Solution.euler(variant, steps, fromX, toX)
    var solutionEulerImpr: Array<DataPoint> = Solution.eulerImproved(variant, steps, fromX, toX)
    var solutionRungeKutta: Array<DataPoint> = Solution.rungeKutta(variant, steps, fromX, toX)

    // Base init of all local error arrays
    var errorEulerLocal: Array<DataPoint> = ErrorLocal.euler(variant, steps, fromX, toX)
    var errorEulerImprLocal: Array<DataPoint> = ErrorLocal.eulerImproved(variant, steps, fromX, toX)
    var errorRungeKuttaLocal: Array<DataPoint> = ErrorLocal.rungeKutta(variant, steps, fromX, toX)

    /**
     * Recalculate all solutions and errors
     */
    fun updateData() {

        if (steps <= 0 || steps > 200) return

        solutionExact = Solution.exact(variant, steps, fromX, toX)
        solutionEuler = Solution.euler(variant, steps, fromX, toX)
        solutionEulerImpr = Solution.eulerImproved(variant, steps, fromX, toX)
        solutionRungeKutta = Solution.rungeKutta(variant, steps, fromX, toX)

        errorEulerLocal = ErrorLocal.euler(variant, steps, fromX, toX)
        errorEulerImprLocal = ErrorLocal.eulerImproved(variant, steps, fromX, toX)
        errorRungeKuttaLocal = ErrorLocal.rungeKutta(variant, steps, fromX, toX)
    }
}