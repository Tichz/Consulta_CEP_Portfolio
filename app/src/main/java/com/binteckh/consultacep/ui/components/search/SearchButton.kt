package com.binteckh.consultacep.ui.components.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun SearchButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(shape = CircleShape)
            .background(MaterialTheme.colorScheme.tertiary)
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 36.dp,
                vertical = 12.dp
            )
    ) {
        Text(
            text = "Buscar",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onTertiary
        )
    }
    Spacer(modifier = Modifier.height(32.dp))
}