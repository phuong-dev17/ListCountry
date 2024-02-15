package se.linerotech.module206.project1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import se.linerotech.module206.project1.common.CountryData
import se.linerotech.module206.project1.databinding.LayoutCellBinding

class CountryRecyclerViewAdapter(
    private val items: List<CountryData>,
    private val onCellClicked: (CountryData) ->Unit
) : RecyclerView.Adapter<CountryRecyclerViewAdapter.CountryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        return CountryViewHolder.create(parent, onCellClicked)
    }

    override fun onBindViewHolder(
        holder: CountryViewHolder,
        position: Int
    ) {
        val country = items[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CountryViewHolder(
        private val binding: LayoutCellBinding,
        private val onCellClicked: (CountryData) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: CountryData) {
            binding.countryName.text = country.country
            binding.language.text = country.language
            loadImage(country.flagUrl)

            binding.root.setOnClickListener{onCellClicked(country)}

        }
        private fun loadImage(url: String) {
            Glide
                .with(binding.imgFlag)
                .load(url)
                .into(binding.imgFlag)
        }
        companion object {
            fun create(parent: ViewGroup, onCellClicked: (CountryData) -> Unit): CountryViewHolder {
                val binding =
                    LayoutCellBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)

                return CountryViewHolder(binding, onCellClicked)
            }
        }
    }
}
