package com.kalum.dynamo.courses.ui.view.feature.detail

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import android.os.Bundle
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.kalum.dynamo.courses.R
import com.kalum.dynamo.courses.databinding.CourseDetailsFragmentBinding
import com.kalum.dynamo.courses.ui.base.fragment.ScopedFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CourseDetailsFragment : ScopedFragment() {

    private lateinit var item: String
    private lateinit var navController: NavController
    private val viewModel: CourseDetailsViewModel by viewModel { parametersOf(item) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.let {
            if (!it.isEmpty) {
                item = it.getString("key")
            }
        }
        return DataBindingUtil.inflate<CourseDetailsFragmentBinding>(
            inflater,
            R.layout.course_details_fragment,
            container,
            false
        ).apply {
            model = viewModel
            setLifecycleOwner(this@CourseDetailsFragment)
            enterTransition = Fade()
            setupToolbar(toolbar)
        }.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindUI()
    }

    private fun bindUI() = launch {

        with(viewModel) {
            detailCourse.await().observe(viewLifecycleOwner, Observer {
                loading.value = false
                setDetailCourse(it)
            })
        }
    }

    private fun setupToolbar(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

}
