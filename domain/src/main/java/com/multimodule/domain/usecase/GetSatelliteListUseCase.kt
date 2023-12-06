package com.multimodule.domain.usecase

import com.multimodule.domain.model.SatelliteList
import com.multimodule.domain.repository.SatelliteGateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


/**
 * Created by bedirhansaricayir on 7.12.2023.
 */

class GetSatelliteListUseCase @Inject constructor(private val repository: SatelliteGateRepository) {

    suspend operator fun invoke(): Flow<Response<List<SatelliteList>>> = flow {
        try {
            emit(Response.Loading)

            emit(Response.Success(data = repository.getSatelliteList()))
        } catch (e: IOException) {
            emit(Response.Error(errorMessage = "NO_INTERNET"))
        } catch (e: Exception) {
            emit(Response.Error(errorMessage = e.message ?: "UNKNOWN"))
        }
    }
}