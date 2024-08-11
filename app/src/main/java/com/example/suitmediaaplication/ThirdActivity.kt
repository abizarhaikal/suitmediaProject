package com.example.suitmediaaplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediaaplication.adapter.LoadingStateAdapter
import com.example.suitmediaaplication.adapter.UserAdapter
import com.example.suitmediaaplication.data.DataItem
import com.example.suitmediaaplication.databinding.ActivityThirdBinding
import com.example.suitmediaaplication.viewModel.ThirdScreenViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private val viewModel by viewModels<ThirdScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var userAdapter: UserAdapter
    private lateinit var loadingStateAdapter: LoadingStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize adapters
        userAdapter = UserAdapter()
        loadingStateAdapter = LoadingStateAdapter { userAdapter.retry() }

        // Set up RecyclerView
        binding.rvList.layoutManager = LinearLayoutManager(this)
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

//        setupIcon
        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }

////        KlikItem
//        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: DataItem) {
//                val
//            }
//
//        })
    }
}
