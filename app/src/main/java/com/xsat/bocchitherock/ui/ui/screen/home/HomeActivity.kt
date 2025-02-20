package com.xsat.bocchitherock.ui.ui.screen.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.xsat.bocchitherock.data.BocchiRepository
import com.xsat.bocchitherock.ui.components.Search
import com.xsat.bocchitherock.ui.theme.BocchiTheRockTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeActivity(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(BocchiRepository()))
) {
    val bocchis = viewModel.bocchis.collectAsState()
    val query = viewModel.query

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        val listState = rememberLazyListState()
        Column(modifier = modifier) {
            TopAppBar(
                title = { Text(text = "Bocchi The Rock") },
                actions = {
                    IconButton(onClick = { /* TODO: Tambahkan Aksi Profile */ }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Profile"
                        )
                    }
                }
            )

            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                item {
                    Search(
                        query = query.toString(),
                        onQueryChange = viewModel::search
                    )
                }
                items(bocchis.value.size) { index ->
                    val bocchi = bocchis.value[index]
                    BocchiListItem(
                        name = bocchi.name,
                        photoUrl = bocchi.photoUrl,
                        detail = bocchi.detail,
                        modifier = Modifier.fillMaxWidth()
                        ,
                        navigateToDetail = { /* TODO: Tambahkan Aksi Navigasi */ }
                    )
                }
            }
        }
    }
}


@Composable
fun BocchiListItem(
    name: String,
    photoUrl: String,
    detail: String,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
) {
    Card (
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .fillMaxWidth()
            .clickable { /* TODO */ },
    ){
        Row (modifier = modifier.padding(8.dp)) {
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = detail,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeActivityPreview() {
    BocchiTheRockTheme {
        HomeActivity()
    }
}
