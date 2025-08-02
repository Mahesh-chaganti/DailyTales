package com.mahesh.storyaday.ui.presentation.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.WindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahesh.storyaday.GenresAndIcons
import com.mahesh.storyaday.R
import com.mahesh.storyaday.model.StoryADay
import com.mahesh.storyaday.viewmodel.SavedStoriesViewModel
import io.github.alexzhirkevich.cupertino.CupertinoButton
import io.github.alexzhirkevich.cupertino.CupertinoButtonSize
import io.github.alexzhirkevich.cupertino.CupertinoIcon
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.ExperimentalCupertinoApi
import io.github.alexzhirkevich.cupertino.icons.CupertinoIcons
import io.github.alexzhirkevich.cupertino.icons.filled.Bookmark
import io.github.alexzhirkevich.cupertino.icons.filled.Heart
import io.github.alexzhirkevich.cupertino.icons.outlined.Bookmark
import io.github.alexzhirkevich.cupertino.icons.outlined.Heart
import io.github.alexzhirkevich.cupertino.icons.outlined.Shareplay
import io.github.alexzhirkevich.cupertino.icons.outlined.SquareAndArrowUp


@OptIn(ExperimentalCupertinoApi::class)
@Composable
fun CommonLazyColumn(
    modifier: Modifier = Modifier,
    story: StoryADay,
    drawable: Int,
    savedStoriesViewModel: SavedStoriesViewModel
) {
    var clickLike by remember { mutableStateOf(false) }
    val savedList = savedStoriesViewModel.savedList.collectAsState().value
    val size = LocalConfiguration.current

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Column(modifier= Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier.padding(top = 24.dp).size(size.screenWidthDp.dp*3/4)) {
                    Image(
                        painter = painterResource(
                            id = when(story.theme){
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
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(size.screenWidthDp.dp*3/4)
                            .clip(
                                RoundedCornerShape(24.dp)
                            )
                    )
                }

                CupertinoText("Read time: " + story.readTime, color = Color.Gray)
                if(savedList.contains(story))
                    clickLike = true

                CupertinoButton(
                    onClick = {
                        clickLike = !clickLike
                        if (savedList.contains(story)){
                            savedStoriesViewModel.deleteSavedStory(story)
                        }
                        else
                        savedStoriesViewModel.saveStory(story)
                    },
                    size = CupertinoButtonSize.Regular,
                    modifier = Modifier.padding(4.dp)
                ) {
                    CupertinoIcon(
                        imageVector = if (!clickLike)
                            CupertinoIcons.Default.Heart
                        else
                            CupertinoIcons.Filled.Heart,
                        null,
                        modifier = Modifier.size(18.dp)
                    )
                    CupertinoText("Like")

                }

            }
        }
        item {
            Row(
                modifier = Modifier
                    .padding(top = 18.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {


                CupertinoText(
                    fontSize = 24.sp,
                    text = story.title,
                    fontWeight = FontWeight.Bold,
                    overflow = Ellipsis
                )
            }
        }
        item {
            FormattedText(story.story)
        }
    }
}