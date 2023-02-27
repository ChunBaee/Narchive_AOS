package com.chunb.narchive.presentation.ui.write.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunb.narchive.R
import com.chunb.narchive.data.local.data.MoodData
import com.chunb.narchive.data.remote.response.Book
import com.chunb.narchive.data.remote.response.Movie
import com.chunb.narchive.domain.mapper.mapToFeedData
import com.chunb.narchive.domain.repository.ContentRepository
import com.chunb.narchive.domain.repository.FirebaseRepository
import com.chunb.narchive.domain.repository.LocalResourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(
    private val localResourceRepository: LocalResourceRepository,
    private val firebaseRepository: FirebaseRepository,
    private val contentRepository: ContentRepository
) : ViewModel() {

    private val _moodData = MutableLiveData<MutableList<MoodData>>()
    val moodData: LiveData<MutableList<MoodData>> = _moodData

    val currentMood =
        MutableLiveData<MoodData>(MoodData(Pair("normal", R.drawable.ic_face_default)))

    val currentContent = MutableLiveData<String>().apply { value }

    private val _selectedImage = MutableLiveData<MutableList<String>>()
    val selectedImage: LiveData<MutableList<String>> = _selectedImage

    val downloadImageURL = MutableLiveData<MutableList<String>>()

    private val _selectBook = MutableLiveData<Book>()
    val selectBook: LiveData<Book> = _selectBook

    private val _selectMovie = MutableLiveData<Movie>()
    val selectMovie: LiveData<Movie> = _selectMovie

    private val _errorSnackBarMsg = MutableLiveData<String>()
    val errorSnackBarMsg: LiveData<String> = _errorSnackBarMsg

    private val _postFeedStatusCode = MutableLiveData<Int>()
    val postFeedStatusCode : LiveData<Int> = _postFeedStatusCode

    fun getTodayDate() : String {
            return localResourceRepository.getTodayDate()
    }
    fun getMoodData() {
        viewModelScope.launch {
            _moodData.value = localResourceRepository.getMoodsData() as MutableList
        }
    }

    fun setChangedMood(selectedMood: MoodData) {
        currentMood.value = selectedMood
    }

    fun setSelectedImage(selectedImageList: List<String>) {
        _selectedImage.value = selectedImageList as MutableList
    }

    fun setSelectBook(select: Book) {
        _selectBook.value = select
    }

    fun setSelectMovie(select: Movie) {
        _selectMovie.value = select
    }

    fun upLoad() {
        if (currentContent.value.isNullOrBlank()) {
            _errorSnackBarMsg.value = "글이 아직 작성되지 않았어요!"
            _errorSnackBarMsg.value = ""
        } else {
            viewModelScope.launch {
                if (selectedImage.value?.isNotEmpty() == true) {
                    selectedImage.value?.let { firebaseRepository.uploadWriteImageToFirebase(it, downloadImageURL) }
                } else {
                    uploadToRemote()
                }
            }
        }
    }

    fun uploadToRemote() {
        viewModelScope.launch {
            Log.d("----", "uploadToRemote: ${selectBook.value}, ${selectMovie.value}")
            contentRepository.postFeed(
                currentContent.value!!.mapToFeedData(
                    currentMood.value!!.moods.first,
                    downloadImageURL.value,
                    selectBook.value,
                    selectMovie.value
                )
            )
                .onSuccess { _postFeedStatusCode.value = it }
        }
    }
}