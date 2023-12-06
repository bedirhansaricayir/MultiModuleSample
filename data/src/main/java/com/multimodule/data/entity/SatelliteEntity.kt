package com.multimodule.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */
@Entity(tableName = "Satellite")
data class SatelliteEntity(
    @PrimaryKey
    val id: Int?,
    val costPerLaunch: Int?,
    val firstFlight: String?,
    val height: Int?,
    val mass: Int?,
)