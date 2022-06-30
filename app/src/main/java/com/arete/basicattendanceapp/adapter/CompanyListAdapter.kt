package com.arete.basicattendanceapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arete.basicattendanceapp.R
import com.arete.basicattendanceapp.entity.CompanyMaster

class CompanyListAdapter : ListAdapter<CompanyMaster, CompanyListAdapter.CMPViewHolder>(CMP_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CMPViewHolder {
        return CMPViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CMPViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class CMPViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cmpIdView: TextView = itemView.findViewById(R.id.cmp_id)
        private val cmpNameView: TextView = itemView.findViewById(R.id.cmp_name)

        fun bind(cmpMaster: CompanyMaster?) {
            cmpIdView.text = "Company ID:"+cmpMaster?.CMP_ID.toString()
            cmpNameView.text = "Company Name:"+cmpMaster!!.CMP_Name
        }

        companion object {
            fun create(parent: ViewGroup): CMPViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.cmp_list_item, parent, false)
                return CMPViewHolder(view)
            }
        }
    }

    companion object {
        private val CMP_COMPARATOR = object : DiffUtil.ItemCallback<CompanyMaster>() {
            override fun areItemsTheSame(oldItem: CompanyMaster, newItem: CompanyMaster): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: CompanyMaster, newItem: CompanyMaster): Boolean {
                return oldItem == newItem
                //return oldItem.EMP_ID == newItem.EMP_ID
            }
        }
    }
}
