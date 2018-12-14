package com.kalum.dynamo.courses.data.local.dao

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kalum.dynamo.courses.data.local.entity.CoursesItem

@Dao
interface CourseItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(coursesItem: List<CoursesItem>)

    @Query("SELECT * FROM ${CoursesItem.COURSE_ITEM_TABLE_NAME}")
    fun getAllCourses(): LiveData<List<CoursesItem>>

    @Query("SELECT * FROM ${CoursesItem.COURSE_ITEM_TABLE_NAME} WHERE $COLUMN_KEY = :key")
    fun getCourseByKey(key: String): LiveData<CoursesItem>

    companion object {
        const val COLUMN_KEY = "key"
    }
}