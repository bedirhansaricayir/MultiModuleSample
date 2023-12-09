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
class GetPositionUseCaseTest {

    private lateinit var getPositionUseCase: GetPositionUseCase
    private lateinit var mockRepository: SatelliteGateRepository
    @Before
    fun setUp() {
        mockRepository = mockk()
        val testId = 2
        coEvery { mockRepository.getPosition(testId) } returns MockData.getPositionData()
        getPositionUseCase = GetPositionUseCase(repository = mockRepository)
    }

    @Test
    fun `GetPositionUseCase should return loading and success`() = runBlocking {
        val testId = 2
        getPositionUseCase.invoke(testId).collect { result ->
            when (result) {
                is Response.Loading -> {
                    assertThat(result).isInstanceOf(Response.Loading::class.java)
                }
                is Response.Success -> {
                    assertThat(result).isInstanceOf(Response.Success::class.java)
                    assertThat(result.data).isNotNull()
                    assertThat(result.data).isEqualTo(MockData.getPositionData())
                }
                is Response.Error -> {

                }
            }
        }
    }

    @Test
    fun `GetPositionUseCase should return loading and error`() = runBlocking {
        val testId = 2
        coEvery { mockRepository.getPosition(testId) } throws Exception("Test Exception")

        getPositionUseCase.invoke(testId).collect { result ->
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