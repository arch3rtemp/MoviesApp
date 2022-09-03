package com.arch3rtemp.android.moviesapp.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class AllTypeConverters() {

    @Inject
    lateinit var gson: Gson

    @TypeConverter
    fun stringsToJson(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun jsonToStrings(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }
}