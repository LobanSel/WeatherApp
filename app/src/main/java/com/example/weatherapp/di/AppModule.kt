package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.Room.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
@Provides
    fun provideDB(@ApplicationContext context: Context): WeatherDatabase
    {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "WeatherDB"
        ).build()
    }
}