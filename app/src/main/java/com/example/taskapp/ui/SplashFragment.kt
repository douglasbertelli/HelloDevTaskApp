package com.example.taskapp.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentSplashBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashFragment : Fragment() {

	private var _binding: FragmentSplashBinding? = null
	private val binding get() = _binding!!

	private lateinit var auth: FirebaseAuth

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?

	): View {
		_binding = FragmentSplashBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		auth = Firebase.auth

		// delay de 3 segundo da tela Splash para tela de Login
		Handler(Looper.getMainLooper()).postDelayed(this::checkAuth, 3000)
	}

	private fun checkAuth() {
		val currentUser = auth.currentUser

		if(currentUser != null) {
			// se o usuário estive logado, será redicionado para a tela home.
			findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
		} else {
			// navegando da tela de Splash para Login.
			findNavController().navigate(R.id.action_splashFragment_to_authetication)
		}

	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

}