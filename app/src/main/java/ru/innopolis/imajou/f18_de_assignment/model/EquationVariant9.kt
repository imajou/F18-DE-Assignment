package ru.innopolis.imajou.f18_de_assignment.model

class EquationVariant9(override var x0: Double, override var y0: Double) : EquationVariant {

    /**
     * Get exact solution by analythical method for the problem
     * @param x point to calculate
     */
    override fun getExactDifferentialSolution(x: Double): Double = (if ((x0 * y0) > 0) (1) else (-1)) * x *
            Math.sqrt((y0 * y0) / (x0 * x0) - 2 * Math.log(x0) + 2 * Math.log(x))

    /**
     * Get function value
     * @param x point to calculate
     */
    override fun getFunctionValue(x: Double, y: Double): Double = ((x / y) + (y / x))

    /**
     * Get approximation with Euler method for the problem
     * @param xi
     * @param yi
     * @param h difference for step
     */
    override fun getEulerSolutionY(xi: Double, yi: Double, h: Double): Double =
        yi + h * getFunctionValue(xi, yi)

    /**
     * Get approximation with improved Euler method for the problem
     * @param xi
     * @param yi
     * @param h difference for step
     */
    override fun getEulerImprSolutionY(xi: Double, yi: Double, h: Double): Double {
        val k1 = getFunctionValue(xi, yi)
        val k2 = getFunctionValue(xi + h, yi + h * k1)
        return yi + h * (k1 + k2) / 2
    }

    /**
     * Get approximation with Runge-Kutta method for the problem
     * @param xi
     * @param yi
     * @param h difference for step
     */
    override fun getRungeKuttaSolutionY(xi: Double, yi: Double, h: Double): Double {
        val xz = xi + h / 2
        val k1 = getFunctionValue(xi, yi)
        val k2 = getFunctionValue(xz, yi + h * k1 / 2)
        val k3 = getFunctionValue(xz, yi + h * k2 / 2)
        val k4 = getFunctionValue(xi + h, yi + h * k3)
        return yi + h * (k1 + 2 * k2 + 2 * k3 + k4) / 6
    }
}