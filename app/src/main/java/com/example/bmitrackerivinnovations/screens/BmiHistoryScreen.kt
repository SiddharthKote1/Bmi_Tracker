package com.example.bmitrackerivinnovations.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmitrackerivinnovations.history.BmiHistoryEntity
import com.example.bmitrackerivinnovations.viewmodel.BmiViewModel
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.lineSeries
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun BmiHistoryScreen(
    profileId: Int,
    viewModel: BmiViewModel = viewModel()
) {

    val history =
        viewModel.bmiHistory
            .collectAsStateWithLifecycle()
            .value

    LaunchedEffect(profileId) {

        viewModel.loadHistory(profileId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement =
            Arrangement.Top
    ) {

        Text(
            text = "BMI History"
        )

        Spacer(
            modifier =
                Modifier.height(6.dp)
        )

        Text(
            text = "Last 7 Days"
        )

        Spacer(
            modifier =
                Modifier.height(20.dp)
        )

        if (history.isEmpty()) {

            Text(
                text =
                    "No BMI records in the last 7 days"
            )

        } else {

            BmiChart(
                history = history
            )

            Spacer(
                modifier =
                    Modifier.height(24.dp)
            )

            history.forEach { item ->

                val date =
                    SimpleDateFormat(
                        "dd MMM",
                        Locale.getDefault()
                    ).format(
                        Date(item.date)
                    )

                Text(
                    text =
                        "$date    BMI: ${
                            String.format(
                                "%.1f",
                                item.bmi
                            )
                        }"
                )

                Spacer(
                    modifier =
                        Modifier.height(6.dp)
                )
            }
        }
    }
}


@Composable
private fun BmiChart(
    history: List<BmiHistoryEntity>
) {

    val modelProducer =
        remember {
            CartesianChartModelProducer()
        }

    LaunchedEffect(history) {

        modelProducer.runTransaction {

            lineSeries {

                series(
                    history.map {
                        it.bmi
                    }
                )
            }
        }
    }

    CartesianChartHost(
        chart =
            rememberCartesianChart(
                rememberLineCartesianLayer(),

                startAxis =
                    VerticalAxis.rememberStart(),

                bottomAxis =
                    HorizontalAxis.rememberBottom()
            ),

        modelProducer =
            modelProducer,

        modifier =
            Modifier
                .fillMaxWidth()
                .height(300.dp)
    )
}