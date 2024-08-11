package com.example.suitmediaaplication.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.suitmediaaplication.data.DataItem

@Dao
interface UserDao {
    @Query("SELECT * FROM list_user")
    fun getAllUser(): PagingSource<Int, DataItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: List<DataItem>)

    @Query("DELETE FROM list_user")
    suspend fun deleteAll()
}
