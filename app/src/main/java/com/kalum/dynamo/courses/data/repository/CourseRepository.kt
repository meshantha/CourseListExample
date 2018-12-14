package com.kalum.dynamo.courses.data.repository

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import androidx.lifecycle.LiveData
import com.kalum.dynamo.courses.data.local.entity.CoursesItem

interface CourseRepository {

    suspend fun getAllCourses(): LiveData<out List<CoursesItem>>

    suspend fun getCourseByKey(key: String): LiveData<out CoursesItem>
}