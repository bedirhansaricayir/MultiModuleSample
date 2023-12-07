package com.multimodule.domain.usecase

import com.multimodule.domain.model.SatelliteDetail
import com.multimodule.domain.repository.SatelliteGateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */
class GetSatelliteUseCase @Inject constructor(private val repository: SatelliteGateRepository) {

    suspend operator fun invoke(id: Int?): Flow<Response<SatelliteDetail>> = flow {
        try {
            emit(Response.Loading)
            val satelliteFromCache = repository.getSatelliteFromCache(id)
            if (satelliteFromCache != null) {
                emit(Response.Success(data = satelliteFromCache))
            } else {
                val satelliteFromAsset = repository.getSatelliteFromAsset(id)
                repository.insertSatelliteCache(satelliteFromAsset)
                emit(Response.Success(data = satelliteFromAsset))
            }
        } catch (e: IOException) {
            emit(Response.Error(errorMessage = "NO_INTERNET"))
        } catch (e: Exception) {
            emit(Response.Error(errorMessage = e.message ?: "UNKNOWN"))
        }
    }
}