@file:Suppress("DEPRECATION")

package com.kalum.dynamo.courses

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.kalum.dynamo.courses.data.local.CoursesDatabase
import com.kalum.dynamo.courses.data.local.dao.CourseItemDao
import com.kalum.dynamo.courses.data.remote.ConnectivityInterceptor
import com.kalum.dynamo.courses.data.remote.ConnectivityInterceptorImpl
import com.kalum.dynamo.courses.data.remote.api.UdacityCoursesApiService
import com.kalum.dynamo.courses.data.repository.CourseRepository
import com.kalum.dynamo.courses.data.repository.CourseRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import com.kalum.dynamo.courses.util.LiveDataTestUtil


@RunWith(RobolectricTestRunner::class)
class MyUnitTest2 : KoinTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    class ContextService(private val context: Context) {
        fun getString(stringID: Int): String = context.getString(stringID)
    }

    private val contextService: ContextService by inject()

    private val db: CoursesDatabase by inject()

    private val service: UdacityCoursesApiService by inject()

    private val courseItemDao: CourseItemDao by inject()

    private val repository: CourseRepository by inject()

    @Before
    fun setUp() {

        val contextModule = module {
            single { RuntimeEnvironment.application }
            factory { ContextService(get()) }
            single(override = true) {
                // In-Memory database config
                Room.inMemoryDatabaseBuilder(get(), CoursesDatabase::class.java)
                    .allowMainThreadQueries()
                    .build()
            }
            single<ConnectivityInterceptor>(override = true) { ConnectivityInterceptorImpl(get()) }
            single(override = true) { UdacityCoursesApiService(get()) }
            single<CourseRepository>(override = true) { CourseRepositoryImpl(get(), get()) }
            single(override = true) { get<CoursesDatabase>().courseItemDao() }
        }
        loadKoinModules(listOf(contextModule))

    }

    @After
    fun tearDown() {
        stopKoin()
    }

    private fun log(msg: Any?) = println(msg)


    @Test
    @Throws(Exception::class)
    fun `testing if the insert all is successful`() {
        val liveData = runBlocking { repository.getAllCourses() }
        Assert.assertNotNull(liveData)

        val list = LiveDataTestUtil.getValue(liveData)
        Assert.assertNotNull(list)

        log(list.size)

    }

}