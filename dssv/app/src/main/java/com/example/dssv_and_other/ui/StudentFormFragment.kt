package com.example.dssv_and_other.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.dssv_and_other.R
import com.example.dssv_and_other.databinding.FragmentStudentFormBinding

class StudentFormFragment : Fragment() {

    private val viewModel: StudentViewModel by activityViewModels()
    private var studentIdArg: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        studentIdArg = arguments?.getString("studentId")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentStudentFormBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_student_form,
            container,
            false
        )
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // set title based on arg presence
        binding.tvFormTitle.text = if (studentIdArg == null) "Thêm sinh viên" else "Cập nhật thông tin"

        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSave.setOnClickListener {
            viewModel.save()
        }

        viewModel.messageEvent.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { msg ->
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.navigateBackEvent.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                findNavController().popBackStack()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize form mode after view created
        if (studentIdArg == null) {
            viewModel.startAdd()
        } else {
            viewModel.startEdit(studentIdArg)
        }
    }
}