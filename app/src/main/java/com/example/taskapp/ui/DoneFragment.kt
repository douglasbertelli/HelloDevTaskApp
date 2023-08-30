package com.example.taskapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentDoneBinding
import com.example.taskapp.databinding.FragmentLoginBinding
import com.example.taskapp.ui.adapter.TaskAdapter
import com.example.taskapp.ui.data.model.Status
import com.example.taskapp.ui.data.model.Task

class DoneFragment : Fragment() {

	private var _binding: FragmentDoneBinding? = null
	private val binding get() = _binding!!

	private lateinit var taskAdapter: TaskAdapter
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {

		_binding = FragmentDoneBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initRecyclerView()
		getTasks()
	}

	private fun initRecyclerView() {
		taskAdapter = TaskAdapter(requireContext()) { task, option ->
			optionSelect(task, option)
		}

		with(binding.rvTasks) {
			layoutManager = LinearLayoutManager(requireContext())
			setHasFixedSize(true)
			adapter = taskAdapter
		}
	}

	private fun optionSelect(task: Task, option: Int) {
		when (option) {
			TaskAdapter.SELECT_BACK -> {
				Toast.makeText(requireContext(), "Back ${task.description}", Toast.LENGTH_SHORT).show()
			}

			TaskAdapter.SELECT_REMOVE -> {
				Toast.makeText(requireContext(), "Removendo ${task.description}", Toast.LENGTH_SHORT).show()
			}

			TaskAdapter.SELECT_EDIT -> {
				Toast.makeText(requireContext(), "Editando ${task.description}", Toast.LENGTH_SHORT).show()
			}

			TaskAdapter.SELECT_DETAILS -> {
				Toast.makeText(requireContext(), "Detalhes ${task.description}", Toast.LENGTH_SHORT).show()
			}
		}
	}

	private fun getTasks() {
		val taskList = listOf(
			Task("0", "Criar nova tela do app", Status.DONE),
			Task("1", "Validar informações na tela do app", Status.DONE),
		)

		taskAdapter.submitList(taskList)
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

}