package com.multimodule.data.model


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */
data class PositionAssetModel(
    val list: List<PositionListAssetModel>?,
)

data class PositionListAssetModel(
    val id: String?,
    val positions: List<PositionsAssetModel>?,
)

data class PositionsAssetModel(
    val posX: Double?,
    val posY: Double?,
)
