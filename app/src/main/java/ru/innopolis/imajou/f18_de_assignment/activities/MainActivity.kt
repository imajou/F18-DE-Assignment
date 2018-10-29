package ru.innopolis.imajou.f18_de_assignment.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_main.*
import ru.innopolis.imajou.f18_de_assignment.R
import ru.innopolis.imajou.f18_de_assignment.model.Equation
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    var exactSolution = LineGraphSeries(Equation.exactSolution())
    var eulerSolution = LineGraphSeries(Equation.eulerMethod())
    var improvedEulerSolution = LineGraphSeries(Equation.improvedEulerMethod())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEps()
        initCheckBoxes()
        initSeekBar()

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

    fun initEps() {
        seekbar_eps.progress = 10
        Equation.eps = 0.1
        tw_eps_value.text = "0.1"
    }

    fun updateDataset() {
        exactSolution.resetData(Equation.exactSolution())
        eulerSolution.resetData(Equation.eulerMethod())
        improvedEulerSolution.resetData(Equation.improvedEulerMethod())
    }

    fun initCheckBoxes() {
        checkbox_exact_solution.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) function_graph.addSeries(exactSolution)
            else function_graph.removeSeries(exactSolution)
        }
        checkbox_euler_method.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) function_graph.addSeries(eulerSolution)
            else function_graph.removeSeries(eulerSolution)
        }
        checkbox_improved_euler_method.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) function_graph.addSeries(improvedEulerSolution)
            else function_graph.removeSeries(improvedEulerSolution)
        }
    }

    fun initSeekBar() {
        seekbar_eps.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                //text_view.text = "Progress : $i"
                tw_eps_value.text = DecimalFormat("#.##").format(i.toDouble() / 100 + 0.01)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
                //Toast.makeText(applicationContext,"start tracking",Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something
                val newEps = DecimalFormat("#.##").format(seekBar.progress.toDouble() / 100 + 0.01).toDouble()
                Equation.eps = newEps
                tw_eps_value.text = newEps.toString()
                updateDataset()
            }
        })
    }


}
