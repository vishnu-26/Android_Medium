package com.example.api

import com.example.api.services.ConduitApi
import com.example.api.services.ConduitAuthAPI

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object ConduitClient {

    var authToken :String? = null

    val authInterceptor = object:Interceptor{

        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            var request = chain.request()
            authToken.let{
                request = request.newBuilder()
                    .header("Authorization","Token ${it}")
                    .build()


            }
            return chain.proceed(request)
        }

    }

    val okHttpClient = OkHttpClient.Builder()
        .readTimeout(60,TimeUnit.SECONDS)
        .connectTimeout(60,TimeUnit.SECONDS)


    val retrofit = Retrofit.Builder()
        .baseUrl("https://conduit.productionready.io/api/")
        .addConverterFactory(MoshiConverterFactory.create())



    val public_api = retrofit
        .client(okHttpClient.build())
        .build()
        .create(ConduitApi::class.java)

    val auth_api = retrofit
        .client(okHttpClient.addInterceptor(authInterceptor).build())
        .build()
        .create(ConduitAuthAPI::class.java)
}


