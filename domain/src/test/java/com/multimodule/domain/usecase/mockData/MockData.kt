package com.multimodule.domain.usecase.mockData

import com.multimodule.domain.model.Position
import com.multimodule.domain.model.PositionList
import com.multimodule.domain.model.Positions
import com.multimodule.domain.model.SatelliteDetail
import com.multimodule.domain.model.SatelliteList


/**
 * Created by bedirhansaricayir on 9.12.2023.
 */
object MockData {

    fun getSatelliteListData(): List<SatelliteList>? {
        return listOf(
            SatelliteList(id = 1, active = true, name = "satellite-1"),
            SatelliteList(id = 2, active = true, name = "falcon-heavy"),
            SatelliteList(id = 3, active = false, name = "satellite-2")
        )
    }
    fun getPositionData(): Position? {
        return Position(list = listOf(
            PositionList(id = 2, positions = listOf(
                Positions(0.864328522,0.646450855),
                Positions(0.459465477,0.323434315),
                Positions(0.213733123,0.239480024)
            ))
        ))
    }

    fun getSatelliteFromCache(): SatelliteDetail {
        return SatelliteDetail(id = 1,costPerLaunch = 600000, firstFlight = "12.04.2015",height = 190,mass = 250)
    }

    fun getSatelliteFromAsset(): SatelliteDetail {
        return SatelliteDetail(id = 2,costPerLaunch = 350600, firstFlight = "25.09.2019",height = 256,mass = 126)
    }
}