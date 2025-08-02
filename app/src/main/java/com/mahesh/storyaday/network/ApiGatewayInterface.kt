package com.mahesh.storyaday.network

import com.mahesh.storyaday.model.StoryADay
import com.mahesh.storyaday.model.Result

interface ApiGatewayInterface {
    suspend fun fetchStories(urlString: String): Result<StoryADay>
}