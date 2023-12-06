package com.multimodule.data.mapper

import com.multimodule.data.entity.SatelliteEntity
import com.multimodule.data.model.PositionAssetModel
import com.multimodule.data.model.SatelliteDetailAssetModel
import com.multimodule.data.model.SatelliteListAssetModel
import com.multimodule.domain.model.Position
import com.multimodule.domain.model.SatelliteDetail
import com.multimodule.domain.model.SatelliteList


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */
interface Mapper {
    fun SatelliteEntity.cacheToDomain(): SatelliteDetail
    fun SatelliteDetail.domainToCache(): SatelliteEntity
    fun PositionAssetModel.assetToDomain(): Position
    fun SatelliteDetailAssetModel.assetToDomain(): SatelliteDetail
    fun SatelliteListAssetModel.assetToDomain(): SatelliteList
}