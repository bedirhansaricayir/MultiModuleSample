package com.multimodule.home.ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.multimodule.home.vm.ListViewModel


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */

@Composable
fun ListScreen(
    onAction: () -> Unit,
    viewModel: ListViewModel = hiltViewModel()

) {
    Button(onClick = {onAction.invoke()}) {
        Text("Go to Detail")
    }
}