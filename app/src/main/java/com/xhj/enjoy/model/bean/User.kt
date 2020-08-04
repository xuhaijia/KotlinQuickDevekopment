package com.xhj.enjoy.model.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(val collectIds: List<Int>,
                val email: String,
                val icon: String,
                val id: Int,
                val password: String,
                val type: Int,
                val username: String)