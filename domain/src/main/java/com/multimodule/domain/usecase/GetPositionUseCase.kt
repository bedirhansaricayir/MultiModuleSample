package com.multimodule.domain.usecase

import com.multimodule.domain.model.Position
import com.multimodule.domain.repository.SatelliteGateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


/**
 * Created by bedirhansaricayir on 7.12.2023.
 */
class GetPositionUseCase @Inject constructor(private val repository: SatelliteGateRepository) {

    suspend operator fun invoke(id: Int?): Flow<Response<Position>> = flow {
        try {
            emit(Response.Loading)

            emit(Response.Success(data = repository.getPosition(id)))
        } catch (e: IOException) {
            emit(Response.Error(errorMessage = "NO_INTERNET"))
        } catch (e: Exception) {
            emit(Response.Error(errorMessage = e.message ?: "UNKNOWN"))
        }
    }
}