package com.example.dssv_and_other.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dssv_and_other.R
import com.example.dssv_and_other.StudentAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.TextView

class StudentListFragment : Fragment() {

    private val viewModel: StudentViewModel by activityViewModels()
    private lateinit var adapter: StudentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val fab = view.findViewById<FloatingActionButton>(R.id.fabAdd)
        val tvEmpty = view.findViewById<TextView>(R.id.tvEmpty)

        adapter = StudentAdapter(mutableListOf(),
            onEditClick = { student ->
                //to form with studentId
                val bundle = bundleOf("studentId" to student.studentId)
                findNavController().navigate(R.id.action_studentListFragment_to_studentFormFragment, bundle)
            },
            onDeleteClick = { student ->
                viewModel.deleteStudent(student.studentId)
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.students.observe(viewLifecycleOwner) { list ->
            adapter.updateData(list)
            tvEmpty.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        }

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_studentListFragment_to_studentFormFragment)
        }
    }
}