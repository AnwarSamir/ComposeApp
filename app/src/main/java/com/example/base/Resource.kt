package com.example.base

/**
 * @author Anwar ElSayed
 * sealed class with success , failure or loading status
 * */
sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    // you can pass if the error network error or not and pass custom
    // error code to handle any custom exception with it as you see error body it Any
    // so you can pass any thing and cast it
    data class Failure(val isNetworkError: Boolean, val errorCode: ErrorCodes?, val errorBody: Any?) :
        Resource<Nothing>()

}


enum class ErrorCodes {
    EXCEPTION,
    HTTP,
    JSON_EXP
}