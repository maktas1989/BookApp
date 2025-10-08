package com.firsatbilisim.bookapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform