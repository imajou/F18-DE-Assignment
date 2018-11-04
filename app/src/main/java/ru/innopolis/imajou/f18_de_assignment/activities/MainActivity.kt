package ru.innopolis.imajou.f18_de_assignment.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.SeekBar
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_main.*
import ru.innopolis.imajou.f18_de_assignment.R
import ru.innopolis.imajou.f18_de_assignment.model.Equation
import ru.innopolis.imajou.f18_de_assignment.model.EquationVariant9
import ru.innopolis.imajou.f18_de_assignment.model.ErrorGlobal


class MainActivity : AppCompatActivity() {

    private var variant: EquationVariant9 = EquationVariant9(1.0, 1.0)      // Init variant
    private var equation: Equation = Equation(variant)                              // Init equation

    private var plotExact = LineGraphSeries(equation.solutionExact)
    private var plotEuler = LineGraphSeries(equation.solutionEuler)
    private var plotEulerImproved = LineGraphSeries(equation.solutionEulerImpr)
    private var plotRungeKutta = LineGraphSeries(equation.solutionRungeKutta)

    private var errorEuler = LineGraphSeries(equation.errorEulerLocal)
    private var errorEulerImproved = LineGraphSeries(equation.errorEulerImprLocal)
    private var errorRungeKutta = LineGraphSeries(equation.errorRungeKuttaLocal)

    private var errorGlobalEuler = LineGraphSeries(ErrorGlobal.euler(variant, equation.fromX, equation.toX))
    private var errorGlobalEulerImproved = LineGraphSeries(ErrorGlobal.eulerImproved(variant, equation.fromX, equation.toX))
    private var errorGlobalRungeKutta = LineGraphSeries(ErrorGlobal.rungeKutta(variant, equation.fromX, equation.toX))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCheckBoxes()
        initSeekBar()
        initFunctionGraphs()
        initErrorGraphs()
        initErrorGlobalGraphs()
        initButtons()
    }

    /**
     * Initialize graphics switching radio buttons
     */
    private fun initButtons() {
        btn_group_graph_params.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.btn_show_graphs -> {
                    graph_error_local.visibility = View.GONE
                    graph_error_global.visibility = View.GONE
                    graph_function.visibility = View.VISIBLE
                }
                R.id.btn_show_local_errors -> {
                    graph_function.visibility = View.GONE
                    graph_error_global.visibility = View.GONE
                    graph_error_local.visibility = View.VISIBLE
                }
                R.id.btn_show_global_errors -> {
                    graph_function.visibility = View.GONE
                    graph_error_local.visibility = View.GONE
                    graph_error_global.visibility = View.VISIBLE
                }
            }
        }
    }

    /**
     * Initialize function graphics
     */
    private fun initFunctionGraphs() {
        graph_function.viewport.isScrollable = true
        graph_function.viewport.isScalable = true
        graph_function.viewport.setScrollableY(false)
        graph_function.viewport.setScalableY(false)

        graph_function.viewport.isXAxisBoundsManual = true
        graph_function.viewport.setMinX(equation.fromX)
        graph_function.viewport.setMaxX(equation.toX)

        plotExact.color = resources.getColor(R.color.materialRed_400)
        plotExact.title = "Exact solution"

        plotEuler.color = resources.getColor(R.color.materialBlue_400)
        plotEuler.title = "Euler method"

        plotEulerImproved.color = resources.getColor(R.color.materialPurple_400)
        plotEulerImproved.title = "Impr. Euler method"

        plotRungeKutta.color = resources.getColor(R.color.materialGreen_400)
        plotRungeKutta.title = "Runge-Kutta method"

        graph_function.addSeries(plotExact)
        graph_function.addSeries(plotEuler)
        graph_function.addSeries(plotEulerImproved)
        graph_function.addSeries(plotRungeKutta)
    }

    /**
     * Initialize local error graphics
     */
    private fun initErrorGraphs() {
        graph_error_local.viewport.isScrollable = true
        graph_error_local.viewport.isScalable = true
        graph_error_local.viewport.setScrollableY(false)
        graph_error_local.viewport.setScalableY(false)

        errorEuler.color = resources.getColor(R.color.materialBlue_400)
        errorEuler.title = "Euler error"

        errorEulerImproved.color = resources.getColor(R.color.materialPurple_400)
        errorEulerImproved.title = "Euler impr. error"

        errorRungeKutta.color = resources.getColor(R.color.materialGreen_400)
        errorRungeKutta.title = "Runge-Kutta error"

        graph_error_local.addSeries(errorEuler)
        graph_error_local.addSeries(errorEulerImproved)
        graph_error_local.addSeries(errorRungeKutta)
    }

    /**
     * Initialize global error graphics
     */
    private fun initErrorGlobalGraphs() {
        graph_error_global.viewport.isScrollable = true
        graph_error_global.viewport.isScalable = true
        graph_error_global.viewport.setScrollableY(false)
        graph_error_global.viewport.setScalableY(false)

        graph_function.viewport.isXAxisBoundsManual = true
        graph_function.viewport.setMinX(1.0)
        graph_function.viewport.setMaxX(100.0)

        errorGlobalEuler.color = resources.getColor(R.color.materialBlue_400)
        errorGlobalEuler.title = "Euler global error"

        errorGlobalEulerImproved.color = resources.getColor(R.color.materialPurple_400)
        errorGlobalEulerImproved.title = "Euler improved global error"

        errorGlobalRungeKutta.color = resources.getColor(R.color.materialGreen_400)
        errorGlobalRungeKutta.title = "Runge-Kutta global error"

        graph_error_global.addSeries(errorGlobalEuler)
        graph_error_global.addSeries(errorGlobalEulerImproved)
        graph_error_global.addSeries(errorGlobalRungeKutta)
    }

    /**
     * Update grapics and datasets
     */
    private fun updateDataset() {
        equation.updateData()

        plotExact.resetData(equation.solutionExact)
        plotEuler.resetData(equation.solutionEuler)
        plotEulerImproved.resetData(equation.solutionEulerImpr)
        plotRungeKutta.resetData(equation.solutionRungeKutta)

        graph_function.viewport.setMinX(equation.fromX)
        graph_function.viewport.setMaxX(equation.toX)

        errorEuler.resetData(equation.errorEulerLocal)
        errorEulerImproved.resetData(equation.errorEulerImprLocal)
        errorRungeKutta.resetData(equation.errorRungeKuttaLocal)
    }

    /**
     * Init control panel graphics show/hide checkboxes
     */
    private fun initCheckBoxes() {
        checkbox_exact_solution.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) graph_function.addSeries(plotExact)
            else graph_function.removeSeries(plotExact)
        }
        checkbox_euler_method.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                graph_function.addSeries(plotEuler)
                graph_error_local.addSeries(errorEuler)
                graph_error_global.addSeries(errorGlobalEuler)
            } else {
                graph_function.removeSeries(plotEuler)
                graph_error_local.removeSeries(errorEuler)
                graph_error_global.removeSeries(errorGlobalEuler)
            }
        }
        checkbox_improved_euler_method.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                graph_function.addSeries(plotEulerImproved)
                graph_error_local.addSeries(errorEulerImproved)
                graph_error_global.addSeries(errorGlobalEulerImproved)
            } else {
                graph_function.removeSeries(plotEulerImproved)
                graph_error_local.removeSeries(errorEulerImproved)
                graph_error_global.removeSeries(errorGlobalEulerImproved)
            }
        }
        checkbox_runge_kutta_method.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                graph_function.addSeries(plotRungeKutta)
                graph_error_local.addSeries(errorRungeKutta)
                graph_error_global.addSeries(errorGlobalRungeKutta)
            } else {
                graph_function.removeSeries(plotRungeKutta)
                graph_error_local.removeSeries(errorRungeKutta)
                graph_error_global.removeSeries(errorGlobalRungeKutta)
            }
        }
    }

    /**
     * Initialize control panel seekbar
     */
    private fun initSeekBar() {

        // Seekbar for h
        seekbar_h.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                tw_steps_value.text = "${(i + 1)} steps"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                val newH = seekBar.progress + 1
                tw_steps_value.text = "$newH steps"
                equation.steps = newH
                updateDataset()
            }
        })

        // Seekbar for x0
        seekbar_x0.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                tw_x0_value.text = "x0=${(i + 1).toDouble() / 10}"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                val newX0: Double = (seekBar.progress.toDouble() + 1) / 10
                tw_x0_value.text = "x0=$newX0"
                variant.x0 = newX0
                equation.fromX = newX0
                equation.toX = newX0 * 2
                updateDataset()
            }
        })

        // Seekbar for y0
        seekbar_y0.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                tw_y0_value.text = "y0=${(i + 1).toDouble() / 10}"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                val newY0: Double = (seekBar.progress.toDouble() + 1) / 10
                tw_y0_value.text = "y0=$newY0"
                variant.y0 = newY0
                updateDataset()
            }
        })

        // Seekbar for X
        seekbar_xMax.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                tw_xMax_value.text = "X=${(i + 1).toDouble() / 10}"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                if(seekBar.progress <= seekbar_x0.progress) {
                    seekBar.progress = seekbar_x0.progress + 1
                }
                val newXMax: Double = (seekBar.progress.toDouble() + 1) / 10
                tw_xMax_value.text = "X=$newXMax"
                equation.toX = newXMax
                seekbar_x0.max = seekBar.progress - 1
                updateDataset()
            }
        })

    }

}
