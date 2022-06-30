package com.arete.basicattendanceapp.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.arete.basicattendanceapp.dao.CMPMasterDao
import com.arete.basicattendanceapp.entity.CompanyMaster

class CompanyRepository(private val cmpMasterDao: CMPMasterDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allCompanies: LiveData<List<CompanyMaster>> = cmpMasterDao.getAllCMP()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(companyMaster: CompanyMaster) {
        cmpMasterDao.insert(companyMaster)
    }

}