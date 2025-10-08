package com.firsatbilisim.bookapp.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.firsatbilisim.bookapp.presentation.home.BookHomeViewModel
import org.koin.compose.koinInject
import coil3.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(
    navController: NavController,
    bookId: String
) {
    val detailViewModel: BookDetailViewModel = koinInject()
    val state by detailViewModel.state.collectAsState()

    LaunchedEffect(bookId) {
        detailViewModel.loadBookDetail(bookId)
    }

    val book = state.bookDetail

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(book?.title ?: "Detay Yükleniyor", maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Geri"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        val scrollState = rememberScrollState()

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Kitap bilgisi yükleniyor...")
                    }
                }
            }

            state.errorMessage != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.errorMessage ?: "Detay verisi bulunamadı.",
                        textAlign = TextAlign.Center,
                        color = Color.Red
                    )
                }
            }

            book != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(scrollState)
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    book.coverImageUrl?.let { url ->
                        val secureUrl = url.replace("http://", "https://")
                        AsyncImage(
                            model = secureUrl,
                            contentDescription = book.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(200.dp, 300.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.LightGray)
                        )
                    } ?: Box(
                        modifier = Modifier
                            .size(200.dp, 300.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Resim Yok", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = book.title,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = book.authors,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Yayıncı: ${book.publisher ?: "Bilinmiyor"}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DetailChip(
                            label = "Sayfa",
                            value = "${book.pageCount ?: "N/A"}"
                        )
                        DetailChip(
                            label = "Puan",
                            icon = {
                                Icon(
                                    Icons.Filled.Star,
                                    contentDescription = "Puan",
                                    modifier = Modifier.size(16.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                            value = (book.averageRating ?: 0.0).toString()
                        )
                        DetailChip(
                            label = "Yıl",
                            value = book.publishedDate?.split("-")?.firstOrNull() ?: "N/A"
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Özet",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = book.description ?: "Bu kitap için bir özet bulunmamaktadır.",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        }
    }
}


@Composable
fun DetailChip(label: String, value: String, icon: @Composable (() -> Unit)? = null) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) {
                icon()
                Spacer(modifier = Modifier.width(4.dp))
            }
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
