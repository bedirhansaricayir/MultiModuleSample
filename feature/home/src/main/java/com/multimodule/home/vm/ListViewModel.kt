package com.multimodule.home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimodule.domain.model.SatelliteList
import com.multimodule.domain.usecase.GetSatelliteListUseCase
import com.multimodule.domain.usecase.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */
@HiltViewModel
class ListViewModel @Inject constructor(
    private val getSatelliteListUseCase: GetSatelliteListUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<ListUIState> = MutableStateFlow(ListUIState.Loading)
    val state = _state.asStateFlow()

    init {
        getSatelliteListUseCase()
    }

    private fun getSatelliteListUseCase() {
        viewModelScope.launch(Dispatchers.IO) {
            getSatelliteListUseCase.invoke().collect {
                when (it) {
                    is Response.Error -> _state.value = ListUIState.Error(it.errorMessage)
                    is Response.Loading -> _state.value = ListUIState.Loading
                    is Response.Success -> _state.value = ListUIState.Success(it.data)
                }
            }
        }
    }

    sealed class ListUIState {
        data object Loading : ListUIState()
        data class Success(val data: List<SatelliteList>?) : ListUIState()
        data class Error(val error: String?) : ListUIState()
    }
}