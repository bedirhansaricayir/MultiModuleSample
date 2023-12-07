package com.multimodule.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.multimodule.domain.model.SatelliteList
import com.multimodule.home.vm.ListViewModel


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */

@Composable
fun ListScreen(
    onAction: (id: Int, name: String) -> Unit,
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
                is ListViewModel.ListUIState.Success -> List((uiState as ListViewModel.ListUIState.Success).data.orEmpty(), onClick = onAction)

            }
        }
    }
}

@Composable
fun List(
    list: List<SatelliteList>?,
    onClick: (itemId: Int, name: String) -> Unit
) {
    LazyColumn {
        itemsIndexed(items = list.orEmpty()) { index, item ->
            SatelliteItem(
                item = item,
                onClick = onClick
            )
            if (index < list!!.size - 1) {
                Divider(modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun SatelliteItem(
    modifier: Modifier = Modifier,
    item: SatelliteList,
    onClick: (itemId: Int, name: String) -> Unit
) {
    val isActive = item.active == true
    Row(
        modifier = modifier.fillMaxWidth()
            .clickable { onClick.invoke(item.id!!,item.name!!) },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .clip(CircleShape)
                .size(15.dp)
                .background(if (isActive) Color.Green else Color.Red, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = item.name!!,
                color = if (isActive) Color.Black else Color.Gray.copy(alpha = 0.5f),
                fontWeight = FontWeight.W700
            )
            Text(
                text = if (isActive) "Active" else "Passive",
                color = if (isActive) Color.Black else Color.Gray.copy(alpha = 0.5f),
                fontWeight = FontWeight.Normal
            )
        }
    }
}