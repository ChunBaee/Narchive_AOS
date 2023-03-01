package com.chunb.narchive.presentation.ui.main.feed.view

import android.content.Intent
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
import com.chunb.narchive.presentation.ui.detail.view.DetailActivity
import com.chunb.narchive.presentation.ui.main.feed.adapter.FeedListAdapter
import com.chunb.narchive.presentation.ui.main.viewmodel.MainViewModel
import com.chunb.narchive.presentation.ui.write.view.WriteActivity
import com.chunb.narchive.presentation.util.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FeedFragment : Fragment() {
    private lateinit var binding: FragmentFeedBinding
    private val viewModel: MainViewModel by viewModels()
    private val loadingDialog by lazy {
        LoadingDialog(requireActivity())
    }
    private lateinit var feedAdapter : FeedListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)

        Log.d("----", "onCreateView: Hi")

        initBinding()
        initFeed()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        refreshOnResume()
        scrollToTop()
    }

    private fun refreshOnResume() {
        //viewModel.getFeedData()
        observeFeed()
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.fragment = this
    }

    private fun initFeed() {
        feedAdapter = FeedListAdapter(::openDetailFeedActivity)
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

    fun openWrite() {
        startActivity(Intent(requireActivity(), WriteActivity::class.java))
    }

    private fun openDetailFeedActivity(position : Int) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra("contentIdx", position)
        startActivity(intent)
    }

}