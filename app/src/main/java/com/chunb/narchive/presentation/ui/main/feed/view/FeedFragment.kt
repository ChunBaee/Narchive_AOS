package com.chunb.narchive.presentation.ui.main.feed.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chunb.narchive.R
import com.chunb.narchive.databinding.FragmentFeedBinding
import com.chunb.narchive.presentation.ui.main.feed.adapter.FeedListAdapter
import com.chunb.narchive.presentation.ui.main.feed.adapter.HomeFeedAdapter
import com.chunb.narchive.presentation.ui.main.viewmodel.MainViewModel
import com.chunb.narchive.presentation.util.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FeedFragment : Fragment() {
    private lateinit var binding: FragmentFeedBinding
    private val viewModel: MainViewModel by viewModels()
    private val feedAdapter by lazy {
        //HomeFeedAdapter()
        FeedListAdapter()
    }
    private val loadingDialog by lazy {
        LoadingDialog(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)

        initBinding()
        initFeed()
        observeFeed()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getFeedData()
        //loadingDialog.show()
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.fragment = this
    }

    private fun initFeed() {
        binding.fgMainRvContents.adapter = feedAdapter
    }

    private fun observeFeed() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getFeedData().collectLatest {
                withContext(Dispatchers.Main) { loadingDialog.dismiss() }
                feedAdapter.submitData(it)
            }
        }
    }

    fun scrollToTop() {
        binding.fgMainRvContents.smoothScrollToPosition(0)
    }

}