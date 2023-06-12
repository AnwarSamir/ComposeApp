package com.example.base

import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

open class BaseRepository {
    fun <T> safeApiCalls (replaceErrorMessage:Boolean = false,apiCall: suspend () -> T): Flow<Resource<T>> {
        return flow {
            try {
                emit(Resource.Success(apiCall.invoke()))
            } catch (throwable: Throwable) {
                // you can any error may happen with you here and pass with it to Resource as Failure
                when (throwable) {
                    is HttpException -> {
                        if (replaceErrorMessage)
                            emit(
                                Resource.Failure(
                                    false,
                                    ErrorCodes.HTTP,
                                    throwable.response()?.errorBody()
                                )
                            )
                        else
                            emit(
                                Resource.Failure(
                                    false,
                                    ErrorCodes.HTTP,
                                    androidx.compose.ui.R.string.default_error_message
                                )
                            )

                    }
                    is JsonSyntaxException -> {
                        emit(
                            Resource.Failure(
                                false,
                                ErrorCodes.JSON_EXP,
                                androidx.compose.ui.R.string.default_error_message
                            )
                        )
                    }

                    else -> {
                        emit(Resource.Failure(true, null, null))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}