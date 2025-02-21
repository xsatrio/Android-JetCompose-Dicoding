package com.xsat.bocchitherock.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import coil.compose.AsyncImage
import com.xsat.bocchitherock.ui.theme.BocchiTheRockTheme

@Composable
fun BocchiListItem(
    name: String,
    photoUrl: String,
    modifier: Modifier = Modifier,
    navigateToDetail: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .fillMaxWidth()
            .clickable { navigateToDetail() }
    ) {
        Row(
            modifier = modifier.padding(end = 8.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BocchiListItemPreview() {
    BocchiTheRockTheme {
        BocchiListItem(
            name = "Hitori Gotoh",
            photoUrl = "https://raw.githubusercontent.com/xsatrio/Android-Pemula-Dicoding/refs/heads/main/app/src/main/res/drawable/hitori.png",
            navigateToDetail = {})
    }
}