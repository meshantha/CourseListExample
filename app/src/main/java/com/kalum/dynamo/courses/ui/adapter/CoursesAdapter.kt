package com.kalum.dynamo.courses.ui.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kalum.dynamo.courses.BR
import com.kalum.dynamo.courses.R
import com.kalum.dynamo.courses.data.local.entity.CoursesItem
import com.kalum.dynamo.courses.util.bind

class CoursesAdapter(private val itemClick: (Any) -> Unit) :
    RecyclerView.Adapter<CoursesAdapter.ViewHolder>() {

    var courseDataList: List<CoursesItem> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = parent.bind<ViewDataBinding>(R.layout.list_item)
        return ViewHolder(binding, itemClick)
    }

    override fun getItemCount() = courseDataList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(courseDataList[position])
    }

    class ViewHolder(
        private val binding: ViewDataBinding,
        private val itemClick: (Any) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(view: Any) {

            itemView.setOnClickListener {
                itemClick(view)
            }
            with(binding) {
                setVariable(BR.model, view)
                executePendingBindings()
            }

        }
    }
}