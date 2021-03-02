package pl.training.runkeeper.forecast.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import pl.training.runkeeper.R
import pl.training.runkeeper.RunKeeperApplication
import pl.training.runkeeper.RunKeeperApplication.Companion.applicationGraph
import pl.training.runkeeper.commons.Logger
import pl.training.runkeeper.databinding.FragmentForecastListBinding
import pl.training.runkeeper.forecast.viewmodels.ForecastViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ForecastListFragment : Fragment() {

    private val disposableBag = CompositeDisposable()
    private val viewModel: ForecastViewModel by activityViewModels()
    private val forecastListAdapter = ForecastListAdapter()
    private lateinit var binding: FragmentForecastListBinding
    @Inject
    lateinit var logger: Logger

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        applicationGraph.inject(this)
        binding = FragmentForecastListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindViews()
    }

    private fun initViews() {
        forecastListAdapter.tapListener = { findNavController().navigate(R.id.show_forecast_details) }
        with (binding) {
            forecastList.layoutManager = LinearLayoutManager(activity)
            forecastList.adapter = forecastListAdapter
        }
    }

    private fun bindViews() {
        binding.forecastCityName.textChanges()
                .map { it.toString() }
                .filter { it.isNotBlank() }
                .distinct()
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(viewModel::getForecast) { logger.log(it.toString()) }
                .addTo(disposableBag)
        viewModel.forecastData().observe(viewLifecycleOwner) {
            forecastListAdapter.update(it.data)
        }
        viewModel.isLoading().observe(viewLifecycleOwner) {
            with(binding) {
                forecastCityName.isEnabled = !it
                forecastList.isVisible = !it
                forecastActivityIndicator.isVisible = it
            }
        }
        viewModel.errorMessage().observe(viewLifecycleOwner) {
            //Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
                .setAction("Ok") {  }
                .show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableBag.clear()
    }

}