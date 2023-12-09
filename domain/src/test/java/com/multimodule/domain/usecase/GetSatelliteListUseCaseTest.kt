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
 * Created by bedirhansaricayir on 8.12.2023.
 */
class GetSatelliteListUseCaseTest {

    private lateinit var getSatelliteListUseCase: GetSatelliteListUseCase
    private lateinit var mockRepository: SatelliteGateRepository

    @Before
    fun setUp() {
        mockRepository = mockk()
        coEvery { mockRepository.getSatelliteList() } returns MockData.getSatelliteListData()
        getSatelliteListUseCase = GetSatelliteListUseCase(repository = mockRepository)
    }

    @Test
    fun `GetSatelliteListUseCase should be executed then loading and success`() = runBlocking {
        getSatelliteListUseCase.invoke().collect { result ->
            when(result) {
                is Response.Loading -> {
                    assertThat(result).isInstanceOf(Response.Loading::class.java)

                }
                is Response.Success -> {
                    assertThat(result).isInstanceOf(Response.Success::class.java)
                    assertThat(result.data).hasSize(3)
                    assertThat(result.data?.get(0)?.name).isEqualTo("satellite-1")
                }
                is Response.Error -> {

                }
            }
        }
    }

    @Test
    fun `GetSatelliteListUseCase should be executed then loading and error`() = runBlocking {
        coEvery { mockRepository.getSatelliteList() } throws Exception("Test Exception")

        getSatelliteListUseCase.invoke().collect { result ->
            when(result) {
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