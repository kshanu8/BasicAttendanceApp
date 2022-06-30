package com.arete.basicattendanceapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.arete.basicattendanceapp.dao.AttendanceDao
import com.arete.basicattendanceapp.dao.CMPMasterDao
import com.arete.basicattendanceapp.dao.EMPMasterDao
import com.arete.basicattendanceapp.entity.Attendance
import com.arete.basicattendanceapp.entity.CompanyMaster
import com.arete.basicattendanceapp.entity.EMPMaster
import kotlinx.coroutines.CoroutineScope

@Database(entities = [EMPMaster::class, Attendance::class, CompanyMaster::class], version = 2, exportSchema = true)
abstract class AttRoomDatabase : RoomDatabase(){
    abstract fun empMasterDao(): EMPMasterDao
    abstract fun attendanceDao(): AttendanceDao
    abstract fun cmpMasterDao(): CMPMasterDao

    companion object {

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS CompanyMaster (CMP_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, CMP_Name TEXT NOT NULL, Status INTEGER NOT NULL DEFAULT 1, Deleted INTEGER NOT NULL DEFAULT 0, Add_By TEXT, Add_Date TEXT DEFAULT CURRENT_DATE, Edit_By TEXT, Edit_Date TEXT, Delete_By TEXT, Delete_Date TEXT)")
            }
        }

        /*val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `CompanyMaster` (`CMP_ID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `CMP_Name` TEXT NOT NULL, `Status` INTEGER NOT NULL DEFAULT 1, `Deleted` INTEGER NOT NULL DEFAULT 0, `Add_By` TEXT, `Add_Date` TEXT DEFAULT CURRENT_DATE, `Edit_By` TEXT, `Edit_Date` TEXT, `Delete_By` TEXT, `Delete_Date` TEXT)")
            }
        }*/

        /*val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `CompanyMaster` (`CMP_ID` INTEGER , `CMP_Name` TEXT NOT NULL, `Status` INTEGER NOT NULL DEFAULT 1, `Deleted` INTEGER NOT NULL DEFAULT 0, `Add_By` TEXT, `Add_Date` TEXT DEFAULT CURRENT_DATE, `Edit_By` TEXT, `Edit_Date` TEXT, `Delete_By` TEXT, `Delete_Date` TEXT,PRIMARY KEY(`CMP_ID`))")
            }
        }*/

        @Volatile
        private var INSTANCE: AttRoomDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope): AttRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AttRoomDatabase::class.java,
                    "attendance_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    //.fallbackToDestructiveMigration()
                    .addMigrations(MIGRATION_1_2)
                    .addCallback(AttendanceDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class AttendanceDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.

                /*INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.attendanceDao())
                    }
                }*/

            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(attendanceDao: AttendanceDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            attendanceDao.deleteAllAttendance()
        }

    }

}