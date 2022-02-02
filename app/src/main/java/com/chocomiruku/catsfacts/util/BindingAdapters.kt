package com.chocomiruku.catsfacts.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chocomiruku.catsfacts.R
import com.chocomiruku.catsfacts.adapters.FactAdapter
import com.chocomiruku.catsfacts.domain.Fact
import com.chocomiruku.catsfacts.network.NetworkFact
import com.chocomiruku.catsfacts.overview.FactsApiStatus

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Fact>?) {
    val adapter = recyclerView.adapter as FactAdapter
    adapter.submitList(data)
}

@BindingAdapter("factsApiStatus")
fun bindStatus(statusImageView: ImageView, status: FactsApiStatus?) {
    when (status) {
        FactsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        FactsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        FactsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {

        }
    }
}