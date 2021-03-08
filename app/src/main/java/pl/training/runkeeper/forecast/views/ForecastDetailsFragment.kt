package pl.training.runkeeper.forecast.views

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.squareup.picasso.Picasso
import pl.training.runkeeper.R
import pl.training.runkeeper.commons.formatDate
import pl.training.runkeeper.commons.formatDegrees
import pl.training.runkeeper.commons.views.DialogBox
import pl.training.runkeeper.databinding.FragmentForecastDetailsBinding
import pl.training.runkeeper.forecast.viewmodels.ForecastViewModel

class ForecastDetailsFragment : Fragment() {

    private lateinit var binding: FragmentForecastDetailsBinding
    private val viewModel: ForecastViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentForecastDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("%%%$", savedInstanceState.toString())

        val minTemp = binding.dayDetailsMinTemperature
        minTemp.setText(formatDegrees(23))

//        forecastDate.text = formatDate(dayForecast.date)
//        forecastMaxTemperature.text = formatDegrees(dayForecast.maxTemperature)
//        forecastMinTemperature.text = formatDegrees(dayForecast.minTemperature)
//        Picasso.get().load(dayForecast.iconUrl).into(binding.forecastIcon)

    }

}