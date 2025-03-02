package com.xsat.bocchitherock.ui.screen.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xsat.bocchitherock.data.BocchiRepository
import com.xsat.bocchitherock.ui.components.BocchiListItem
import com.xsat.bocchitherock.ui.screen.home.ViewModelFactory
import com.xsat.bocchitherock.ui.theme.BocchiTheRockTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(factory = ViewModelFactory(BocchiRepository())),
    onBackClick: () -> Unit
) {
    val listState = rememberLazyListState()
    val favoriteBocchi = viewModel.favoriteBocchi.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "List Favorit") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (favoriteBocchi.value.isEmpty()) {
                Text(
                    text = "Tidak ada favorit",
                    modifier = Modifier.padding(16.dp)
                        .align(androidx.compose.ui.Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(bottom = 18.dp)
                ) {
                    items(favoriteBocchi.value) { bocchi ->
                        BocchiListItem(
                            name = bocchi.name,
                            photoUrl = bocchi.photoUrl,
                            modifier = Modifier.fillMaxWidth(),
                            navigateToDetail = {}
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BocchiTheRockTheme {
        FavoriteScreen(onBackClick = {})
    }
}