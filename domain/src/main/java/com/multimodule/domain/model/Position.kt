package com.multimodule.domain.model


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */
data class Position(
    val list: List<PositionList>?
)

data class PositionList(
    val id: String?,
    val positions: List<Positions>?
)

data class Positions(
    val posX: Double?,
    val posY: Double?
)
