package com.nadhifhayazee.data.retrotfit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    val baseUrl = "https://pokeapi.co/api/v2/"

    @Provides
    fun providesHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()

    }

    @Provides
    fun provideRetrofitBuilder(
        okHttpClient: OkHttpClient
    ): Retrofit.Builder {
        return Retrofit.Builder().client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl)
    }

    @Provides
    fun provideApiClient(
        retrofitBuilder: Retrofit.Builder
    ): ApiClient {
        return retrofitBuilder.build().create(ApiClient::class.java)
    }
}