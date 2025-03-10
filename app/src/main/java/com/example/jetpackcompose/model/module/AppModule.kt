package com.example.jetpackcompose.model.module

import android.content.Context
import androidx.room.Room
import com.example.jetpackcompose.manager.DataStoreManager
import com.example.jetpackcompose.model.dao.PomodoroDao
import com.example.jetpackcompose.model.dao.TaskDao
import com.example.jetpackcompose.model.database.PomodoroDatabase
import com.example.jetpackcompose.model.database.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TaskDatabase {
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "task_database"
        ).build()
    }

    @Provides
    fun provideTaskDao(database: TaskDatabase): TaskDao {
        return database.taskDao()
    }

    @Provides
    @Singleton
    fun providePomodoroDatabase(@ApplicationContext context: Context): PomodoroDatabase {
        return Room.databaseBuilder(
            context,
            PomodoroDatabase::class.java,
            "pomodoro_db"
        ).build()
    }

    @Provides
    fun providePomodoroDao(database: PomodoroDatabase): PomodoroDao {
        return database.pomodoroDao()
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }
}