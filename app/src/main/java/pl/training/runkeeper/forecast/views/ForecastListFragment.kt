package pl.training.runkeeper.forecast.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import pl.training.runkeeper.databinding.FragmentForecastListBinding
import pl.training.runkeeper.forecast.viewmodels.ForecastViewModel

class ForecastListFragment : Fragment() {

    private val viewModel: ForecastViewModel by activityViewModels()
    private val forecastListAdapter = ForecastListAdapter()
    private lateinit var binding: FragmentForecastListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentForecastListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindViews()
    }

    private fun initViews() {
        binding.forecastList.layoutManager = LinearLayoutManager(activity)
        binding.forecastList.adapter = forecastListAdapter
    }

    private fun bindViews() {
        binding.forecastCityName.doAfterTextChanged {
            viewModel.getForecast(it.toString())
        }
        viewModel.forecastData().observe(viewLifecycleOwner) {
            forecastListAdapter.update(it.data)
        }
    }

}