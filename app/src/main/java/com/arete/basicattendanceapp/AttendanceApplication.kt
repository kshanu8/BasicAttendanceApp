package com.arete.basicattendanceapp

import android.app.Application
import com.arete.basicattendanceapp.db.AttRoomDatabase
import com.arete.basicattendanceapp.repository.CompanyRepository
import com.arete.basicattendanceapp.repository.EmployeeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class AttendanceApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AttRoomDatabase.getDatabase(this,applicationScope) }
    val repositoryEMP by lazy { EmployeeRepository(database.empMasterDao(),database.cmpMasterDao()) }
    val repositoryCMP by lazy { CompanyRepository(database.cmpMasterDao()) }
}
