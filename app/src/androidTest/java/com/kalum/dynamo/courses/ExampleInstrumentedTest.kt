@file:Suppress("DEPRECATION")

package com.kalum.dynamo.courses

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.kalum.dynamo.courses.data.local.CoursesDatabase
import com.kalum.dynamo.courses.data.local.dao.CourseItemDao
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@Suppress("DEPRECATION")
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var database: CoursesDatabase
    private lateinit var courseItemDao: CourseItemDao
    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, CoursesDatabase::class.java).build()
        courseItemDao = database.courseItemDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.kalum.dynamo.courses", appContext.packageName)
    }
}
