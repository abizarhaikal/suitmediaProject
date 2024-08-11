package com.example.suitmediaaplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediaaplication.adapter.LoadingStateAdapter
import com.example.suitmediaaplication.adapter.UserAdapter
import com.example.suitmediaaplication.data.DataItem
import com.example.suitmediaaplication.databinding.FragmentThirdBinding
import com.example.suitmediaaplication.viewModel.ThirdScreenViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding
    private val viewModel by viewModels<ThirdScreenViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    private lateinit var userAdapter: UserAdapter
    private lateinit var loadingStateAdapter: LoadingStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThirdBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize adapters
        userAdapter = UserAdapter()
        loadingStateAdapter = LoadingStateAdapter { userAdapter.retry() }

        // Set up RecyclerView
        binding.rvList.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvList.adapter = userAdapter.withLoadStateFooter(
            footer = loadingStateAdapter
        )

        // Setup SwipeRefreshLayout
        binding.swipeRefreshLayout.setOnRefreshListener {
            userAdapter.refresh() // Trigger a refresh of the data
        }

        lifecycleScope.launch {
            viewModel.userPagingFlow.collectLatest { pagingData ->
                userAdapter.submitData(pagingData)
            }
        }

        // Observe LoadState to show/hide loading indicators
        userAdapter.addLoadStateListener { loadState ->
            // Show or hide the progress bar
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            // Hide the SwipeRefreshLayout loading indicator
            binding.swipeRefreshLayout.isRefreshing = loadState.source.refresh is LoadState.Loading
        }

        binding.topAppBar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataItem) {
                val bundle = Bundle().apply {
                    putString("fullName", data.firstName + " " + data.lastName)
                }
                val fragment = SecondFragment().apply {
                    arguments = bundle
                }

                // Mengganti Fragment saat ini dengan DestinationFragment dan mengirim data
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            }
        })

    }
}
