package com.kalum.dynamo.courses.ui.base.viewholder

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kalum.dynamo.courses.util.bind

class DataBoundViewHolder<out T : ViewDataBinding>(private val binding: T) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun <T : ViewDataBinding> create(parent: ViewGroup, @LayoutRes layoutId: Int): DataBoundViewHolder<T> {
            return parent.bind<T>(layoutId).let { DataBoundViewHolder(it) }
        }
    }

    internal fun bind(objectForPosition: Any) {
        binding.executePendingBindings()
    }
}