package com.arete.basicattendanceapp.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.arete.basicattendanceapp.dao.CMPMasterDao
import com.arete.basicattendanceapp.dao.EMPMasterDao
import com.arete.basicattendanceapp.entity.CompanyMaster
import com.arete.basicattendanceapp.entity.EMPMaster

class EmployeeRepository(private val empMasterDao: EMPMasterDao,private val cmpMasterDao: CMPMasterDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allEmployees: LiveData<List<EMPMaster>> = empMasterDao.getAllEMP()

    val allCompanies: LiveData<List<String>> = cmpMasterDao.getAllCMPName()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(empMaster: EMPMaster) {
        empMasterDao.insert(empMaster)
    }

}