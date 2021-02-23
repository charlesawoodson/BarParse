package com.charlesawoodson.barparse.temp.api

import com.charlesawoodson.barparse.temp.repos.MusixMatchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object NetworkModule {

    private val BASE_URL = "https://api.musixmatch.com/ws/1.1/"
    private val API_KEY_NAME = "apikey"
    private val API_KEY = "93b4e60cba02807b2774d77f6bc5b23c"

    @Provides
    fun providesAuthInterceptor() = Interceptor { chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter(API_KEY_NAME, API_KEY)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    @Provides
    fun providesHttpClient(authInterceptor: Interceptor): OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .build()

    @Provides
    fun providesMusixMatchApi(client: OkHttpClient): MusixMatchApi =
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MusixMatchApi::class.java)

    @Provides
    fun provideMusixMatchRepository(api: MusixMatchApi) = MusixMatchRepository(api)
}