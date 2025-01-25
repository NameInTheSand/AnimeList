package com.ukrdroiddev.presentation.generics

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LabelMediumText(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        style = MaterialTheme.typography.labelMedium,
        maxLines = 1
    )
}