package pl.training.runkeeper.forecast.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

        // TODO Stworzyć widok szczegółowy pogody

        binding.forecastDetailsImageBox.setOnClickListener {
            Log.d("###", "Click")
        }
    }

}