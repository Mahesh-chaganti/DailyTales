package com.mahesh.storyaday

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiClientModule {

    @Singleton
    @Provides
    fun provideHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val cacheSize = (10 * 1024 * 1024).toLong()
        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(logging)
            .cache(Cache(File(context.cacheDir, "http_cache"), cacheSize))
            .build()
    }

    @Singleton
    @Provides
    fun provideStoryDao(storyDatabase: StoryDatabase): StoryDao
        = storyDatabase.storyDao()



    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): StoryDatabase
            = Room.databaseBuilder(
        context,
        StoryDatabase::class.java,
        "story_database")
        .fallbackToDestructiveMigration()
        .build()
}