package com.multimodule.home.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimodule.domain.usecase.GetSatelliteUseCase
import com.multimodule.domain.usecase.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */
@HiltViewModel
class ListViewModel @Inject constructor(
    private val getSatelliteUseCase: GetSatelliteUseCase,
) : ViewModel() {

    init {
        getSatellite()
    }

    private fun getSatellite() {
        viewModelScope.launch(Dispatchers.IO) {
            getSatelliteUseCase.invoke().collect {
                when (it) {
                    is Response.Error -> {
                        Log.d("Result",it.errorMessage)
                    }
                    Response.Loading -> {
                        Log.d("Result","Loading")
                    }
                    is Response.Success -> {
                        Log.d("Result",it.data?.id.toString())
                    }
                }
            }
        }
    }
}