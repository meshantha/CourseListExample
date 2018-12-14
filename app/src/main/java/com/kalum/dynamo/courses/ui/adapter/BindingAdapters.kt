package com.kalum.dynamo.courses.ui.adapter


import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.kalum.dynamo.courses.R
import com.kalum.dynamo.courses.util.loadUrl

@BindingAdapter("visibleGone")
fun showHide(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}


@BindingAdapter("android:src")
fun loadImage(image: ImageView, imageUrl: String?) {
    val options = RequestOptions()
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.placeholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)


    imageUrl?.let { image.loadUrl(it, options) }
}

@BindingAdapter("android:circle")
fun loadCircularImage(image: ImageView, imageUrl: String?) {
    val options = RequestOptions()
        .error(R.color.error_color_material_dark)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
        .circleCrop()



    imageUrl?.let { image.loadUrl(it, options) }
}


