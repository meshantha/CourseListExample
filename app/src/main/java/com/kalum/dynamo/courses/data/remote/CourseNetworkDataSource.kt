package com.kalum.dynamo.courses.data.remote

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import androidx.lifecycle.LiveData
import com.kalum.dynamo.courses.data.remote.response.CoursesResponse

interface CourseNetworkDataSource {

    val downloadedCourses: LiveData<CoursesResponse>

    suspend fun fetchCourses()
}