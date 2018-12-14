package com.kalum.dynamo.courses.ui.view.feature.detail

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kalum.dynamo.courses.data.local.entity.CoursesItem
import com.kalum.dynamo.courses.data.repository.CourseRepository
import com.kalum.dynamo.courses.data.remote.internal.lazyDeferred

class CourseDetailsViewModel(private val key: String, private val repository: CourseRepository) : ViewModel() {

    private val detailsCourse = MutableLiveData<CoursesItem>()
    val loading = MutableLiveData<Boolean>()
    init {
        loading.value = true
    }

    val _detailCourse: LiveData<CoursesItem> get() = detailsCourse

    internal fun setDetailCourse(course: CoursesItem) {
        detailsCourse.value = course
    }

    internal val detailCourse by lazyDeferred { repository.getCourseByKey(key) }

}
