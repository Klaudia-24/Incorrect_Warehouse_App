package com.example.incorrect_warehouse_app.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.incorrect_warehouse_app.R
import com.google.android.material.textview.MaterialTextView
import java.util.*

class NewEmployeeAdapter(private var newEmployeeList: List<Employee>):
    RecyclerView.Adapter<NewEmployeeAdapter.ViewHolder>()  {

    private lateinit var mListner: onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListner){
        mListner = listener
    }

    inner class ViewHolder(employeeAdminView: View, listener: onItemClickListner): RecyclerView.ViewHolder(employeeAdminView){

        val employeeName: MaterialTextView = employeeAdminView.findViewById(R.id.empEmployeeName)
        val employeeSurname: MaterialTextView = employeeAdminView.findViewById(R.id.empEmployeeSurname)
        val salary: MaterialTextView = employeeAdminView.findViewById(R.id.empEmployeeSalary)
        val address: MaterialTextView = employeeAdminView.findViewById(R.id.empEmployeeAddress)
        val empLogin: MaterialTextView = employeeAdminView.findViewById(R.id.empEmployeeLogin)
        val email: MaterialTextView = employeeAdminView.findViewById(R.id.empEmployeeEmail)
        val roleName: MaterialTextView = employeeAdminView.findViewById(R.id.empEmployeeRole)

        init {
            employeeAdminView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.employee_admin_list, parent, false)
        return ViewHolder(view, mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.employeeName.text = newEmployeeList[position].name
        holder.employeeSurname.text = newEmployeeList[position].surname

    }

    override fun getItemCount(): Int {
        return newEmployeeList.size
    }
}