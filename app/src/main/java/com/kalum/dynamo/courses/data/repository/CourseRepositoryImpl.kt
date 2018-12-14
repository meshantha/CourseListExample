package com.kalum.dynamo.courses.data.repository

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import androidx.lifecycle.LiveData
import com.kalum.dynamo.courses.data.local.dao.CourseItemDao
import com.kalum.dynamo.courses.data.local.entity.CoursesItem
import com.kalum.dynamo.courses.data.remote.CourseNetworkDataSource
import com.kalum.dynamo.courses.data.remote.response.CoursesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class CourseRepositoryImpl(
    private val courseItemDao: CourseItemDao,
    private val courseNetworkDataSource: CourseNetworkDataSource
) : CourseRepository {

    override suspend fun getCourseByKey(
        key: String
    ): LiveData<out CoursesItem> {
        return withContext(Dispatchers.IO) {
            refreshCourseData()
            return@withContext courseItemDao.getCourseByKey(key)
        }
    }

    init {
        courseNetworkDataSource.downloadedCourses.observeForever { currentCourses ->
            storeFetchedCourses(currentCourses)
        }
    }

    override suspend fun getAllCourses(): LiveData<out List<CoursesItem>> {
        return withContext(Dispatchers.IO) {
            refreshCourseData()
            return@withContext courseItemDao.getAllCourses()
        }
    }

    private fun storeFetchedCourses(courseResponse: CoursesResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            courseItemDao.insertAll(courseResponse.courses)
        }
    }

    private fun isFetchNeeded(lastFetchedTime: ZonedDateTime) = with(lastFetchedTime) {
        isBefore(ZonedDateTime.now().minusMinutes(1))
    }

    private suspend fun refreshCourseData() {
        //  if (isFetchNeeded(ZonedDateTime.now()))
        fetchAllCourses()
    }

    private suspend fun fetchAllCourses() {
        courseNetworkDataSource.fetchCourses()
    }


}