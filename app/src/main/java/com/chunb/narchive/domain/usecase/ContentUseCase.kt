package com.chunb.narchive.domain.usecase

import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.data.remote.response.Image
import com.chunb.narchive.data.remote.response.Movie
import com.chunb.narchive.data.remote.response.Book
import com.chunb.narchive.data.remote.response.Comment
import com.chunb.narchive.data.remote.response.ResponseFeed
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
    private val contentRepository: ContentRepository,
) {

    suspend fun mapToContents(page : Int) : List<Contents> {
        val contents = mutableListOf<Contents>()
//        for (i in getFeedData(page)) {
//            contents.add(
//                Contents(
//                    contentIdx = i.contentIdx,
//                    user = mapUser(i),
//                    content = mapContent(i),
//                    book = mapBook(i),
//                    movie = mapMovie(i),
//                    i.image,
//                    comments = mapComment(i)
//                )
//            )
//        }
        return contents
    }


    private suspend fun getFeedData(page : Int) : List<ResponseFeed> {
        return contentRepository.getFeed(page).getOrThrow()
    }

//    private fun mapUser(data : ResponseFeed) : User {
//        return User(data.userProfile, data.userNickName)
//    }
//
//    private fun mapContent(data : ResponseFeed) : Content {
//        return Content(data.updatedAt, data.content, data.mood, data.commentCount)
//    }
//
//    private fun mapBook(data : ResponseFeed) : Book? {
//        return Book(data.bookThumbnail, data.bookTitle, data.bookAuthor, data.bookPublisher, data.bookPublishedDate)
//    }
//
//    private fun mapMovie(data : ResponseFeed) : Movie {
//        return Movie(data.movieThumbnail, data.movieTitle, data.movieDirector, data.movieActor, data.movieReleaseYear)
//    }
//
//    private fun mapComment(data : ResponseFeed) : Comment? {
//        return Comment(null, data.commentContent, data.commentNickName, null, null)
//    }

}