package com.mahesh.storyaday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mahesh.storyaday.ui.presentation.App
import com.mahesh.storyaday.ui.theme.StoryADayTheme
import com.mahesh.storyaday.viewmodel.SavedStoriesViewModel
import com.mahesh.storyaday.viewmodel.StoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val storiesViewModel by viewModels<StoriesViewModel>()
    private val savedStoriesViewModel by viewModels<SavedStoriesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            StoryADayTheme {
                App(storiesViewModel = storiesViewModel, savedStoriesViewModel = savedStoriesViewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StoryADayTheme {
        Greeting("Android")
    }
}