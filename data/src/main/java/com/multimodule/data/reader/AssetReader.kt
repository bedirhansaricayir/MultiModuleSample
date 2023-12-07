package com.multimodule.data.reader

import com.multimodule.data.model.PositionAssetModel
import com.multimodule.data.model.SatelliteDetailAssetModel
import com.multimodule.data.model.SatelliteListAssetModel


/**
 * Created by bedirhansaricayir on 7.12.2023.
 */
interface AssetReader {

    fun readPositions(id: Int?): PositionAssetModel?
    fun readSatelliteDetail(id: Int?): SatelliteDetailAssetModel?
    fun readSatelliteList(): List<SatelliteListAssetModel>?
}