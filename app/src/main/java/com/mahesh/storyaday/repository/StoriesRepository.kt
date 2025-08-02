package com.mahesh.storyaday.repository

import android.util.Log
import com.mahesh.storyaday.network.ApiGatewayInterface
import com.mahesh.storyaday.Constants
import com.mahesh.storyaday.model.Result
import com.mahesh.storyaday.model.StoryADay
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class StoriesRepository @Inject constructor(private val client: OkHttpClient): ApiGatewayInterface {
    override suspend fun fetchStories(urlString: String): Result<StoryADay> {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter: JsonAdapter<StoryADay> = moshi.adapter(StoryADay::class.java)


        val request = Request.Builder()
            .url(urlString)
            .addHeader(Constants.API_KEY_NAME, Constants.API_KEY_VALUE)
            .build()
        return try {
            val response = client.newCall(request).execute()

            checkResponse(response)

            val json = response.body?.string()

            if (response.isSuccessful) {
                val data = json?.let { jsonAdapter.fromJson(it) }!!
                Result.Success(data)
            } else {
                Result.ApiError(message = json.toString(),code = response.code)
            }
        } catch (e: Exception) {
            Result.Error(exception = e)
        }


    }
    private fun checkResponse(response: Response) {
        if(response.networkResponse == null && response.cacheResponse != null){
            Log.d("N/W or Cache", "checkResponse: Came from Cache")
        }
        else if(response.cacheResponse == null && response.networkResponse != null){
            Log.d("N/W or Cache", "checkResponse: Came from Network")
        }
        else if( response.cacheResponse != null && response.networkResponse != null){
            Log.d("N/W or Cache", "checkResponse: Conditional GET ")
        }
    }
}