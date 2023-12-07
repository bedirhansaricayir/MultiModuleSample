package com.multimodule.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.multimodule.domain.model.SatelliteDetail
import com.multimodule.home.vm.DetailViewModel


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */

@Composable
fun DetailScreen(
    itemId: Int,
    name: String,
    viewModel: DetailViewModel = hiltViewModel()
) {

    val uiState by viewModel.stateDetail.collectAsStateWithLifecycle()
    val positionState by viewModel.statePosition.collectAsStateWithLifecycle()
    val currentPosition by viewModel.currentPosition.collectAsStateWithLifecycle()


    LaunchedEffect(key1 = itemId) {
        viewModel.getSatellite(id = itemId)
        viewModel.getPositionUseCase(id = itemId)
    }


    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState) {
            is DetailViewModel.DetailUIState.Error -> Text(text = (uiState as DetailViewModel.DetailUIState.Error).error.orEmpty())
            is DetailViewModel.DetailUIState.Loading -> CircularProgressIndicator()
            is DetailViewModel.DetailUIState.Success -> SatelliteItem(
                satelliteName = name,
                item = (uiState as DetailViewModel.DetailUIState.Success).data
            )

        }

        when (positionState) {
            is DetailViewModel.PositionUIState.Error -> Text(text = (positionState as DetailViewModel.PositionUIState.Error).error.orEmpty())
            is DetailViewModel.PositionUIState.Loading -> Text(text = "Loading..")
            is DetailViewModel.PositionUIState.Success -> PositionItem(position = currentPosition)
        }

    }
}


@Composable
fun SatelliteItem(
    modifier: Modifier = Modifier,
    satelliteName: String,
    item: SatelliteDetail?
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = satelliteName,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text(
            text = "${item?.firstFlight}",
            fontWeight = FontWeight.W700,
            color = Color.Gray,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = createAnnotatedString(
                label = "Height/Mass",
                value = "${item?.height}/${item?.mass}"
            )
        )

        Text(
            text = createAnnotatedString(label = "Cost", value = "${item?.costPerLaunch}")
        )
    }
}

@Composable
fun PositionItem(
    position: String,
) {
    Text(
        modifier = Modifier.padding(vertical = 16.dp),
        text = createAnnotatedString(label = "Last Position", value = position)
    )
}

@Composable
fun createAnnotatedString(label: String, value: String): AnnotatedString {
    return AnnotatedString.Builder().apply {
        pushStyle(SpanStyle(fontWeight = FontWeight.Black))
        append(label)
        pop()
        append(": $value")
    }.toAnnotatedString()
}