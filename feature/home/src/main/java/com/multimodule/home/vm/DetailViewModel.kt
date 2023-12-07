package com.multimodule.home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimodule.domain.model.Positions
import com.multimodule.domain.model.SatelliteDetail
import com.multimodule.domain.usecase.GetPositionUseCase
import com.multimodule.domain.usecase.GetSatelliteUseCase
import com.multimodule.domain.usecase.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by bedirhansaricayir on 7.12.2023.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSatelliteUseCase: GetSatelliteUseCase,
    private val getPositionUseCase: GetPositionUseCase,
) : ViewModel() {

    private val _stateDetail: MutableStateFlow<DetailUIState> = MutableStateFlow(DetailUIState.Loading)
    val stateDetail = _stateDetail.asStateFlow()

    private val _statePosition: MutableStateFlow<PositionUIState> = MutableStateFlow(PositionUIState.Loading)
    val statePosition = _statePosition.asStateFlow()

    private val _currentPosition: MutableStateFlow<String> = MutableStateFlow("")
    val currentPosition = _currentPosition.asStateFlow()

    sealed class DetailUIState {
        data object Loading : DetailUIState()
        data class Success(val data: SatelliteDetail?) : DetailUIState()
        data class Error(val error: String?) : DetailUIState()
    }

    sealed class PositionUIState {
        data object Loading : PositionUIState()
        data class Success(val data: List<Positions>?) : PositionUIState()
        data class Error(val error: String?) : PositionUIState()
    }

    fun getSatellite(id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            getSatelliteUseCase.invoke(id).collect {
                when (it) {
                    is Response.Error -> _stateDetail.value = DetailUIState.Error(it.errorMessage)
                    is Response.Loading -> _stateDetail.value = DetailUIState.Loading
                    is Response.Success -> _stateDetail.value = DetailUIState.Success(it.data)
                }
            }
        }
    }

    fun getPositionUseCase(id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            getPositionUseCase.invoke(id).collect {
                when (it) {
                    is Response.Error -> _statePosition.value = PositionUIState.Error(it.errorMessage)
                    is Response.Loading -> _statePosition.value = PositionUIState.Loading
                    is Response.Success -> {
                        val positionList = it.data?.list?.find { it.id == id }?.positions
                        _statePosition.value = PositionUIState.Success(positionList)

                        callPositions(positionList) { position ->
                            _currentPosition.value = "(${position.posX},${position.posY})"
                        }
                    }
                }
            }
        }
    }

    private fun callPositions(
        positionList: List<Positions>?,
        callPosition: (Positions) -> Unit
    ) {
        viewModelScope.launch {
            while (true) {
                for (position in positionList!!) {
                    callPosition.invoke(position)
                    delay(3000)
                }
            }
        }

    }
}