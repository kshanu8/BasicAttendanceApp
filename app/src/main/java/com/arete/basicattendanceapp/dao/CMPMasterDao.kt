package com.arete.basicattendanceapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arete.basicattendanceapp.entity.CompanyMaster

@Dao
interface CMPMasterDao {

    @Query("SELECT * FROM CompanyMaster")
    fun getAllCMP(): LiveData<List<CompanyMaster>>

    @Query("SELECT CMP_Name FROM CompanyMaster Where Status = 1 and Deleted = 0")
    fun getAllCMPName(): LiveData<List<String>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(companyMaster: CompanyMaster)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(companyMasterList: List<CompanyMaster>)

    @Update
    suspend fun update(companyMaster: CompanyMaster)

    @Delete
    suspend fun delete(companyMaster: CompanyMaster)

}