package alexbrukhov.appweather.fragments

import alexbrukhov.appweather.MainViewModel
import alexbrukhov.appweather.adapters.HourAdapter
import alexbrukhov.appweather.adapters.WeatherHourModel
import alexbrukhov.appweather.databinding.FragmentHoursBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager

class HoursFragment : Fragment(), HourAdapter.Listener {
    private lateinit var adapter: HourAdapter
    private lateinit var binding: FragmentHoursBinding
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        model.liveDataList.observe(viewLifecycleOwner){
            val hoursModel = arrayListOf<WeatherHourModel>()
            it.forEach { day ->
                day.hours.forEach { hour ->
                    hoursModel.add(
                        WeatherHourModel(
                            city = day.city,
                            time = hour.time,
                            condition = hour.condition,
                            currentTemp = hour.temp_c.toString(),
                            maxTemp = day.maxTemp,
                            minTemp = day.minTemp
                        )
                    )
                }
            }
            adapter.submitList(hoursModel)
        }
    }

    private fun init() = with(binding){
        adapter = HourAdapter(this@HoursFragment)
        rcView.layoutManager = LinearLayoutManager(activity)
        rcView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }

    override fun onClick(item: WeatherHourModel) {
        model.liveDataCurrent.value = item
    }
}