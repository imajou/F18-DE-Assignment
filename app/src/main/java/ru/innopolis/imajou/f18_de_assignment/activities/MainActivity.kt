package ru.innopolis.imajou.f18_de_assignment.activities

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_main.*
import ru.innopolis.imajou.f18_de_assignment.R
import ru.innopolis.imajou.f18_de_assignment.model.Equation


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        function_graph.viewport.isScrollable = false
        function_graph.viewport.isScalable = false
        function_graph.viewport.setScrollableY(false)
        function_graph.viewport.setScalableY(false)

        function_graph.viewport.isXAxisBoundsManual = true
        function_graph.viewport.setMinX(Equation.XLow)
        function_graph.viewport.setMaxX(Equation.XHigh)

        function_graph.viewport.isYAxisBoundsManual = true
        function_graph.viewport.setMinY(1.0)
        function_graph.viewport.setMaxY(4.0)

        val exactSolution = LineGraphSeries(Equation.exactSolution())
        exactSolution.color = Color.GREEN
        val eulerSolution = LineGraphSeries(Equation.eulerMethod())
        eulerSolution.color = Color.BLUE
        val improvedEulerSolution = LineGraphSeries(Equation.improvedEulerMethod())
        eulerSolution.color = Color.YELLOW

        function_graph.addSeries(exactSolution)
        function_graph.addSeries(eulerSolution)
        function_graph.addSeries(improvedEulerSolution)

    }
}
