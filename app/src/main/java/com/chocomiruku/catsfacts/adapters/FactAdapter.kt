package com.chocomiruku.catsfacts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chocomiruku.catsfacts.databinding.ListItemFactBinding
import com.chocomiruku.catsfacts.domain.Fact


class FactAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Fact, FactAdapter.ViewHolder>(FactDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fact = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(fact)
        }
        holder.bind(fact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemFactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(fact: Fact) {
            binding.factText.text = fact.shortFactText
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemFactBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    class OnClickListener(val clickListener: (fact: Fact) -> Unit) {
        fun onClick(fact: Fact) = clickListener(fact)
    }


    class FactDiffCallback :
        DiffUtil.ItemCallback<Fact>() {

        override fun areItemsTheSame(oldItem: Fact, newItem: Fact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Fact, newItem: Fact): Boolean {
            return oldItem == newItem
        }
    }
}