package com.chunb.narchive.presentation.ui.search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chunb.narchive.data.remote.response.Book
import com.chunb.narchive.data.remote.response.Movie
import com.chunb.narchive.data.remote.response.ResultSearchBook
import com.chunb.narchive.data.remote.response.ResultSearchMovie
import com.chunb.narchive.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {
    private val _searchType = MutableLiveData<Boolean>()
    val searchType: LiveData<Boolean> = _searchType

    private val _titleText = MutableLiveData<String>()
    val titleText: LiveData<String> = _titleText

    val targetWord = MutableLiveData<String>().apply { value }

    private val _selectBook = MutableLiveData<Book>()
    val selectBook : LiveData<Book> = _selectBook

    private val _selectMovie = MutableLiveData<Movie>()
    val selectMovie : LiveData<Movie> = _selectMovie

    fun setSearchType(type: Boolean) {
        _searchType.value = type
        setSearchViewTitle()
    }

    private fun setSearchViewTitle() {
        _titleText.value = if (searchType.value == true) "책 검색" else "영화 검색"
    }

    fun getBookList() : Flow<PagingData<ResultSearchBook>>{
        return searchRepository.kakaoBookSearch(targetWord.value.orEmpty()).cachedIn(viewModelScope)
    }

    fun getMovieList() : Flow<PagingData<ResultSearchMovie>> {
        return searchRepository.naverMovieSearch(targetWord.value.orEmpty()).cachedIn(viewModelScope)
    }

    fun setSelectBook(select : Book) {
        _selectBook.value = select
    }

    fun setSelectMovie(select : Movie) {
        _selectMovie.value = select
    }


}