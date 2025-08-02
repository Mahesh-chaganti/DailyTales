package com.mahesh.storyaday.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahesh.storyaday.Constants
import com.mahesh.storyaday.model.Result
import com.mahesh.storyaday.model.StoryADay
import com.mahesh.storyaday.repository.StoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class StoriesViewModel @Inject constructor(private val repository: StoriesRepository) :
    ViewModel() {
    private val _inProgress = MutableStateFlow<Boolean>(true)
    val inProgress = _inProgress.asStateFlow()

    private val _urlString = MutableStateFlow<String>("")
    val urlString = _urlString.asStateFlow()

    private val _screenName = MutableStateFlow<String>("")
    val screenName = _screenName.asStateFlow()

    private val _storiesResponseState = MutableStateFlow<StoryADay>(StoryADay())
    val storiesResponseState = _storiesResponseState.asStateFlow()

    fun fetchStories(urlString: String) {
        _inProgress.value = true
        viewModelScope.launch(Dispatchers.IO) {
            async {


                try {
                    when (val result = repository.fetchStories(urlString = urlString)) {
                        is Result.Success -> {
                            _storiesResponseState.value = result.data
                        }

                        is Result.Error -> {

                        }

                        is Result.ApiError -> {

                        }
                    }

                    progressIndicator()
                } catch (e: IOException) {
                    e.printStackTrace()
                    null
                }
            }
        }
    }

    private fun progressIndicator() {
        viewModelScope.launch(Dispatchers.IO) {
            _inProgress.value = false
        }

    }

    fun onTopicClick(topic: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _urlString.value = Constants.BASE_URL + "/stories?topic=${topic}"
            _screenName.value = topic
            if (_storiesResponseState.value == StoryADay() || _storiesResponseState.value.theme != topic)
                fetchStories(urlString.value)


        }
    }

    fun setStoryDetailStory(storyADay: StoryADay) {
        viewModelScope.launch(Dispatchers.IO) {
            _storiesResponseState.value = storyADay
        }
    }


}