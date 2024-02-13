package se.linerotech.module206.project1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import se.linerotech.module206.project1.common.CountryData
import se.linerotech.module206.project1.databinding.LayoutCellBinding

class CountryRecyclerViewAdapter (
    private val items: List<CountryData>
): RecyclerView.Adapter<CountryRecyclerViewAdapter.CountryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        return CountryViewHolder.create(parent)
    }

    override fun onBindViewHolder(
        holder: CountryViewHolder,
        position: Int
    ) {
        val countries = items[position]
        holder.bind(countries)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CountryViewHolder (
        private val binding: LayoutCellBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: CountryData) {
            binding.countryName.text = country.country
            binding.language.text = country.language
        }

        companion object {
            fun create(parent: ViewGroup): CountryViewHolder {
                val binding =
                    LayoutCellBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)

                return CountryViewHolder(binding)
            }
        }
    }
}