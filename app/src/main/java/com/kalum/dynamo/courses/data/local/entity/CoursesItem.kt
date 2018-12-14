package com.kalum.dynamo.courses.data.local.entity

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity( tableName = "courses_item", indices = [ (Index("key")) ] )
data class CoursesItem  (

    @NonNull
    @PrimaryKey
    @SerializedName("key")
    var key: String,

    @SerializedName("syllabus")
    var syllabus: String? = null,

    @SerializedName("featured")
    var isFeatured: Boolean = false,

    @SerializedName("expected_duration_low")
    var expectedDurationLow: String? = null,

    @SerializedName("capped")
    var isCapped: Boolean = false,


    @SerializedName("public_listing")
    var isPublicListing: Boolean = false,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("project_name")
    var projectName: String? = null,

    @SerializedName("faq")
    var faq: String? = null,

    @SerializedName("expected_duration")
    var expectedDuration: String? = null,

    @SerializedName("expected_duration_unit")
    var expectedDurationUnit: String? = null,

    @SerializedName("required_knowledge")
    var requiredKnowledge: String? = null,

    @SerializedName("banner_image")
    var bannerImage: String? = null,

    @SerializedName("short_summary")
    var shortSummary: String? = null,

    @SerializedName("slug")
    var slug: String? = null,

    @SerializedName("summary")
    var summary: String? = null,

    @SerializedName("enables_profiles")
    var isEnablesProfiles: Boolean = false,

    @SerializedName("image")
    var image: String? = null,

    @SerializedName("starter")
    var isStarter: Boolean = false,

    @SerializedName("level")
    var level: String? = null,

    @SerializedName("open_for_enrollment")
    var isOpenForEnrollment: Boolean = false,

    @SerializedName("expected_duration_high")
    var expectedDurationHigh: String? = null,

    @SerializedName("full_course_available")
    var isFullCourseAvailable: Boolean = false,

    @SerializedName("project_description")
    var projectDescription: String? = null,

    @SerializedName("new_release")
    var isNewRelease: Boolean = false,

    @SerializedName("expected_learning")
    var expectedLearning: String? = null,

    @SerializedName("subtitle")
    var subtitle: String? = null,


    @SerializedName("instructors")
    var instructors: List<InstructorsItem>? = null




)




    : Serializable {
    constructor() : this(
               "",
            "",
                  false,
            "",
                false,
            false,"",null,"","","",null,
"","","","",false,"",false,
            "",false,"",false,"",false,"","",null
            )


    companion object {
        const val COURSE_ITEM_TABLE_NAME = "courses_item"
    }
}