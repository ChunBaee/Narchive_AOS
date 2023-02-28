package com.chunb.narchive.presentation.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chunb.narchive.data.data.ArchiveTabData
import com.chunb.narchive.data.remote.response.ArchiveBook
import com.chunb.narchive.data.remote.response.ArchiveMovie
import com.chunb.narchive.data.remote.response.Feed
import com.chunb.narchive.data.remote.response.Image
import com.chunb.narchive.domain.repository.BookRepository
import com.chunb.narchive.domain.repository.ContentRepository
import com.chunb.narchive.domain.repository.ImageRepository
import com.chunb.narchive.domain.repository.LocalResourceRepository
import com.chunb.narchive.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val contentRepository: ContentRepository,
    private val localResourceRepository: LocalResourceRepository,
    private val bookRepository: BookRepository,
    private val movieRepository : MovieRepository,
    private val imageRepository: ImageRepository
) : ViewModel() {

    private val _archiveBookData = MutableLiveData<MutableList<ArchiveBook>>()
    val archiveBookData : LiveData<MutableList<ArchiveBook>> = _archiveBookData
    private val _archiveMovieData = MutableLiveData<MutableList<ArchiveMovie>>()
    val archiveMovieData : LiveData<MutableList<ArchiveMovie>> = _archiveMovieData
    private val _archiveImageData = MutableLiveData<MutableList<Image>>()
    val archiveImageData : LiveData<MutableList<Image>> = _archiveImageData

    fun getFeedData() : Flow<PagingData<Feed>> {
        return contentRepository.getFeedPagingData().cachedIn(viewModelScope)
    }

    fun getArchiveTabData() : List<ArchiveTabData> = localResourceRepository.getArchiveTabData()

    fun getArchiveData() {
        viewModelScope.launch {
            bookRepository.getBook()
                .onSuccess { _archiveBookData.value = it as MutableList}
            movieRepository.getMovies()
                .onSuccess { _archiveMovieData.value = it as MutableList }
            imageRepository.getImages()
                .onSuccess { _archiveImageData.value = it as MutableList }
        }
    }
}