package com.mahesh.storyaday.repository

import com.mahesh.storyaday.StoryDao
import com.mahesh.storyaday.model.StoryADay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoriesDbRepository @Inject constructor(private val roomDao: StoryDao) {
     fun getAllSavedStories(): Flow<List<StoryADay>> = roomDao.getAllSavedStories()

     suspend fun insertSavedStory(storyADay: StoryADay) = roomDao.insertSavedStory(storyADay = storyADay)

     suspend fun deleteSavedStory(storyADay: StoryADay) = roomDao.deleteSavedStory(storyADay = storyADay)
}