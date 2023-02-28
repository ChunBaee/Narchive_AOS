package com.chunb.narchive.presentation.ui.main.archive.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chunb.narchive.R
import com.chunb.narchive.databinding.FragmentArchiveBinding
import com.chunb.narchive.presentation.ui.detail.view.DetailActivity
import com.chunb.narchive.presentation.ui.main.archive.adapter.ArchiveAdapter
import com.chunb.narchive.presentation.ui.main.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArchiveFragment : Fragment() {
    private lateinit var binding : FragmentArchiveBinding
    private val viewModel : MainViewModel by viewModels()
    private lateinit var archiveAdapter : ArchiveAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_archive, container, false)

        initBinding()
        initVP()
        initArchiveObserve()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getArchiveData()
    }

    private fun initBinding() {
        binding.activity = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initVP() {
        archiveAdapter = ArchiveAdapter(requireContext())
        binding.fgArchiveLayoutVp.apply {
            this.adapter = archiveAdapter
            TabLayoutMediator(binding.fgArchiveLayoutTab, this) { mTab, position ->
                mTab.icon =
                    resources.getDrawable(viewModel.getArchiveTabData()[position].tabImage, null)
            }.attach()
            offscreenPageLimit = 3
        }
    }

    private fun initArchiveObserve() {
        viewModel.archiveBookData.observe(viewLifecycleOwner) {
            archiveAdapter.setBookList(it)
        }
        viewModel.archiveMovieData.observe(viewLifecycleOwner) {
            archiveAdapter.setMovieList(it)
        }
        viewModel.archiveImageData.observe(viewLifecycleOwner) {
            archiveAdapter.setImageList(it)
        }

        archiveAdapter.selectedContentId.observe(viewLifecycleOwner) {
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            intent.putExtra("contentIdx", it)
            startActivity(intent)
        }
    }
}