package com.chunb.narchive.presentation.ui.comment.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunb.narchive.R
import com.chunb.narchive.databinding.ActivityAddCommentBinding
import com.chunb.narchive.presentation.ui.comment.adapter.CommentAdapter
import com.chunb.narchive.presentation.ui.comment.viewmodel.CommentViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddCommentBinding
    private val viewModel : CommentViewModel by viewModels()

    private val commentAdapter by lazy {
        CommentAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_comment)

        initBinding()
        getContentId()
        observeContentId()
        initCommentRv()
        observeCommentData()
        observeSnackbarData()
    }

    private fun initBinding() {
        binding.activity = this
        binding.viewModel = viewModel
    }

    private fun getContentId() {
        val contentId = intent.getIntExtra("contentIdx", 0)
        viewModel.setContentIdx(contentId)
    }

    private fun observeContentId() {
        viewModel.contentIdx.observe(this) {
            viewModel.getComments(it)
        }
    }

    private fun initCommentRv() {
        binding.commentRvComments.adapter = commentAdapter
    }

    private fun observeCommentData() {
        viewModel.commentData.observe(this) {
            commentAdapter.commentList = it
            commentAdapter.notifyDataSetChanged()
        }
    }

    private fun observeSnackbarData() {
        viewModel.snackBarState.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
            initCurrentComment()
        }
    }

    private fun initCurrentComment() {
        binding.commentEdtComment.setText("")
    }

    fun dismiss() {
        finish()
    }


}