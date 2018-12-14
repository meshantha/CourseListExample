package com.kalum.dynamo.courses.data.local.entity

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class InstructorsItem(
    @NonNull
    @PrimaryKey
    @SerializedName("key")
    var key: String,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("bio")
    var bio: String? = null,

    @SerializedName("image")
    var image: String? = null

) : Serializable