package com.chocomiruku.catsfacts.util

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
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

@BindingAdapter("imageUrl")
fun bindPhoto(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        GlideApp.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
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
    }
    else {
        button.text = button.context.getString(R.string.delete_from_favourites)
        button.setCompoundDrawablesWithIntrinsicBounds(
            AppCompatResources.getDrawable(
                button.context,
                R.drawable.ic_baseline_favorites_filled_24
            ), null, null, null
        )
    }
}