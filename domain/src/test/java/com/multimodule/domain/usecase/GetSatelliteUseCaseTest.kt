package com.multimodule.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.multimodule.domain.repository.SatelliteGateRepository
import com.multimodule.domain.usecase.mockData.MockData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Created by bedirhansaricayir on 9.12.2023.
 */
class GetSatelliteUseCaseTest {

    private lateinit var getSatelliteUseCase: GetSatelliteUseCase
    private lateinit var mockRepository: SatelliteGateRepository

    @Before
    fun setUp() {
        mockRepository = mockk()
        getSatelliteUseCase = GetSatelliteUseCase(mockRepository)
    }

    @Test
    fun `GetSatelliteUseCase should return loading and success from cache`() = runBlocking {
        val testId = 1
        val satelliteFromCache = MockData.getSatelliteFromCache()

        coEvery { mockRepository.getSatelliteFromCache(testId) } returns satelliteFromCache

        getSatelliteUseCase.invoke(testId).collect { result ->
            when (result) {
                is Response.Loading -> {
                    assertThat(result).isInstanceOf(Response.Loading::class.java)
                }
                is Response.Success -> {
                    assertThat(result).isInstanceOf(Response.Success::class.java)
                    assertThat(result.data).isEqualTo(MockData.getSatelliteFromCache())
                }
                is Response.Error -> {

                }
            }
        }
    }

    @Test
    fun `GetSatelliteUseCase should return loading and success from asset`() = runBlocking {
        val testId = 2
        val satelliteFromAsset = MockData.getSatelliteFromAsset()

        coEvery { mockRepository.getSatelliteFromAsset(testId) } returns satelliteFromAsset
        coEvery { mockRepository.insertSatelliteCache(satelliteFromAsset) }

        getSatelliteUseCase.invoke(testId).collect { result ->
            when (result) {
                is Response.Loading -> {
                    assertThat(result).isInstanceOf(Response.Loading::class.java)
                }
                is Response.Success -> {
                    assertThat(result).isInstanceOf(Response.Success::class.java)
                    assertThat(result.data).isEqualTo(MockData.getSatelliteFromAsset())
                }
                is Response.Error -> {

                }
            }
        }
    }

    @Test
    fun `GetSatelliteUseCase should return loading and error`() = runBlocking {
        val testId = 2

        coEvery { mockRepository.getSatelliteFromCache(testId) } throws Exception("Test Exception")

        getSatelliteUseCase.invoke(testId).collect { result ->
            when (result) {
                is Response.Loading -> {
                    assertThat(result).isInstanceOf(Response.Loading::class.java)
                }
                is Response.Success -> {

                }
                is Response.Error -> {
                    assertThat(result).isInstanceOf(Response.Error::class.java)
                    assertThat(result.errorMessage).isEqualTo("Test Exception")
                }
            }
        }
    }

}