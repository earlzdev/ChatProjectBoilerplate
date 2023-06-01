package com.earl.chatprojectboilerplate.data.localDataSource

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
    UserProfileInfoDb::class
], exportSchema = false, version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userProfileDap(): UserProfileDao
}

fun buildAppDatabase(app: Application) = Room.databaseBuilder(app, AppDatabase::class.java, "appDb")
    .fallbackToDestructiveMigration()
    .allowMainThreadQueries()
    .build()