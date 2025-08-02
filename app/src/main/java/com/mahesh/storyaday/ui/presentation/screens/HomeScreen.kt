package com.mahesh.storyaday.ui.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mahesh.storyaday.GenresAndIcons
import com.mahesh.storyaday.viewmodel.StoriesViewModel
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.ExperimentalCupertinoApi

@OptIn(ExperimentalCupertinoApi::class)
@Composable
fun HomeScreen(navController: NavController, storiesViewModel: StoriesViewModel) {
    val size = LocalConfiguration.current


    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        columns = GridCells.Fixed(2)
    ) {
        val genresShuffled = GenresAndIcons.shuffled()
        items(genresShuffled,key = {it.title}) { item ->
            Box(
                modifier = Modifier.padding(12.dp)
                    .size(size.screenWidthDp.dp/2,size.screenHeightDp.dp/4)
                    .clip(
                        RoundedCornerShape(24.dp)
                    )
                    .clickable {
                        navController.navigate("Story Detail")
                        storiesViewModel.onTopicClick(item.title)
                    }
            ) {
                AsyncImage(
                    model = item.imageRes, null,
                    modifier = Modifier.align(Alignment.Center),
                    contentScale = ContentScale.Crop

                )
                CupertinoText(
                    item.title,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}
