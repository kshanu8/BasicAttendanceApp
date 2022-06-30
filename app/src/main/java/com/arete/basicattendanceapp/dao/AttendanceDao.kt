package com.arete.basicattendanceapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arete.basicattendanceapp.entity.Attendance

@Dao
interface AttendanceDao {

    @Insert
    fun insertAttendance(attendance: Attendance)

    @Query("SELECT * FROM Attendance WHERE EMP_Name = :name")
    fun findAttendance(name: String): List<Attendance>

    @Query("DELETE FROM Attendance WHERE EMP_Name = :name")
    fun deleteAttendance(name: String)

    @Query("SELECT * FROM Attendance")
    fun getAllAttendance(): LiveData<List<Attendance>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(attendance: Attendance)

    @Update
    suspend fun update(attendance: Attendance)

    @Delete
    suspend fun delete(attendance: Attendance)

    @Query("DELETE FROM Attendance")
    fun deleteAllAttendance()

}