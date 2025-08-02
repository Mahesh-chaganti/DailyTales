package com.mahesh.storyaday.ui.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mahesh.storyaday.viewmodel.SavedStoriesViewModel
import com.mahesh.storyaday.viewmodel.StoriesViewModel
import io.github.alexzhirkevich.cupertino.CupertinoIcon
import io.github.alexzhirkevich.cupertino.CupertinoNavigateBackButton
import io.github.alexzhirkevich.cupertino.CupertinoNavigationBar
import io.github.alexzhirkevich.cupertino.CupertinoNavigationBarItem
import io.github.alexzhirkevich.cupertino.CupertinoScaffold
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.CupertinoTopAppBar
import io.github.alexzhirkevich.cupertino.ExperimentalCupertinoApi
import io.github.alexzhirkevich.cupertino.icons.CupertinoIcons
import io.github.alexzhirkevich.cupertino.icons.filled.Heart
import io.github.alexzhirkevich.cupertino.icons.filled.House
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme
import androidx.compose.runtime.Composable


@OptIn(ExperimentalCupertinoApi::class)
@Composable
fun App(storiesViewModel: StoriesViewModel,savedStoriesViewModel: SavedStoriesViewModel) {

    val screenName = storiesViewModel.screenName.collectAsState().value
    CupertinoTheme {
        val navController = rememberNavController()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val screens = listOf("Home", "Liked Stories")
        CupertinoScaffold(
            Modifier.fillMaxSize(),
            topBar = {
                when (currentDestination?.route) {
                    "Home" -> {
                        CupertinoTopAppBar(
                            title = { CupertinoText(text = "Home") },

                            )
                    }

                    "Liked Stories" -> {
                        CupertinoTopAppBar(
                            title = { CupertinoText(text = "Liked Stories") },

                            )
                    }



                    else -> {
                        currentDestination?.route?.let {


                            CupertinoTopAppBar(
                                title = { CupertinoText(text = screenName) },
                                navigationIcon = {
                                    CupertinoNavigateBackButton(
                                        title = { CupertinoText("Back") },
                                        onClick = { navController.popBackStack() })
                                }
                            )
                        }
                    }
                }
            },
            bottomBar = {
                if (currentDestination?.route == "Home" || currentDestination?.route == "Liked Stories") {
                    CupertinoNavigationBar {
                        screens.forEachIndexed { index, screen ->
                            CupertinoNavigationBarItem(
                                icon = {
                                    when (screen) {
                                        "Home" -> CupertinoIcon(
                                            CupertinoIcons.Filled.House,
                                            null
                                        )

                                        "Liked Stories" -> CupertinoIcon(
                                            CupertinoIcons.Filled.Heart,
                                            null
                                        )


                                    }
                                },
                                label = { CupertinoText(screen) },
                                selected = currentDestination?.hierarchy?.any { it.route == screen } == true,
                                onClick = {
                                    navController.navigate(screen) {

                                        navController.graph.findStartDestination().route?.let {
                                            popUpTo(it) {
                                                saveState = true
                                            }
                                        }

                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            }) { padding ->

            MyNavHost(
                innerPadding = padding,
                navController = navController,
                storiesViewModel = storiesViewModel,
                savedStoriesViewModel = savedStoriesViewModel
            )

        }
    }
}


