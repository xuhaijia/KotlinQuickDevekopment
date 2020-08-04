package com.xhj.enjoy.model.bean

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Author: 7
 * Create: 2020/5/9 15:31
 */
@Dao
interface UserDao {
    @Query("select * from user")
    suspend fun getAll(): List<User>

    @Insert
    suspend fun insertAll(vararg user: User)

    @Delete
    suspend fun deleteAll(vararg user: User)
}