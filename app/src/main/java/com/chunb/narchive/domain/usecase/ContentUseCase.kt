package com.chunb.narchive.domain.usecase

import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.data.remote.response.Image
import com.chunb.narchive.data.remote.response.Movie
import com.chunb.narchive.data.remote.response.Book
import com.chunb.narchive.data.remote.response.Comment
import com.chunb.narchive.data.remote.response.User
import com.chunb.narchive.domain.data.Contents
import com.chunb.narchive.domain.repository.BookRepository
import com.chunb.narchive.domain.repository.CommentRepository
import com.chunb.narchive.domain.repository.ContentRepository
import com.chunb.narchive.domain.repository.ImageRepository
import com.chunb.narchive.domain.repository.MovieRepository
import com.chunb.narchive.domain.repository.UserRepository
import javax.inject.Inject

class ContentUseCase @Inject constructor(
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val movieRepository: MovieRepository,
    private val contentRepository: ContentRepository,
    private val imageRepository: ImageRepository,
    private val commentRepository: CommentRepository
) {

    suspend fun mapToContentList(query : String?): MutableList<Contents> {
        val returnList = mutableListOf<Contents>()

        for (i in getContentList(query)) {
            returnList.add(
                Contents(
                    i.contentIdx,
                    getTargetUser(i.contentIdx, getUserList(query)),
                    i,
                    getTargetBook(i.contentIdx, getBookList(query)),
                    getTargetMovie(i.contentIdx, getMovieList(query)),
                    getTargetImages(i.contentIdx, getImageList(query)),
                    getTargetLastComment(i.contentIdx, getLastCommentList(query))
                )
            )
        }
        return returnList
    }

    private suspend fun getBookList(query : String?): List<Book> {
        return bookRepository.getBook(query).getOrNull()!!
    }

    private suspend fun getUserList(query : String?): List<User> {
        return userRepository.getUserWithContentId(query).getOrNull()!!
    }

    private suspend fun getMovieList(query : String?): List<Movie> {
        return movieRepository.getMovies(query).getOrNull()!!
    }

    private suspend fun getContentList(query : String?): List<Content> {
        return contentRepository.getContents(query).getOrNull()!!
    }

    private suspend fun getImageList(query : String?): List<Image> {
        return imageRepository.getImages(query).getOrNull()!!
    }

    private suspend fun getLastCommentList(query : String?): List<Comment> {
        return commentRepository.getLastComment(query).getOrNull()!!
    }

    private fun getTargetUser(
        contentId: Int,
        targetList: List<User>
    ): User = targetList.find { it.contentIdx == contentId }!!

    private fun getTargetBook(
        contentId: Int,
        targetList: List<Book>
    ): Book? = targetList.find { it.contentIdx == contentId }

    private fun getTargetMovie(
        contentId: Int,
        targetList: List<Movie?>
    ): Movie? = targetList.find { it?.contentIdx == contentId }

    private fun getTargetImages(
        contentId: Int,
        targetList: List<Image>
    ): List<String>? = targetList.find { it.contentIdx == contentId }?.images

    private fun getTargetLastComment(
        contentId: Int,
        targetList: List<Comment>
    ): Comment? = targetList.find { it.contentId == contentId }

}