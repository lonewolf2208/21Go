package com.example.a21go.Auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.a21go.R
import com.example.a21go.databinding.LoginFragmentBinding
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment() {
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        val view =binding.root


        showHintBox()
        return view
    }

    private fun showHintBox() {

        binding.loginBtn.setOnClickListener {

            val builder = AlertDialog.Builder(requireContext())
            val inflater=layoutInflater
            val dialogLayout=inflater.inflate(R.layout.edit_text_layout,null)
            val editText= dialogLayout.findViewById<TextInputEditText>(R.id.enter_edit)

            val dialog=builder.create()
            dialog.setView(dialogLayout)
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.getWindow()?.setLayout(800, 800)


        }
    }

}