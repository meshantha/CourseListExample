package com.kalum.dynamo.courses.ui.view.feature.list

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kalum.dynamo.courses.data.repository.CourseRepository
import com.kalum.dynamo.courses.data.remote.internal.lazyDeferred

class CourseListViewModel(private val repository: CourseRepository) : ViewModel() {

    internal val courses by lazyDeferred { repository.getAllCourses() }

    val loading = MutableLiveData<Boolean>()

    init {
        loading.value = true
    }
}
