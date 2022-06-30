package com.arete.basicattendanceapp.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Attendance")
data class Attendance(
    @PrimaryKey(autoGenerate = true)
    //@PrimaryKey
    @NonNull
    @ColumnInfo(name = "AT_ID")
    val AT_ID: Int,

    @ColumnInfo(name = "EMP_ID")
    val EMP_ID: Int,

    @ColumnInfo(name = "EMP_Name")
    val EMP_Name: String,

    @ColumnInfo(name = "Status", defaultValue = "1")
    val Status: Int,

    @ColumnInfo(name = "Deleted", defaultValue = "0")
    val Deleted: Int,

    @ColumnInfo(name = "Add_By")
    val Add_By: String?,

    @ColumnInfo(name = "Add_Date",defaultValue = "CURRENT_DATE")
    val Add_Date: String?,

    @ColumnInfo(name = "Edit_By")
    val Edit_By: String?,

    @ColumnInfo(name = "Edit_Date")
    val Edit_Date: String?,

    @ColumnInfo(name = "Delete_By")
    val Delete_By: String?,

    @ColumnInfo(name = "Delete_Date")
    val Delete_Date: String?

)
