package com.multimodule.home.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.multimodule.home.vm.ListViewModel


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */

@Composable
fun DetailScreen(
    viewModel: ListViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    Text("İts Detail")
}