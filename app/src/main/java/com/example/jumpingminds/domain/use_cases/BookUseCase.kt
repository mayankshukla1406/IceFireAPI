package com.example.jumpingminds.domain.use_cases

import android.util.Log
import androidx.compose.ui.text.toLowerCase
import androidx.room.Entity
import com.example.jumpingminds.MainActivity
import com.example.jumpingminds.Resource
import com.example.jumpingminds.data.local.Book.BookDao
import com.example.jumpingminds.data.local.Book.BookEntity
import com.example.jumpingminds.data.remote.dto.BookDto
import com.example.jumpingminds.domain.repository.IceFireRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.util.*
import javax.inject.Inject

class BookUseCase @Inject constructor(
    private val iceFireRepository: IceFireRepository,
    private val bookDao: BookDao,
) {
    private var cachedBooks: List<BookEntity>? = null

    suspend fun getBooks(): Resource<List<BookDto>> {
        return if (checkCache()) {
            var filteredBooks = cachedBooks!!.map { it.toBookDto() }

            Resource.Success(filteredBooks)
        } else {
            val resource = iceFireRepository.getBooks()
            if (resource is Resource.Success) {
                resource.data?.let {
                    cachedBooks = resource.data.map { it.toBookEntity() }
                    cachedBooks?.let { bookEntities ->
                        bookDao.insertBooks(bookEntities)
                    }
                    cachedBooks!!.map { it.toBookDto() }
                } ?: Resource.Error("Null Data Received from API", null)
            } else {
                Resource.Error(resource.message ?: "Unknown Error Occurred", null)
            }
            resource
        }
    }

    private suspend fun checkCache(): Boolean {
        cachedBooks = bookDao.getBooks()
        return cachedBooks?.let {
            return it.isNotEmpty()
        } ?: false
    }

    suspend fun invalidateCache() {
        cachedBooks = null
    }
}
