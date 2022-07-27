package com.arete.basicattendanceapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arete.basicattendanceapp.R
import com.arete.basicattendanceapp.entity.EMPMaster

class EmployeeListAdapter : ListAdapter<EMPMaster, EmployeeListAdapter.EMPViewHolder>(EMP_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EMPViewHolder {
        return EMPViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EMPViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class EMPViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val companyView: TextView = itemView.findViewById(R.id.company)
        private val empIdView: TextView = itemView.findViewById(R.id.emp_id)
        private val empNameView: TextView = itemView.findViewById(R.id.emp_name)
        private val empDesigView: TextView = itemView.findViewById(R.id.emp_designation)
        private val empGenderView: TextView = itemView.findViewById(R.id.emp_gender)

        fun bind(empMaster: EMPMaster?) {
            if (empMaster != null) {
                companyView.text = "Company Name:"+empMaster.CMP_Name
            }
            empIdView.text = "EMP ID:"+empMaster?.EMP_ID.toString()
            empNameView.text = "EMP Name:"+empMaster!!.EMP_Name
            empDesigView.text = "Designation:"+empMaster.Designation
            empGenderView.text = "Gender:"+empMaster.Gender
        }

        companion object {
            fun create(parent: ViewGroup): EMPViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.emp_list_item, parent, false)
                return EMPViewHolder(view)
            }
        }
    }

    companion object {
        private val EMP_COMPARATOR = object : DiffUtil.ItemCallback<EMPMaster>() {
            override fun areItemsTheSame(oldItem: EMPMaster, newItem: EMPMaster): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: EMPMaster, newItem: EMPMaster): Boolean {
                return oldItem == newItem
                //return oldItem.EMP_ID == newItem.EMP_ID
            }
        }
    }
}
