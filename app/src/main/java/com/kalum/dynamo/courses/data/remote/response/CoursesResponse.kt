package com.kalum.dynamo.courses.data.remote.response

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import com.google.gson.annotations.SerializedName
import com.kalum.dynamo.courses.data.local.entity.CoursesItem
import java.io.Serializable


data class CoursesResponse(

    @SerializedName("courses")
    var courses: List<CoursesItem>

) : Serializable