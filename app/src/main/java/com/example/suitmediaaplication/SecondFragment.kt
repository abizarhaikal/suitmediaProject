package com.example.suitmediaaplication


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.suitmediaaplication.databinding.FragmentSecondBinding
import com.example.suitmediaaplication.viewModel.SecondViewModel


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var viewModel : SecondViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SecondViewModel::class.java)

        val name = viewModel.name.value ?: arguments?.getString("name")
        binding.tvName.text = name

        viewModel.name.value = name
        val fullName = arguments?.getString("fullName")
        if (fullName != null) {
            if (fullName.isNotEmpty()) {
                binding.tvFullname.visibility = View.VISIBLE
                binding.tvFullname.text = fullName
                binding.tvSelectedUser.visibility = View.GONE
            }
        } else {
            binding.tvFullname.visibility = View.GONE
            binding.tvSelectedUser.visibility = View.VISIBLE
        }
        binding.btnChooseUser.setOnClickListener { viewUser() }
        setTopAppBar()
    }

    private fun viewUser() {
        val thirdFragment = ThirdFragment()
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(
                R.id.fragment_container,
                thirdFragment,
                ThirdFragment::class.java.simpleName
            )
            commit()
        }
    }

    private fun setTopAppBar() {
        binding.topAppBar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}