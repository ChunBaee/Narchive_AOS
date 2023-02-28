package com.chunb.narchive.presentation.ui.comment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunb.narchive.data.remote.request.RequestPostComment
import com.chunb.narchive.data.remote.response.Comment
import com.chunb.narchive.domain.repository.CommentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(private val commentRepository: CommentRepository) :
    ViewModel() {

    private val _contentIdx = MutableLiveData<Int>()
    val contentIdx: LiveData<Int> = _contentIdx

    private val _commentData = MutableLiveData<MutableList<Comment>>()
    val commentData: LiveData<MutableList<Comment>> = _commentData

    val currentComment = MutableLiveData<String>().apply { value }

    private val _snackBarState = MutableLiveData<String>()
    val snackBarState: LiveData<String> = _snackBarState

    fun setContentIdx(contentId: Int) {
        _contentIdx.value = contentId
    }

    fun getComments(contentId: Int) {
        viewModelScope.launch {
            commentRepository.getComment(contentId)
                .onSuccess { _commentData.value = it as MutableList }
        }
    }

    fun postComment() {
        if (currentComment.value.isNullOrEmpty()) {
            _snackBarState.value = "댓글이 아직 작성되지 않았어요!"
            return
        }
        viewModelScope.launch {
            commentRepository.postComment(contentIdx.value!!, RequestPostComment(currentComment.value!!))
                .onSuccess {
                    _snackBarState.value = it
                    getComments(contentIdx.value!!)
                }
        }
    }
}