package com.arete.basicattendanceapp

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arete.basicattendanceapp.adapter.EmployeeListAdapter
import com.arete.basicattendanceapp.databinding.ActivityEmplistBinding
import com.arete.basicattendanceapp.entity.EMPMaster
import com.arete.basicattendanceapp.viewmodel.EMPViewModel
import com.arete.basicattendanceapp.viewmodel.EMPViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class EMPList1Activity : AppCompatActivity() {

    //private var recyclerView: RecyclerView? = null

    private val empViewModel: EMPViewModel by viewModels() {
        EMPViewModelFactory((application as AttendanceApplication).repositoryEMP)
    }

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emplist)

        context = this@EMPList1Activity

        //val empID = findViewById<TextInputEditText>(R.id.empID)
        val name = findViewById<TextInputEditText>(R.id.name)
        val designation = findViewById<TextInputEditText>(R.id.designation)
        val male = findViewById<RadioButton>(R.id.male)
        val female = findViewById<RadioButton>(R.id.female)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        btnSave.setOnClickListener {
            /*if (empID.text.toString().trim().isEmpty()) {
                empID.error = "Please enter emp id"
            }
            else */if (name.text.toString().trim().isEmpty()) {
                name.error = "Please enter name"
            }
            else if (designation.text.toString().trim().isEmpty()) {
                designation.error = "Please enter designation"
            }
            else {
                val gender: String = if (male.isChecked){
                    "Male"
                } else{
                    "Female"
                }
                val empMaster = EMPMaster(/*empID.text.toString().trim().toInt()*/0,name.text.toString().trim(),
                    designation.text.toString().trim(),gender,0,"",1,0,"Shanu",getCurrentDate(),
                    "","","","")
                //empViewModel.insert(empMaster)
                empViewModel.insertEmployee(empMaster)
                Toast.makeText(this,"Inserted Successfully", Toast.LENGTH_LONG).show()
            }
        }

        val adapter = EmployeeListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

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