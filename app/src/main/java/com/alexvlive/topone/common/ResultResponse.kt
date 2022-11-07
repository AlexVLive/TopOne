package com.alexvlive.topone.common

sealed class ResultResponse<out T>  {
    object Loading : ResultResponse<Nothing>()
    class Success<T>(val data: T) : ResultResponse<T>()
    class Error(val exception: Exception) : ResultResponse<Nothing>()
}

fun <T> ResultResponse<T>.takeSuccessData(): T? =
    if (this is ResultResponse.Success)
        this.data
    else
        null
