package com.kalum.dynamo.courses.data.local

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kalum.dynamo.courses.data.local.dao.CourseItemDao
import com.kalum.dynamo.courses.data.local.entity.CoursesItem
import com.kalum.dynamo.courses.data.local.converter.InstructorsListTypeConverter

@Database(
    entities = [(CoursesItem::class)],
    version = 8,
    exportSchema = false
)

@TypeConverters(InstructorsListTypeConverter::class)
abstract class CoursesDatabase : RoomDatabase() {

    abstract fun courseItemDao(): CourseItemDao

}