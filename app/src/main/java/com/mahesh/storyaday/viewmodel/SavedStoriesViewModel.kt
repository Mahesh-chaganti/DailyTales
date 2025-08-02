package com.mahesh.storyaday.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahesh.storyaday.model.StoryADay
import com.mahesh.storyaday.repository.StoriesDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedStoriesViewModel @Inject constructor(private val storiesDbRepository: StoriesDbRepository): ViewModel()
{
    private val _savedList = MutableStateFlow<List<StoryADay>>(emptyList())
    val savedList = _savedList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            async {
                storiesDbRepository.getAllSavedStories().distinctUntilChanged()
                    .collect { savedStories ->
                        if (savedStories.isEmpty()) {
                            _savedList.value = emptyList()
                        } else {
                            _savedList.value = savedStories
                        }
                    }
            }
        }
    }

    fun saveStory(storyADay: StoryADay){
        viewModelScope.launch(Dispatchers.IO) {
            storiesDbRepository.insertSavedStory(storyADay)
        }
    }

    fun deleteSavedStory(storyADay: StoryADay){
        viewModelScope.launch(Dispatchers.IO) {
            storiesDbRepository.deleteSavedStory(storyADay)
        }
    }
}