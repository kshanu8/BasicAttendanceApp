package com.arete.basicattendanceapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arete.basicattendanceapp.adapter.CompanyListAdapter
import com.arete.basicattendanceapp.adapter.EmployeeListAdapter
import com.arete.basicattendanceapp.databinding.ActivityAddEmployeeBinding
import com.arete.basicattendanceapp.databinding.ActivityCompanyBinding
import com.arete.basicattendanceapp.entity.CompanyMaster
import com.arete.basicattendanceapp.entity.EMPMaster
import com.arete.basicattendanceapp.viewmodel.CMPViewModel
import com.arete.basicattendanceapp.viewmodel.CMPViewModelFactory
import com.arete.basicattendanceapp.viewmodel.EMPViewModel
import com.arete.basicattendanceapp.viewmodel.EMPViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class AddCompanyActivity: AppCompatActivity() {

    private var _binding: ActivityCompanyBinding? = null
    private val binding get() = _binding!!

    private val cmpViewModel: CMPViewModel by viewModels() {
        CMPViewModelFactory((application as AttendanceApplication).repositoryCMP)
    }

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this@AddCompanyActivity

        binding.btnSave.setOnClickListener {
            if (binding.name.text.toString().trim().isEmpty()) {
                binding.name.error = "Please enter company name"
            } else {
                val cmpMaster = CompanyMaster(CMP_Name = binding.name.text.toString().trim(),
                    Status = 1, Deleted = 0, Add_By = "Shanu", Add_Date = getCurrentDate(),
                    Edit_By = "", Edit_Date = "", Delete_By = "", Delete_Date = "")
                cmpViewModel.insertCompany(cmpMaster)
                Toast.makeText(this,"Company Inserted Successfully", Toast.LENGTH_LONG).show()
                binding.name.setText("")
            }
        }

        val adapter = CompanyListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        /*empViewModel.allEmployees.observe(this, Observer { employees -> employees.let {
            adapter.submitList(it)
        } })*/

        cmpViewModel.allCompanies.observe(this) { companies ->
            companies.let {
                adapter.submitList(it)
            }
        }


    }

    private fun getCurrentDate(): String {
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")
        return simpleDateFormat.format(Date())
    }

}