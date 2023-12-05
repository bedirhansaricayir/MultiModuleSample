package com.multimodule.home.ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */

@Composable
fun ListScreen(
    onAction: () -> Unit,
) {
    Button(onClick = {onAction.invoke()}) {
        Text("Go to Detail")
    }
}