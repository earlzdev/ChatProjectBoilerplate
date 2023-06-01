package com.earl.chatprojectboilerplate.data.localDataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserProfileDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewUserProfile(entity: UserProfileInfoDb)

    @Query("select * from UserProfileInfo")
    suspend fun fetchUserProfileInfo(): UserProfileInfoDb?
}