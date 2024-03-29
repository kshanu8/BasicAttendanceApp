package com.arete.basicattendanceapp.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CompanyMaster(
    @PrimaryKey(autoGenerate = true)
    //@PrimaryKey
    @NonNull
    val CMP_ID: Int = 0,
    val CMP_Name: String,
    @ColumnInfo(defaultValue = "1")
    val Status: Int,
    @ColumnInfo(defaultValue = "0")
    val Deleted: Int,
    val Add_By: String?,
    @ColumnInfo(defaultValue = "CURRENT_DATE")
    val Add_Date: String?,
    val Edit_By: String?,
    val Edit_Date: String?,
    val Delete_By: String?,
    val Delete_Date: String?
)
