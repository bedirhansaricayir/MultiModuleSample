package com.multimodule.home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimodule.domain.model.Position
import com.multimodule.domain.model.SatelliteDetail
import com.multimodule.domain.usecase.GetPositionUseCase
import com.multimodule.domain.usecase.GetSatelliteUseCase
import com.multimodule.domain.usecase.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    sealed class DetailUIState {
        data object Loading : DetailUIState()
        data class Success(val data: List<SatelliteDetail>?) : DetailUIState()
        data class Error(val error: String?) : DetailUIState()
    }

    sealed class PositionUIState {
        data object Loading : PositionUIState()
        data class Success(val data: Position?) : PositionUIState()
        data class Error(val error: String?) : PositionUIState()
    }

    init {
        getSatellite()
        getPositionUseCase()
    }

    private fun getSatellite() {
        viewModelScope.launch(Dispatchers.IO) {
            getSatelliteUseCase.invoke().collect {
                when (it) {
                    is Response.Error -> _stateDetail.value = DetailUIState.Error(it.errorMessage)
                    is Response.Loading -> _stateDetail.value = DetailUIState.Loading
                    is Response.Success -> _stateDetail.value = DetailUIState.Success(it.data)
                }
            }
        }
    }

    private fun getPositionUseCase() {
        viewModelScope.launch(Dispatchers.IO) {
            getPositionUseCase.invoke().collect {
                when (it) {
                    is Response.Error -> _statePosition.value = PositionUIState.Error(it.errorMessage)
                    is Response.Loading -> _statePosition.value = PositionUIState.Loading
                    is Response.Success -> _statePosition.value = PositionUIState.Success(it.data)
                }
            }
        }
    }
}