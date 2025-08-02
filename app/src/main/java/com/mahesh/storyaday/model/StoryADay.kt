package com.mahesh.storyaday.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "storyaday")
data class StoryADay(
    @PrimaryKey
    @ColumnInfo(name = "_id")
    val _id: String = "",
    @ColumnInfo(name = "theme")
    val theme: String = "",
    @ColumnInfo(name = "title")
    val title: String =  "",
    @ColumnInfo(name = "readTime")
    val readTime: String =  "",
    @ColumnInfo(name = "story")
    val story: String = ""
)
