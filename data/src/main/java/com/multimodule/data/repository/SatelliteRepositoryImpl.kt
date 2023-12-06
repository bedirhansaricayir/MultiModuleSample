package com.multimodule.data.repository

import com.multimodule.data.cache.SatelliteDao
import com.multimodule.data.mapper.SatelliteMapper.cacheToDomain
import com.multimodule.data.mapper.SatelliteMapper.domainToCache
import com.multimodule.domain.model.Position
import com.multimodule.domain.model.SatelliteDetail
import com.multimodule.domain.model.SatelliteList
import com.multimodule.domain.repository.SatelliteGateRepository
import javax.inject.Inject


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */
class SatelliteRepositoryImpl @Inject constructor(
    private val dao: SatelliteDao,
) : SatelliteGateRepository {
    override suspend fun getSatelliteList(): SatelliteList {
        TODO("Not yet implemented")
    }

    override suspend fun getSatelliteFromCache(id: Int?): SatelliteDetail? {
        return dao.getSatelliteById(id)?.cacheToDomain()
    }

    override suspend fun insertSatelliteCache(satelliteDetail: SatelliteDetail?) {
        satelliteDetail?.domainToCache()?.let { dao.insertItem(it) }
    }

    override suspend fun getSatelliteFromAsset(id: Int?): SatelliteDetail {
        TODO("Not yet implemented")
    }

    override suspend fun getPosition(id: Int?): Position {
        TODO("Not yet implemented")
    }

}