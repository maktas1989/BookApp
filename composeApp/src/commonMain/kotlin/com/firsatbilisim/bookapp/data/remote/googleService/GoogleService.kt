package com.firsatbilisim.bookapp.data.remote.googleService

import com.firsatbilisim.bookapp.data.remote.google.GoogleRemote
import com.firsatbilisim.bookapp.data.remote.google.GoogleResponse
import com.firsatbilisim.bookapp.data.remote.googleApi.GoogleApi
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GoogleService: GoogleApi() {
    suspend fun searchBooks(query: String): GoogleResponse = client.get {
        apiUrl("/volumes")
        parameter("q", query)
    }.body()

    suspend fun getBookDetail(bookId: String): GoogleRemote = client.get {
        apiUrl("/volumes/$bookId")
    }.body()



}