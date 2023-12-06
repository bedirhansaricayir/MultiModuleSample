package com.multimodule.domain.usecase


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */
sealed interface Response<out T> {
    class Success<T>(val data: T?) : Response<T>
    class Error<T>(val errorMessage: String) : Response<T>
    object Loading : Response<Nothing>
}