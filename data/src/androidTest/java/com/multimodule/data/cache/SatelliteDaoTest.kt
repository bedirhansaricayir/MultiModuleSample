package com.multimodule.data.cache

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.multimodule.data.entity.SatelliteEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith



/**
 * Created by bedirhansaricayir on 9.12.2023.
 */

@RunWith(AndroidJUnit4::class)
@SmallTest
class SatelliteDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: SatelliteDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.satelliteDao()
    }

    @After
    fun tearDown() {
        database.close()
    }


    @Test
    fun insertItem_expectedThatEntity() = runBlocking {
        val satelliteEntity = SatelliteEntity(id = 1,costPerLaunch = 50000,firstFlight = "12.12.2015",height = 233,mass = 142)
        dao.insertItem(satelliteEntity)

        val getSatelliteById = dao.getSatelliteById(1)

        assertThat(getSatelliteById).isEqualTo(satelliteEntity)
    }

    @Test
    fun insertItem_expectedThatAllEntities() = runBlocking {
        val satelliteEntity1 = SatelliteEntity(id = 1,costPerLaunch = 50000,firstFlight = "12.12.2015",height = 233,mass = 142)
        val satelliteEntity2 = SatelliteEntity(id = 2,costPerLaunch = 35000,firstFlight = "08.11.2011",height = 225,mass = 169)

        dao.insertItem(satelliteEntity1,satelliteEntity2)

        val getSatelliteFrom1 = dao.getSatelliteById(1)
        val getSatelliteFrom2 = dao.getSatelliteById(2)

        assertThat(getSatelliteFrom1).isEqualTo(satelliteEntity1)
        assertThat(getSatelliteFrom2).isEqualTo(satelliteEntity2)
    }
}