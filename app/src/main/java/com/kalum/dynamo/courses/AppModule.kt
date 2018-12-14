package com.kalum.dynamo.courses

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import androidx.room.Room
import com.kalum.dynamo.courses.data.local.CoursesDatabase
import com.kalum.dynamo.courses.data.remote.ConnectivityInterceptor
import com.kalum.dynamo.courses.data.remote.ConnectivityInterceptorImpl
import com.kalum.dynamo.courses.data.remote.CourseNetworkDataSource
import com.kalum.dynamo.courses.data.remote.CourseNetworkDataSourceImpl
import com.kalum.dynamo.courses.data.remote.api.UdacityCoursesApiService
import com.kalum.dynamo.courses.data.repository.CourseRepository
import com.kalum.dynamo.courses.data.repository.CourseRepositoryImpl
import com.kalum.dynamo.courses.ui.view.feature.detail.CourseDetailsViewModel
import com.kalum.dynamo.courses.ui.view.feature.list.CourseListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.singleBy

const val DATABASE_NAME = "courses-db"

val appModule = module {

    single {

        Room.databaseBuilder(androidApplication(), CoursesDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<CoursesDatabase>().courseItemDao() }

    singleBy<ConnectivityInterceptor, ConnectivityInterceptorImpl>(createOnStart = true)

    single { UdacityCoursesApiService(get()) }

    singleBy<CourseNetworkDataSource, CourseNetworkDataSourceImpl>(createOnStart = true)

    singleBy<CourseRepository, CourseRepositoryImpl>(createOnStart = true)

    viewModel<CourseListViewModel>()

    viewModel { (date: String) -> CourseDetailsViewModel(date, get()) }

}

