package com.binteckh.consultacep.ui.components.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binteckh.consultacep.R
import com.binteckh.consultacep.utils.ZipCodeVisualTransformation
import com.binteckh.consultacep.viewmodels.search.SearchState
import com.binteckh.consultacep.viewmodels.search.SearchViewModel

@Composable
fun SearchTextField(
    searchViewModel: SearchViewModel,
    searchState: SearchState
) {
    TextField(
        value = searchState.cep,
        onValueChange = { input ->
            val digits = input.filter { it.isDigit() }.take(8)
            searchViewModel.updateCep(digits)
        },
        modifier = Modifier
            .clip(shape = CircleShape)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary),
        placeholder = {
            Text(
                text = stringResource(R.string.placeholder_CEP),
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 22.sp
                ),
            )
        },
        leadingIcon = {
            Box(
                modifier = Modifier
                    .padding(start = 12.dp)
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }
        },
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            fontSize = 22.sp
        ),
        visualTransformation = ZipCodeVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
            cursorColor = MaterialTheme.colorScheme.onSecondary,
            focusedContainerColor = MaterialTheme.colorScheme.secondary,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
            focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSecondary,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary
        ),
        singleLine = true,
        maxLines = 1
    )
    Spacer(modifier = Modifier.height(32.dp))
}