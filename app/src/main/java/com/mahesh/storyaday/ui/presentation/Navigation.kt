package com.mahesh.storyaday.ui.presentation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mahesh.storyaday.ui.presentation.screens.HomeScreen
import com.mahesh.storyaday.ui.presentation.screens.LikedStoriesScreen
import com.mahesh.storyaday.ui.presentation.screens.StoryDetailScreen
import com.mahesh.storyaday.viewmodel.SavedStoriesViewModel
import com.mahesh.storyaday.viewmodel.StoriesViewModel

enum class Screens(val title: String) {
    HomeScreen("Home" ),
    LikedStoriesScreen("Liked Stories"),
    StoryDetailScreen("Story Detail")
}



@Composable
fun MyNavHost(
    innerPadding: PaddingValues = PaddingValues(),
    navController: NavHostController,
    storiesViewModel: StoriesViewModel
    ,savedStoriesViewModel: SavedStoriesViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.title,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)

    ) {
        composable(route = Screens.HomeScreen.title,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }
            ,
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            HomeScreen(navController, storiesViewModel)
        }
        composable(route = Screens.LikedStoriesScreen.title,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }
            ,
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            LikedStoriesScreen(savedStoriesViewModel = savedStoriesViewModel, storiesViewModel = storiesViewModel, navController = navController)
        }
        composable(route = Screens.StoryDetailScreen.title,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }
                ,
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            StoryDetailScreen(navController = navController, storiesViewModel,savedStoriesViewModel)
        }



    }
}
