package alexbrukhov.appweather

import alexbrukhov.appweather.adapters.WeatherHourModel
import alexbrukhov.appweather.adapters.WeatherDayModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val liveDataCurrent = MutableLiveData<WeatherHourModel>()
    val liveDataList = MutableLiveData<List<WeatherDayModel>>()
}