package com.mahesh.storyaday

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mahesh.storyaday.model.StoryADay

@Database(entities = [StoryADay::class], version = 1)
abstract class StoryDatabase: RoomDatabase() {
    abstract fun storyDao(): StoryDao
}