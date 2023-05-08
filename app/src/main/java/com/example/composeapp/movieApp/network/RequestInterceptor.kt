

package com.example.composeapp.movieApp.network

import okhttp3.Interceptor
import okhttp3.Response

internal class RequestInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val originalUrl = originalRequest.url
    val url = originalUrl.newBuilder()
      .addQueryParameter("api_key", "bec0d511ab324200f07a552100e8f6e3")
      .build()

    val requestBuilder = originalRequest.newBuilder().url(url)
    val request = requestBuilder.build()
    return chain.proceed(request)
  }
}
