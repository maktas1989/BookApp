package com.firsatbilisim.bookapp.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firsatbilisim.bookapp.domain.model.GoogleModel
import org.koin.compose.koinInject
import kotlinx.coroutines.delay
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import bookapp.composeapp.generated.resources.Res
import bookapp.composeapp.generated.resources.close_hint
import bookapp.composeapp.generated.resources.search_hint
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

private const val DEFAULT_SEARCH_QUERY = "android"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookHomeScreen(
    navController: NavController,
) {
    val viewModel: BookHomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    var isSearching by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (state.search.isEmpty()) {
            viewModel.loadBooksBySearch(DEFAULT_SEARCH_QUERY)
        }
    }

    LaunchedEffect(state.search) {
        if (state.search.isNotEmpty() && isSearching) {
            delay(500)
            viewModel.loadBooksBySearch(state.search)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (isSearching) {
                        TextField(
                            value = state.search,
                            onValueChange = {
                                viewModel.onSearchQueryChange(it)
                            },
                            placeholder = { Text(text = "Kitap Listesi...", color = Color.White.copy(alpha = 0.7f)) },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                unfocusedTextColor = Color.White,
                                focusedTextColor = Color.White,
                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor = Color.White,
                            )
                        )
                    } else {
                        Text("Kitap Listesi", fontWeight = FontWeight.Bold)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                actions = {
                    if (!isSearching) {
                        IconButton(
                            onClick = {
                                isSearching = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = stringResource(Res.string.search_hint),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    } else {
                        IconButton(
                            onClick = {
                                isSearching = false
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = stringResource(Res.string.close_hint),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
        ) {
            when {
                state.isError && state.errorMessage != null -> {
                    Text(
                        text = state.errorMessage!!,
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Red
                    )
                }
                state.isLoading && state.books.isEmpty() -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                !state.isLoading && state.books.isEmpty() && state.search.isNotEmpty() -> {
                    Text(
                        text = "Sonuç bulunamadı: ${state.search}",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    BookList(
                        books = state.books,
                        onBookClick = { book ->
                            navController.navigate("detail/${book.id}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BookList(
    books: List<GoogleModel>,
    onBookClick: (GoogleModel) -> Unit
) {
    LazyColumn(contentPadding = PaddingValues(top = 8.dp)) {
        items(books) { book ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onBookClick(book) }
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(width = 80.dp, height = 100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    book.coverImageUrl?.let { url ->
                        AsyncImage(
                            model = url,
                            contentDescription = book.title,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                        )
                    } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Kapak Yok",
                            style = TextStyle(fontSize = 10.sp),
                            color = Color.LightGray,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = book.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Yazar: ${book.authors}",
                        fontSize = 13.sp,
                        color = Color.DarkGray
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 0.dp),
                thickness = 1.dp,
                color = Color.LightGray.copy(alpha = 0.5f)
            )
        }
    }
}