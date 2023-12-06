package com.multimodule.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.multimodule.data.entity.SatelliteEntity


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */
@Dao
interface SatelliteDao {

    @Query("SELECT * FROM Satellite where id=:id")
    fun getSatelliteById(id: Int?): SatelliteEntity?

    @Insert
    suspend fun insertItem(vararg item: SatelliteEntity)
}