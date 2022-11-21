package alexbrukhov.appweather.fragments

import alexbrukhov.appweather.MainViewModel
import alexbrukhov.appweather.adapters.WeatherAdapter
import alexbrukhov.appweather.adapters.WeatherHourModel
import alexbrukhov.appweather.adapters.WeatherDayModel
import alexbrukhov.appweather.databinding.FragmentDaysBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager

class DaysFragment : Fragment(), WeatherAdapter.Listener {
    private lateinit var adapter: WeatherAdapter
    private lateinit var binding: FragmentDaysBinding
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        model.liveDataList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    private fun init() = with(binding){
        adapter = WeatherAdapter(this@DaysFragment)
        rcView.layoutManager = LinearLayoutManager(activity)
        rcView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }

    override fun onClick(item: WeatherDayModel) {
        model.liveDataCurrent.value = WeatherHourModel(
            city = item.city,
            time = item.time,
            condition = item.condition,
            currentTemp = item.currentTemp,
            maxTemp = item.maxTemp,
            minTemp = item.minTemp
        )
    }
}