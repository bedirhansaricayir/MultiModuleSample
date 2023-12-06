package com.multimodule.domain.repository

import com.multimodule.domain.model.Position
import com.multimodule.domain.model.SatelliteDetail
import com.multimodule.domain.model.SatelliteList


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */
interface SatelliteGateRepository {

    suspend fun getSatelliteList(): List<SatelliteList>?

    suspend fun getSatelliteFromCache(id: Int?): SatelliteDetail?

    suspend fun insertSatelliteCache(id: SatelliteDetail?)

    suspend fun getSatelliteFromAsset(id: Int?): List<SatelliteDetail>?

    suspend fun getPosition(id: Int?): Position?
}