package com.arete.basicattendanceapp.viewmodel

import androidx.lifecycle.*
import com.arete.basicattendanceapp.repository.EmployeeRepository
import com.arete.basicattendanceapp.entity.EMPMaster
import kotlinx.coroutines.launch

class EMPViewModel(private val repository: EmployeeRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allEmployees: LiveData<List<EMPMaster>> = repository.allEmployees

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(empMaster: EMPMaster) = viewModelScope.launch {
        repository.insert(empMaster)
    }

    fun insertEmployee(empMaster: EMPMaster){
        viewModelScope.launch{
            repository.insert(empMaster)
        }
    }

}

class EMPViewModelFactory(private val repository: EmployeeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EMPViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EMPViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}