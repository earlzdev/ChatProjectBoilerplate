package com.earl.chatprojectboilerplate.data.localDataSource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.earl.chatprojectboilerplate.data.localDataSource.mappers.UserProfileDbToMainMapper

@Entity(tableName = "UserProfileInfo")
data class UserProfileInfoDb(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "birthday") val birthday: String,
    @ColumnInfo(name = "about") val about: String
) {
    fun <T> map(mapper: UserProfileDbToMainMapper<T>) =
        mapper.map(
            id,
            username,
            phone,
            city,
            birthday,
            about
        )
}
