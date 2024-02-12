package com.blogspot.softwareengineerrohan.naarirakshak

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.blogspot.softwareengineerrohan.naarirakshak.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.signup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_signupFragment)

        }
        binding.loginBtn.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity2::class.java))

        }




        return binding.root



    }

}