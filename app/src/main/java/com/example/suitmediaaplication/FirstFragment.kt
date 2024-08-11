package com.example.suitmediaaplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.suitmediaaplication.databinding.FragmentFirstBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class FirstFragment : Fragment() {


    private lateinit var binding : FragmentFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCheck.setOnClickListener { checkPalindrome() }
        binding.btnNext.setOnClickListener { nextActivity() }
    }

    private fun nextActivity() {
        val name = binding.nameEditText.text.toString()
        if (name.isEmpty()) {
            binding.nameEditText.error = "Name is required"
            return
        }
        val fragmentManager = parentFragmentManager
        val secondFragment = SecondFragment()
        val bundle = Bundle()
        binding.nameEditText.text?.clear()
        bundle.putString("name", name)
        secondFragment.arguments = bundle
        fragmentManager.beginTransaction().apply {
            replace(
                R.id.fragment_container,
                secondFragment,
                SecondFragment::class.java.simpleName
            )
            addToBackStack(null)
            commit()
        }
    }

    private fun checkPalindrome() {
        val sentence = binding.palindromeEditText.text.toString()
        if (sentence.isEmpty()) {
            binding.palindromeEditText.error = "Sentence is required"
            return
        }
        if (isPalindrome(sentence)) {
            showDialog("isPalindrome")
        } else {
            showDialog("not Palindrome")
        }
    }

    private fun showDialog(message: String) {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Check Palindrome")
            .setMessage(message)
            .setPositiveButton("OK") { _, _ ->
                binding.palindromeEditText.text?.clear()
            }
            .show()

    }

    private fun isPalindrome(input: String): Boolean {
        return input.lowercase() == input.reversed().lowercase()
    }
}