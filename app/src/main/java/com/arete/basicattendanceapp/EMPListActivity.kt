package com.arete.basicattendanceapp

import android.R
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arete.basicattendanceapp.adapter.EmployeeListAdapter
import com.arete.basicattendanceapp.databinding.ActivityCompanyBinding
import com.arete.basicattendanceapp.databinding.ActivityEmplistBinding
import com.arete.basicattendanceapp.entity.EMPMaster
import com.arete.basicattendanceapp.viewmodel.EMPViewModel
import com.arete.basicattendanceapp.viewmodel.EMPViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class EMPListActivity : AppCompatActivity() {

    //private var recyclerView: RecyclerView? = null
    private var _binding: ActivityEmplistBinding?=null
    private val binding get() = _binding!!

    private val empViewModel: EMPViewModel by viewModels() {
        EMPViewModelFactory((application as AttendanceApplication).repositoryEMP)
    }

    lateinit var context: Context
    val arrayList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_emplist)
        _binding = ActivityEmplistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this@EMPListActivity

        /*val empID = findViewById<TextInputEditText>(R.id.empID)
        val name = findViewById<TextInputEditText>(R.id.name)
        val designation = findViewById<TextInputEditText>(R.id.designation)
        val male = findViewById<RadioButton>(R.id.male)
        val female = findViewById<RadioButton>(R.id.female)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)*/
        arrayList.add("Select")
        empViewModel.allCompanyList.observe(this){company ->
            company.let {
                for (companyName in it){
                    arrayList.add(companyName)
                }
            }
        }

        val adapterCmp = ArrayAdapter(this, R.layout.simple_spinner_item, arrayList)
        binding.spinnerCompany.adapter = adapterCmp

        binding.btnSave.setOnClickListener {
            if (binding.name.text.toString().trim().isEmpty()) {
                binding.name.error = "Please enter name"
            }
            else if (binding.designation.text.toString().trim().isEmpty()) {
                binding.designation.error = "Please enter designation"
            }
            else {
                val gender: String = if (binding.male.isChecked){
                    "Male"
                } else{
                    "Female"
                }
                val empMaster = EMPMaster(EMP_Name = binding.name.text.toString().trim(),
                    Designation = binding.designation.text.toString().trim(), Gender = gender, Status = 1,
                    Deleted = 0, Add_By = "Shanu", Add_Date = getCurrentDate(),
                    Edit_By = "", Edit_Date = "", Delete_By = "", Delete_Date = "")
                //empViewModel.insert(empMaster)
                empViewModel.insertEmployee(empMaster)
                Toast.makeText(this,"Inserted Successfully", Toast.LENGTH_LONG).show()
                binding.name.setText("")
            }
        }

        val adapter = EmployeeListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        /*empViewModel.allEmployees.observe(this, Observer { employees -> employees.let {
            adapter.submitList(it)
        } })*/

        empViewModel.allEmployees.observe(this) { employees ->
            employees.let {
                adapter.submitList(it)
            }
        }

    }

    private fun getCurrentDate(): String {
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")
        return simpleDateFormat.format(Date())
    }

}