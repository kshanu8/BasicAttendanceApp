package com.arete.basicattendanceapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arete.basicattendanceapp.databinding.ActivitySelectionBinding

class SelectionActivity : AppCompatActivity() {

    private var _binding: ActivitySelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.card.setOnClickListener {
            val intent = Intent(this, AddEmployeeActivity::class.java).apply {
                putExtra("EXTRA_MESSAGE", "message")
            }
            startActivity(intent)
            /*lifecycleScope.launch {
                save("", "")
            }*/
        }

        binding.cardCmp.setOnClickListener{
            val intent = Intent(this, AddCompanyActivity::class.java)
            startActivity(intent)
        }

        binding.cardEmpList.setOnClickListener{
            val intent = Intent(this, EMPListActivity::class.java)
            startActivity(intent)
        }

        binding.card1.setOnClickListener {
            val intent = Intent(this, AddEmployeeActivity::class.java).apply {
                putExtra("EXTRA_MESSAGE", "message")
            }
            startActivity(intent)
        }

        binding.card2.setOnClickListener {
            val intent = Intent(this, AddEmployeeActivity::class.java).apply {
                putExtra("EXTRA_MESSAGE", "message")
            }
            startActivity(intent)
        }

        binding.card3.setOnClickListener {
            val intent = Intent(this, AddEmployeeActivity::class.java).apply {
                putExtra("EXTRA_MESSAGE", "message")
            }
            startActivity(intent)
        }

    }

    private suspend fun save(key: String, value: String) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}