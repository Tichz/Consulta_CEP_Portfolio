package com.binteckh.consultacep.ui.components.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun ScreenTitle(title: String, topPadding: Dp, bottomPadding: Dp) {
    Text(
        modifier = Modifier.padding(
            top = topPadding,
            bottom = bottomPadding
        ),
        text = title,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onPrimary
    )
}