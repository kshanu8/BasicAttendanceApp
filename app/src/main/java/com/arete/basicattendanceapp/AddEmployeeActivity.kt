package com.arete.basicattendanceapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.arete.basicattendanceapp.databinding.ActivityAddEmployeeBinding
import com.arete.basicattendanceapp.viewmodel.EMPViewModel
import com.arete.basicattendanceapp.viewmodel.EMPViewModelFactory
import com.arete.basicattendanceapp.entity.EMPMaster
import java.text.SimpleDateFormat
import java.util.*

class AddEmployeeActivity: AppCompatActivity() {

    private var _binding: ActivityAddEmployeeBinding? = null
    private val binding get() = _binding!!

    //lateinit var empViewModel: EMPViewModel
    private val empViewModel: EMPViewModel by viewModels() {
        EMPViewModelFactory((application as AttendanceApplication).repositoryEMP)
    }

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this@AddEmployeeActivity
        //empViewModel = ViewModelProvider(this).get(EMPViewModel::class.java)

        binding.btnSave.setOnClickListener {
            /*if (binding.empID.text.toString().trim().isEmpty()){
                Toast.makeText(this,"Please enter emp id",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }*/

            /*if (binding.empID.text.toString().trim().isEmpty()) {
                binding.empID.error = "Please enter emp id"
            }
            else if (binding.name.text.toString().trim().isEmpty()) {
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
                empViewModel.insertData(context, binding.empID.text.toString().trim().toInt(), binding.name.text.toString().trim(),
                    binding.designation.text.toString().trim(),gender)
                Toast.makeText(this,"Inserted Successfully",Toast.LENGTH_LONG).show()
            }*/

            when {
                binding.empID.text.toString().trim().isEmpty() -> {
                    binding.empID.error = "Please enter emp id"
                }
                binding.name.text.toString().trim().isEmpty() -> {
                    binding.name.error = "Please enter name"
                }
                binding.designation.text.toString().trim().isEmpty() -> {
                    binding.designation.error = "Please enter designation"
                }
                else -> {
                    val gender: String = if (binding.male.isChecked){
                        "Male"
                    } else{
                        "Female"
                    }
                    val empMaster = EMPMaster(binding.empID.text.toString().trim().toInt(),binding.name.text.toString().trim(),
                        binding.designation.text.toString().trim(),gender,0,"",1,0,"Shanu",getCurrentDate(),
                    "","","","")
                    //empViewModel.insert(empMaster)
                    empViewModel.insertEmployee(empMaster)
                    Toast.makeText(this,"Inserted Successfully",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun getCurrentDate(): String {
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")
        return simpleDateFormat.format(Date())
    }

}