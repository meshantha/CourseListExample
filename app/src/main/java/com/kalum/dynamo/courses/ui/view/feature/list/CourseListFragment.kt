package com.kalum.dynamo.courses.ui.view.feature.list

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.kalum.dynamo.courses.R
import com.kalum.dynamo.courses.data.local.entity.CoursesItem
import com.kalum.dynamo.courses.databinding.CourseListFragmentBinding
import com.kalum.dynamo.courses.ui.adapter.CoursesAdapter
import com.kalum.dynamo.courses.ui.base.fragment.ScopedFragment
import kotlinx.android.synthetic.main.course_list_fragment.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class CourseListFragment : ScopedFragment() {

    private val viewModel by viewModel<CourseListViewModel>()

    private val adapter: CoursesAdapter by lazy {
        CoursesAdapter { course: Any ->
            detailItemClicked(course)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return DataBindingUtil.inflate<CourseListFragmentBinding>(
            inflater,
            R.layout.course_list_fragment,
            container,
            false
        ).apply {
            model = viewModel
            setLifecycleOwner(this@CourseListFragment)
        }.root
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        courses_rv.adapter = adapter
        courses_rv.setHasFixedSize(true)
        courses_rv.layoutManager = GridLayoutManager(context, 2)

        bindUI()
    }

    private fun bindUI() = launch {

        with(viewModel) {

            courses.await().observe(viewLifecycleOwner, Observer {
                if (null == it) return@Observer
                loading.value = false
                adapter.courseDataList = it
            })
        }
    }

    private fun detailItemClicked(details: Any) {
        val item = (details as CoursesItem)
        val args = Bundle()
        args.putString("key", item.key);
        findNavController().navigate(R.id.courseDetailFragment, args, null, null)
    }

}
