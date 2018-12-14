package com.kalum.dynamo.courses.ui.adapter

/**
 * Created by Kalum Fernando on 13/10/2018.
 */

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kalum.dynamo.courses.ui.base.viewholder.DataBoundViewHolder

abstract class BaseDataBoundAdapter<T: ViewDataBinding> : RecyclerView.Adapter<DataBoundViewHolder<T>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<T> {
        return DataBoundViewHolder.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<T>, position: Int) {
        holder.bind(getObjectForPosition(position))
    }

    abstract fun getObjectForPosition(position: Int): Any

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    @LayoutRes
    abstract fun getLayoutIdForPosition(position: Int): Int

}