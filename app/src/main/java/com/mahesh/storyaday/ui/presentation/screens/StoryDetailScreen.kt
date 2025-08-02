package com.mahesh.storyaday.ui.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mahesh.storyaday.GenresAndIcons
import com.mahesh.storyaday.R
import com.mahesh.storyaday.viewmodel.StoriesViewModel
import com.mahesh.storyaday.ui.presentation.uicomponents.CommonLazyColumn
import com.mahesh.storyaday.viewmodel.SavedStoriesViewModel
import io.github.alexzhirkevich.cupertino.CupertinoActivityIndicator
import io.github.alexzhirkevich.cupertino.ExperimentalCupertinoApi

@OptIn(ExperimentalCupertinoApi::class)
@Composable
fun StoryDetailScreen(navController: NavController,
                 storiesViewModel: StoriesViewModel,
                 savedStoriesViewModel: SavedStoriesViewModel
) {
    val inProgress = storiesViewModel.inProgress.collectAsState().value
    val story = storiesViewModel.storiesResponseState.collectAsState().value


    Box(modifier = Modifier.fillMaxSize())
    {
        if(inProgress){
            CupertinoActivityIndicator(modifier = Modifier.align(Alignment.Center) )
        }
        else
           CommonLazyColumn(story = story, drawable = GenresAndIcons.find { it.title == story.title }?.imageRes?:R.drawable.placeholder, savedStoriesViewModel = savedStoriesViewModel)
    }


}
