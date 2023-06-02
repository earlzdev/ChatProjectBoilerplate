package com.earl.chatprojectboilerplate.data.localDataSource

import androidx.room.*

@Dao
interface UserProfileDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewUserProfile(entity: UserProfileInfoDb)

    @Query("select * from UserProfileInfo")
    suspend fun fetchUserProfileInfo(): UserProfileInfoDb?

    @Query("update UserProfileInfo set city =:newCity, birthday =:newbd, about =:newAbout, vk =:newvk, inst =:newinst, avatar =:newAvatar")
    suspend fun updateUserProfile(newCity: String, newbd: String, newAbout: String, newvk: String, newinst: String, newAvatar: String)
}
