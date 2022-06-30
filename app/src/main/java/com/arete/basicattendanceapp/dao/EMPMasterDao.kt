package com.arete.basicattendanceapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arete.basicattendanceapp.entity.EMPMaster

@Dao
interface EMPMasterDao {

    @Insert
    fun insertEMP(empMaster: EMPMaster)

    @Query("SELECT * FROM EMPMaster WHERE EMP_ID = :EMP_ID")
    fun findEMP(EMP_ID: Int): LiveData<List<EMPMaster>>

    @Query("SELECT * FROM EMPMaster WHERE EMP_Name = :EMP_Name")
    fun getEMP(EMP_Name: String): LiveData<EMPMaster>

    @Query("DELETE FROM EMPMaster WHERE EMP_ID = :ID")
    fun deleteEMP(ID: Int)

    @Query("SELECT * FROM EMPMaster")
    fun getAllEMP(): LiveData<List<EMPMaster>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(empMaster: EMPMaster)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(empMasterList: List<EMPMaster>)

    @Update
    suspend fun update(empMaster: EMPMaster)

    @Delete
    suspend fun delete(empMaster: EMPMaster)

}