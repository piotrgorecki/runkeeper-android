package pl.training.runkeeper.forecast.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import pl.training.runkeeper.RunKeeperApplication.Companion.applicationGraph
import pl.training.runkeeper.commons.Logger
import pl.training.runkeeper.databinding.FragmentForecastListBinding
import pl.training.runkeeper.forecast.models.ForecastService
import javax.inject.Inject

class ForecastListFragment : Fragment() {

    private val disposableBag = CompositeDisposable()
    private val forecastListAdapter = ForecastListAdapter()
    private lateinit var binding: FragmentForecastListBinding
    @Inject
    lateinit var logger: Logger
    @Inject
    lateinit var forecastService: ForecastService

    init {
        applicationGraph.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentForecastListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.forecastList.layoutManager = LinearLayoutManager(activity)
        binding.forecastList.adapter = forecastListAdapter

        forecastService.getForecast("warsaw")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ forecastListAdapter.update(it.data) }, { logger.log(it.toString()) })
            .addTo(disposableBag)
    }

    override fun onDetach() {
        super.onDetach()
        disposableBag.clear()
    }

}