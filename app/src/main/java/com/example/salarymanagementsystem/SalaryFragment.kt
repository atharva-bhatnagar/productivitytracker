package com.example.salarymanagementsystem

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SalaryFragment : Fragment(R.layout.fragment_salary) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView:ListView=view.findViewById(R.id.lv)
        val list= loadTasks()

        val arrayAdapter=
            context?.let { ArrayAdapter<String>(it,android.R.layout.simple_list_item_1,list) }
        listView.adapter=arrayAdapter
        listView.setOnItemClickListener{ adapterView: AdapterView<*>, view2: View, i: Int, l: Long ->
            Toast.makeText(context,"Task Selected!!!",Toast.LENGTH_SHORT).show()
        }
        }
    private fun loadTasks(): ArrayList<String> {
        val sharedPreferences=activity?.getSharedPreferences("tasks", Context.MODE_PRIVATE)
        var jsonString=sharedPreferences?.getString("tasklist","")
        val gson= Gson()
        val type= object: TypeToken<ArrayList<String>>(){}.type
        var list=gson.fromJson<ArrayList<String>>(jsonString,type)
        if(list==null){
            list=ArrayList<String>()
        }
        return list
    }



}
//val departments = arrayOf("Please select department", "Information Technology", "Human Resource", "Sales and Marketing")
//var positions = arrayOf<String>()
//val positionsit = arrayOf("Software Developer", "Network Administrator", "System Analyst")
//val positionshr = arrayOf("HR manager", "Recruiter")
//val positionssm = arrayOf("Sales Representative", "Marketing Manager", "Advertising Specialist")
//val leaves = arrayOf(1, 2, 3, 4, 5)
//
//val dep = view.findViewById<Spinner>(R.id.department)
//val pos = view.findViewById<Spinner>(R.id.Position)
//val lve = view.findViewById<Spinner>(R.id.leave)
//val extra = view.findViewById<EditText>(R.id.extra)
//val calculate = view.findViewById<Button>(R.id.calcu)
//val result = view.findViewById<TextView>(R.id.result)
//val finalresult = view.findViewById<TextView>(R.id.finalresult)
//
//val adapter1 = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, departments)
//adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//dep.adapter = adapter1
//
//val adapter3 = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, leaves)
//adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//lve.adapter = adapter3
//
//dep.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
//        // Update the positions array based on the selected department
//        when (parent.getItemAtPosition(position)) {
//            "Information Technology" -> {
//                positions = positionsit
//            }
//            "Human Resource" -> {
//                positions = positionshr
//            }
//            "Sales and Marketing" -> {
//                positions = positionssm
//            }
//            else -> {
//                positions = emptyArray()
//            }
//        }
//        val adapter2 = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, positions)
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        pos.adapter = adapter2
//    }
//
//    override fun onNothingSelected(parent: AdapterView<*>) {
//
//    }
//}

//calculate.setOnClickListener(){
//    val depitem = dep.selectedItem.toString()
//    val positem = pos.selectedItem.toString()
//    val lveitem = lve.selectedItem.toString()
//
//    var monsalary = 0
//    var leavededuction = 0
//    var extrapay = 0
//
//
//    when {
//        depitem == "Information Technology" && positem == "Software Developer" -> {
//            monsalary = 80000
//        }
//        depitem == "Information Technology" && positem == "Network Administrator" -> {
//            monsalary = 60000
//        }
//        depitem == "Information Technology" && positem == "System Analyst" -> {
//            monsalary = 50000
//        }
//        depitem == "Human Resource" && positem == "HR manager" -> {
//            monsalary = 50000
//        }
//        depitem == "Human Resource" && positem == "Recruiter" -> {
//            monsalary = 40000
//        }
//        depitem == "Sales and Marketing" && positem == "Sales Representative" -> {
//            monsalary = 35000
//        }
//        depitem == "Sales and Marketing" && positem == "Marketing Manager" -> {
//            monsalary = 50000
//        }
//        depitem == "Sales and Marketing" && positem == "Advertising Specialist" -> {
//            monsalary = 40000
//        }
//
//        else -> {
//            Toast.makeText(requireContext(), "Invalid department or position", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//
//    when (lveitem) {
//        "1" -> leavededuction = 500
//        "2" -> leavededuction = 1000
//        "3" -> leavededuction = 1500
//        "4" -> leavededuction = 2000
//        "5" -> leavededuction = 2500
//    }
//
//
//    if (!extra.text.isNullOrEmpty()) {
//        val hours = extra.text.toString().toInt()
//        extrapay = hours * 200
//    }
//
//
//    val total = monsalary - leavededuction + extrapay
//    result.text = "Base Salary: $monsalary \nLeave Deduction: $leavededuction \nExtra amount : $extrapay "
//    finalresult.text = "Total Salary: ${String.format("%.2f", total.toFloat())}"
//}