package com.kalum.dynamo.courses.data.local.converter

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kalum.dynamo.courses.data.local.entity.InstructorsItem

class InstructorsListTypeConverter {

    @TypeConverter
    fun toAffiliatesList(  instructorsListAsString : String? ) :  List<InstructorsItem> {
        return Gson().fromJson<List<InstructorsItem>>( instructorsListAsString!! )
    }

    @TypeConverter
    fun fromAffiliatesList( affiliatesList : List<InstructorsItem> ) : String? {
        return Gson().toJson( affiliatesList )
    }

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

}