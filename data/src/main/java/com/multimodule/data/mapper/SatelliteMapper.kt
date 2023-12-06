package com.multimodule.data.mapper

import com.multimodule.data.entity.SatelliteEntity
import com.multimodule.data.model.PositionAssetModel
import com.multimodule.data.model.PositionListAssetModel
import com.multimodule.data.model.PositionsAssetModel
import com.multimodule.data.model.SatelliteDetailAssetModel
import com.multimodule.data.model.SatelliteListAssetModel
import com.multimodule.domain.model.Position
import com.multimodule.domain.model.PositionList
import com.multimodule.domain.model.Positions
import com.multimodule.domain.model.SatelliteDetail
import com.multimodule.domain.model.SatelliteList


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */
object SatelliteMapper : Mapper {

    override fun SatelliteEntity.cacheToDomain(): SatelliteDetail = with(this) {
        SatelliteDetail(
            id = id,
            costPerLaunch = costPerLaunch,
            firstFlight = firstFlight,
            height = height,
            mass = mass
        )
    }

    override fun SatelliteDetail.domainToCache() = with(this) {
        SatelliteEntity(
            id = id,
            costPerLaunch = costPerLaunch,
            firstFlight = firstFlight,
            height = height,
            mass = mass
        )
    }

    override fun PositionAssetModel.assetToDomain() = with(this) {
        Position(
            list = list?.map { mapToPositionList(it) }
        )
    }

    override fun SatelliteDetailAssetModel.assetToDomain() = with(this) {
        SatelliteDetail(id, costPerLaunch, firstFlight, height, mass)
    }

    override fun SatelliteListAssetModel.assetToDomain() = with(this) {
        SatelliteList(id, active, name)
    }

    private fun mapToPositionList(model: PositionListAssetModel) = with(model) {
        PositionList(
            id = id,
            positions = positions?.map { mapToPositions(it) }
        )
    }

    private fun mapToPositions(model: PositionsAssetModel) = with(model) {
        Positions(
            posX = posX,
            posY = posY
        )
    }
}