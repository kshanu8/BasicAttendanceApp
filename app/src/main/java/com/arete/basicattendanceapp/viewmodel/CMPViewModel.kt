package com.arete.basicattendanceapp.viewmodel

import androidx.lifecycle.*
import com.arete.basicattendanceapp.entity.CompanyMaster
import com.arete.basicattendanceapp.repository.CompanyRepository
import kotlinx.coroutines.launch

class CMPViewModel(private val repository: CompanyRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allCompanies: LiveData<List<CompanyMaster>> = repository.allCompanies

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(companyMaster: CompanyMaster) = viewModelScope.launch {
        repository.insert(companyMaster)
    }

    fun insertCompany(companyMaster: CompanyMaster){
        viewModelScope.launch{
            repository.insert(companyMaster)
        }
    }

}

class CMPViewModelFactory(private val repository: CompanyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CMPViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CMPViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}