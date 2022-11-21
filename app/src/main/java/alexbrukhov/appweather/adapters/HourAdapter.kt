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

class HourAdapter(val listener: Listener?) : ListAdapter<WeatherHourModel, HourAdapter.Holder>(Comparator()) {

    class Holder(view: View, val listener: Listener?) : RecyclerView.ViewHolder(view){
        val binding = ListItemBinding.bind(view)
        var itemTemp: WeatherHourModel? = null
        init {
            itemView.setOnClickListener {
                itemTemp?.let { it1 -> listener?.onClick(it1) }
            }
        }

        fun bind(item: WeatherHourModel) = with(binding){
            itemTemp = item
            tvDate.text = item.time
            tvCondition.text = item.condition.text
            tvTemp.text = item.currentTemp
            Picasso.get().load("https:" + item.condition.icon).into(im)
        }
    }

    class Comparator : DiffUtil.ItemCallback<WeatherHourModel>(){
        override fun areItemsTheSame(oldItem: WeatherHourModel, newItem: WeatherHourModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WeatherHourModel, newItem: WeatherHourModel): Boolean {
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
        fun onClick(item: WeatherHourModel)
    }
}