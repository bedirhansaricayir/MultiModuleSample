package com.multimodule.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.multimodule.domain.model.SatelliteList
import com.multimodule.home.vm.ListViewModel


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */

@Composable
fun ListScreen(
    onAction: () -> Unit,
    viewModel: ListViewModel = hiltViewModel()

) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(topBar = {

    }) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState) {
                is ListViewModel.ListUIState.Error -> Text(text = (uiState as ListViewModel.ListUIState.Error).error.orEmpty())
                is ListViewModel.ListUIState.Loading -> CircularProgressIndicator()
                is ListViewModel.ListUIState.Success -> List((uiState as ListViewModel.ListUIState.Success).data.orEmpty())

            }
        }
    }
}

@Composable
fun List(
    list: List<SatelliteList>?,
) {
    LazyColumn {
        items(items = list.orEmpty()) {
            Text(text = it.name.orEmpty())
        }
    }
}