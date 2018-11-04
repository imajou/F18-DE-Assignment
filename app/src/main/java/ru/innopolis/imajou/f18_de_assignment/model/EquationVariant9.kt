package ru.innopolis.imajou.f18_de_assignment.model

class EquationVariant9(override var x0: Double, override var y0: Double) : EquationVariant {

    /**
     * Reset initial values for the problem
     * @param x0
     * @param y0
     */
    override fun updateInitialValues(x0: Double, y0: Double) {
        this.x0 = x0
        this.y0 = y0
    }

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
     * @param x_prev
     * @param y_prev
     * @param h difference for step
     */
    override fun getEulerSolutionY(x_prev: Double, y_prev: Double, h: Double): Double =
        y_prev + h * getFunctionValue(x_prev, y_prev)

    /**
     * Get approximation with improved Euler method for the problem
     * @param x_prev
     * @param y_prev
     * @param h difference for step
     */
    override fun getEulerImprSolutionY(x_prev: Double, y_prev: Double, h: Double): Double {
        val k1 = getFunctionValue(x_prev, y_prev)
        val k2 = getFunctionValue(x_prev + h, y_prev + h * k1)
        return y_prev + h * (k1 + k2) / 2
    }

    /**
     * Get approximation with Runge-Kutta method for the problem
     * @param x_prev
     * @param y_prev
     * @param h difference for step
     */
    override fun getRungeKuttaSolutionY(x_prev: Double, y_prev: Double, h: Double): Double {
        val xz = x_prev + h / 2
        val k1 = getFunctionValue(x_prev, y_prev)
        val k2 = getFunctionValue(xz, y_prev + h * k1 / 2)
        val k3 = getFunctionValue(xz, y_prev + h * k2 / 2)
        val k4 = getFunctionValue(x_prev + h, y_prev + h * k3)
        return y_prev + h * (k1 + 2 * k2 + 2 * k3 + k4) / 6
    }
}