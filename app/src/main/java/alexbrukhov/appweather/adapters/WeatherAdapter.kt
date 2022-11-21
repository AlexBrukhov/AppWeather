package alexbrukhov.appweather.adapters

import alexbrukhov.appweather.R
import alexbrukhov.appweather.databinding.ListItemBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class WeatherAdapter(val listener: Listener?) : ListAdapter<WeatherDayModel, WeatherAdapter.Holder>(Comparator()) {

    class Holder(view: View, val listener: Listener?) : RecyclerView.ViewHolder(view){
        val binding = ListItemBinding.bind(view)
        var itemTemp: WeatherDayModel? = null
        init {
            itemView.setOnClickListener {
                itemTemp?.let { it1 -> listener?.onClick(it1) }
            }
        }

        fun bind(item: WeatherDayModel) = with(binding){
            itemTemp = item
            tvDate.text = item.time
            tvCondition.text = item.condition.text
            tvTemp.text = (if(item.currentTemp.isEmpty()) {
                "${item.maxTemp}ºC / ${item.minTemp}ºC" }
            else {"${item.currentTemp}ºC"

            }).toString()
            Picasso.get().load("https:" + item.imageUrl).into(im)
        }
    }

    class Comparator : DiffUtil.ItemCallback<WeatherDayModel>(){
        override fun areItemsTheSame(oldItem: WeatherDayModel, newItem: WeatherDayModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WeatherDayModel, newItem: WeatherDayModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return Holder(view, listener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    interface Listener{
        fun onClick(item: WeatherDayModel)
    }
}