package com.flaviovicentini.modulo02aula04

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.flaviovicentini.modulo02aula04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var taskList = arrayListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)

        settingTaskListView()
        settingDeleteTask()
        settingAddTaskButton()
    }

    private fun settingTaskListView(){
        binding.ltvTasks.adapter = adapter
    }

    private fun settingAddTaskButton() {
        binding.btnAdd.setOnClickListener {
            val task = binding.edtNewTask.text.toString()
            if (task.isNotBlank()) {
                taskList.add(task)
                binding.edtNewTask.text.clear()
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "You can't insert a blank task", Toast.LENGTH_SHORT).show()
            }
            println(taskList)
        }
    }

    private fun settingDeleteTask(){
        binding.ltvTasks.setOnItemLongClickListener { _, _, position, _ ->
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Delete Task")
            alertDialog.setMessage("Are you sure you want to delete this task?")

            alertDialog.setPositiveButton("YES"){ dialog, _ ->
                taskList.removeAt(position)
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }

            alertDialog.setNegativeButton("NO"){ dialog, _ ->
                dialog.dismiss()
            }

            alertDialog.create().show()

            true
        }
    }
}