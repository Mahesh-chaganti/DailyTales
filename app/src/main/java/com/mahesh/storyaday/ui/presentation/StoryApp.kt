package com.mahesh.storyaday.ui.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mahesh.storyaday.MainActivity
import com.mahesh.storyaday.R
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
import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import okhttp3.internal.notify

const val MY_CHANNEL_ID = "my_channel_id"
const val NOTIFICATION_ID = 1

fun createNotificationChannel(context: Context, channelId: String, channelName: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance).apply {
            description = "Channel for app notifications" // Optional description
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

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


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NotificationButton() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // --- Runtime Permission Handling (Android 13+) ---
    val postNotificationPermission =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

    LaunchedEffect(Unit) {
        // Create channel on first composition (or move to Application class)
        createNotificationChannel(context, MY_CHANNEL_ID, "My App Notifications")
    }

    Button(onClick = {
        coroutineScope.launch {
            if (postNotificationPermission.status.isGranted) {
                showBasicNotification(context)          }
        }
    }) {
        Text(text ="Show Notification", color = Color.Blue)
    }
}

fun showBasicNotification(context: Context) {
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(
        context, 0, intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    val builder = NotificationCompat.Builder(context, MY_CHANNEL_ID)
        .setSmallIcon(R.drawable.placeholder)
        .setContentTitle("Feeling romantic?")
        .setContentText("Hop on to a short romaantic tale")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notify(NOTIFICATION_ID, builder.build())
    }
}

