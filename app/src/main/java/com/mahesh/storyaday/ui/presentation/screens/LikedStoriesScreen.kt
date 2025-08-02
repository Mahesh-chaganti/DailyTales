package com.mahesh.storyaday.ui.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mahesh.storyaday.GenresAndIcons
import com.mahesh.storyaday.R
import com.mahesh.storyaday.ui.presentation.Screens
import com.mahesh.storyaday.viewmodel.SavedStoriesViewModel
import com.mahesh.storyaday.viewmodel.StoriesViewModel
import io.github.alexzhirkevich.cupertino.CupertinoIcon
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.icons.CupertinoIcons
import io.github.alexzhirkevich.cupertino.icons.filled.DeleteLeft
import io.github.alexzhirkevich.cupertino.icons.filled.Trash
import io.github.alexzhirkevich.cupertino.icons.filled.TrashSlash

@Composable
fun LikedStoriesScreen(savedStoriesViewModel: SavedStoriesViewModel,
                       storiesViewModel: StoriesViewModel,
                       navController: NavController) {
    val stories = savedStoriesViewModel.savedList.collectAsState().value


    Box(modifier = Modifier.fillMaxSize())
    {
        if (stories.isNotEmpty()) {
            LazyColumn {
                items(stories) { story ->
                    Row(
                        Modifier.padding(24.dp).fillMaxWidth().height(50.dp).clickable {
                            navController.navigate(Screens.StoryDetailScreen.title)
                            storiesViewModel.setStoryDetailStory(story)
                        },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {


                        Image(
                            painter = painterResource(
                                id = when (story.theme) {
                                    "Horror" -> R.drawable.horror
                                    "Action" -> R.drawable.action
                                    "Adventure" -> R.drawable.adventure
                                    "Crime" -> R.drawable.crime
                                    "Drama" -> R.drawable.drama
                                    "Fantasy" -> R.drawable.fantasy
                                    "Feelgood" -> R.drawable.feelgood
                                    "Historical" -> R.drawable.historical
                                    "Motivational" -> R.drawable.motivational
                                    "Mystery" -> R.drawable.mystery
                                    "Romance" -> R.drawable.romance
                                    "Sci-fi" -> R.drawable.sci_fi
                                    "Thriller" -> R.drawable.thriller
                                    "Friendship" -> R.drawable.friendship
                                    else -> R.drawable.placeholder
                                }
                            ), null,
                            modifier = Modifier.size(48.dp)
                        )
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                                CupertinoText(text = story.title)
                                CupertinoText(text = story.theme, color = Color.Gray, fontSize = 16.sp)

                            }

                            CupertinoIcon(
                                CupertinoIcons.Filled.Trash,
                                null,
                                modifier = Modifier.size(24.dp).clickable {
                                    savedStoriesViewModel.deleteSavedStory(story)
                                }
                            )
                        }

                    }
                }
            }


        } else {
            Text("Nothing to show", modifier = Modifier.align(Alignment.Center))
        }
    }
}