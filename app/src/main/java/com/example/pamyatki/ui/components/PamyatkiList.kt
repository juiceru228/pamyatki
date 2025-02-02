package com.example.pamyatki.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PamyatkiList(items: List<String>){
    LazyColumn( modifier = Modifier.fillMaxWidth()) {
        items(items, key = { it }) { item ->
            Text(
                fontSize = 30.sp,
                text = item,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
