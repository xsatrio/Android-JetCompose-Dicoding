package com.xsat.bocchitherock.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.xsat.bocchitherock.data.BocchiRepository
import com.xsat.bocchitherock.ui.components.BocchiListItem
import com.xsat.bocchitherock.ui.components.Search
import com.xsat.bocchitherock.ui.navigation.Screen
import com.xsat.bocchitherock.ui.theme.BocchiTheRockTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(BocchiRepository()))
) {
    val bocchis = viewModel.bocchis.collectAsState()
    val query = viewModel.query.collectAsState().value
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Bocchi The Rock") },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Favorite.route) }) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    IconButton(onClick = { navController.navigate(Screen.About.route) }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Profile",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            )
        },
        modifier = modifier
    )
    { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Search(
                query = query,
                onQueryChange = viewModel::search
            )
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(bottom = 18.dp)
            ) {
                items(bocchis.value.size) { index ->
                    val bocchi = bocchis.value[index]
                    BocchiListItem(
                        name = bocchi.name,
                        photoUrl = bocchi.photoUrl,
                        modifier = Modifier.fillMaxWidth(),
                        navigateToDetail = {
                            navController.navigate(Screen.DetailBocchi.createRoute(bocchi.id))
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    BocchiTheRockTheme {
        HomeScreen(navController = NavController(LocalContext.current))
    }
}
