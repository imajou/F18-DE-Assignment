package ru.innopolis.imajou.f18_de_assignment.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.SeekBar
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_main.*
import ru.innopolis.imajou.f18_de_assignment.R
import ru.innopolis.imajou.f18_de_assignment.model.Equation
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    private var exactSolution = LineGraphSeries(Equation.dataExactSolution)
    private var eulerSolution = LineGraphSeries(Equation.dataEulerSolution)
    private var improvedEulerSolution = LineGraphSeries(Equation.dataEulerImprSolution)

    private var eulerError = LineGraphSeries(Equation.dataEulerError)
    private var eulerImprError = LineGraphSeries(Equation.dataEulerImprError)

    private var graphTypeShowed = "FUNCTION_GRAPH"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEps()
        initCheckBoxes()
        initSeekBar()
        initFunctionGraphs()
        initErrorGraphs()
        initButtons()
    }

    private fun initButtons() {
        btn_switch_graphs.setOnClickListener {
            if (graphTypeShowed == "FUNCTION_GRAPH"){
                graphTypeShowed = "ERROR_GRAPH"
                btn_switch_graphs.text = "Show graphs"
                function_graph.visibility = View.GONE
                error_graph.visibility = View.VISIBLE
                return@setOnClickListener
            }
            if (graphTypeShowed == "ERROR_GRAPH") {
                graphTypeShowed = "FUNCTION_GRAPH"
                btn_switch_graphs.text = "Show errors"
                function_graph.visibility = View.VISIBLE
                error_graph.visibility = View.GONE
                return@setOnClickListener
            }
        }
    }

    private fun initFunctionGraphs(){
        function_graph.viewport.isScrollable = true
        function_graph.viewport.isScalable = true
        function_graph.viewport.setScrollableY(false)
        function_graph.viewport.setScalableY(false)

        function_graph.viewport.isXAxisBoundsManual = true
        function_graph.viewport.setMinX(Equation.XLow)
        function_graph.viewport.setMaxX(Equation.XHigh)

        function_graph.viewport.isYAxisBoundsManual = true
        function_graph.viewport.setMinY(1.0)
        function_graph.viewport.setMaxY(4.0)

        exactSolution.color = resources.getColor(R.color.materialRed_400)
        exactSolution.title = "Exact solution"

        eulerSolution.color = resources.getColor(R.color.materialBlue_400)
        eulerSolution.title = "Euler method"

        improvedEulerSolution.color = resources.getColor(R.color.materialPurple_400)
        improvedEulerSolution.title = "Impr. Euler method"

        function_graph.addSeries(exactSolution)
        function_graph.addSeries(eulerSolution)
        function_graph.addSeries(improvedEulerSolution)
    }

    private fun initErrorGraphs(){
        error_graph.viewport.isScrollable = true
        error_graph.viewport.isScalable = true
        error_graph.viewport.setScrollableY(false)
        error_graph.viewport.setScalableY(false)

        eulerError.color = resources.getColor(R.color.materialBlue_400)
        eulerError.title = "Euler error"

        eulerImprError.color = resources.getColor(R.color.materialPurple_400)
        eulerImprError.title = "Euler impr. title"

        error_graph.addSeries(eulerError)
        error_graph.addSeries(eulerImprError)
    }

    private fun initEps() {
        seekbar_eps.progress = 10
        Equation.eps = 0.1
        tw_eps_value.text = "0.1"
    }

    private fun updateDataset() {
        Equation.updateData()

        exactSolution.resetData(Equation.dataExactSolution)
        eulerSolution.resetData(Equation.dataEulerSolution)
        improvedEulerSolution.resetData(Equation.dataEulerImprSolution)

        eulerError.resetData(Equation.dataEulerError)
    }

    private fun initCheckBoxes() {
        checkbox_exact_solution.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) function_graph.addSeries(exactSolution)
            else function_graph.removeSeries(exactSolution)
        }
        checkbox_euler_method.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                function_graph.addSeries(eulerSolution)
                error_graph.addSeries(eulerError)
            }
            else {
                function_graph.removeSeries(eulerSolution)
                error_graph.removeSeries(eulerError)
            }
        }
        checkbox_improved_euler_method.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                function_graph.addSeries(improvedEulerSolution)
                error_graph.addSeries(eulerImprError)
            }
            else {
                function_graph.removeSeries(improvedEulerSolution)
                error_graph.removeSeries(eulerImprError)
            }
        }
    }

    private fun initSeekBar() {
        seekbar_eps.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                tw_eps_value.text = DecimalFormat("#.##").format(i.toDouble() / 100 + 0.01)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                val newEps = DecimalFormat("#.##").format(seekBar.progress.toDouble() / 100 + 0.01).toDouble()
                Equation.eps = newEps
                tw_eps_value.text = newEps.toString()
                updateDataset()
            }
        })

    }

}
