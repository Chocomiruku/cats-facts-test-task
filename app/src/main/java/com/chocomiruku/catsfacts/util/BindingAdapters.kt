package com.chocomiruku.catsfacts.util

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.chocomiruku.catsfacts.R
import com.chocomiruku.catsfacts.adapters.FactAdapter
import com.chocomiruku.catsfacts.domain.Fact

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Fact>?) {
    val adapter = recyclerView.adapter as FactAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatusImage(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {

        }
    }
}

@BindingAdapter("apiStatus")
fun bindStatusText(statusTextView: TextView, status: ApiStatus?) {
    when (status) {
        ApiStatus.ERROR -> {
            statusTextView.visibility = View.VISIBLE
        }
        else -> {
            statusTextView.visibility = View.GONE
        }
    }
}

@BindingAdapter("favouritesCount")
fun bindFavouritesEmpty(favouritesEmptyTextView: TextView, facts: List<Fact>?) {
    if (facts.isNullOrEmpty()) {
        favouritesEmptyTextView.visibility = View.VISIBLE
    } else {
        favouritesEmptyTextView.visibility = View.GONE
    }
}

@BindingAdapter("imageUrl")
fun bindPhoto(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        imgView.visibility = View.VISIBLE
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        GlideApp.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    } ?: run {
        imgView.visibility = View.INVISIBLE
    }
}

@BindingAdapter("favouritesStatus")
fun bindFavouritesButton(button: Button, isAdded: Boolean) {
    if (!isAdded) {
        button.text = button.context.getString(R.string.add_to_favourites)
        button.setCompoundDrawablesWithIntrinsicBounds(
            AppCompatResources.getDrawable(
                button.context,
                R.drawable.ic_baseline_favorites_border_24
            ), null, null, null
        )
    } else {
        button.text = button.context.getString(R.string.delete_from_favourites)
        button.setCompoundDrawablesWithIntrinsicBounds(
            AppCompatResources.getDrawable(
                button.context,
                R.drawable.ic_baseline_favorites_filled_24
            ), null, null, null
        )
    }
}