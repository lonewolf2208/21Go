package com.example.a21go.Auth

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.a21go.Network.Response
import com.example.a21go.R
import com.example.a21go.Repository.LoginRepo
import com.example.a21go.Ui.Splash_Screen
import com.example.a21go.Ui.Splash_Screen.Companion.saveInfo
import com.example.a21go.databinding.LoginFragmentBinding
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var loginRepo: LoginRepo
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        val view =binding.root
       // showHintBox()
        val loginButton= binding.loginBtn
        val progressBar=binding.loginProgressBar
        loginButton.setOnClickListener {
            val loginEmail = binding.emailEdit.text.toString().trim()
            val username = binding.usernameEdit.text.toString().trim()
            Log.i("email", "onActivityResult: $loginEmail")
            if (isValid(loginEmail,username)) {
                progressBar.visibility=View.VISIBLE
                loginButton.isClickable=false
                loginRepo = LoginRepo()
                loginRepo.loginApi(loginEmail, username)
                loginRepo.loginResponse.observe(viewLifecycleOwner, {
                    when (it) {
                        is Response.Success -> {
                                
                           
                            progressBar.visibility=View.GONE

                            lifecycleScope.launch {
                                saveInfo("USERID",it.data?.id.toString())
                                Splash_Screen.save("loggedIn",true)
                            }
                            loginRepo.userDetails.observe(viewLifecycleOwner, {
//                                Log.i("login","response"+it)
//                                datastore = Datastore(requireContext())
//                                lifecycleScope.launch {
//                                    datastore.saveToDatastore(it, requireContext())
//
//                                    access=it.access.toString()
//                                    activity?.finish()
//                                    Navigation.findNavController(view)
//                                        .navigate(R.id.action_login_Fragment_to_dashboard)
//                                }
                            })


                                findNavController().navigate(R.id.homePageActivity)

                        }

                        is Response.Error -> {
                            Toast.makeText(context, it.errorMessage.toString(), Toast.LENGTH_SHORT)
                                .show()
                            progressBar.visibility=View.GONE
                            loginButton.isClickable=true
                        }

                        else -> {
                            loginButton.isClickable=true
                        }
                    }
                })
            }
        }
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
    fun isValid(email:String,password:String):Boolean{
        return when{
            email.isBlank()->{
                binding.email.helperText="Enter Your Email Id"
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.email.helperText = "Enter valid Email Id"
                false
            }
            password.isBlank()->{
                binding.username.helperText="Enter Your Password"
                false
            }
            else -> {
                true
            }
        }
    }
}