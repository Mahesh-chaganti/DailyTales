package com.mahesh.storyaday

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mahesh.storyaday.model.StoryADay
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    @Query("SELECT * FROM STORYADAY")
    fun getAllSavedStories(): Flow<List<StoryADay>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedStory(storyADay: StoryADay)

    @Delete
    suspend fun deleteSavedStory(storyADay: StoryADay)


}