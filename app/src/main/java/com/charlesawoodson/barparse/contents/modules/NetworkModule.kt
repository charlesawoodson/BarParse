package com.charlesawoodson.barparse.contents.modules

import android.content.Context
import androidx.room.Room
import com.charlesawoodson.barparse.R
import com.charlesawoodson.barparse.contents.api.MusixMatchApi
import com.charlesawoodson.barparse.contents.databases.MusixMatchDatabase
import com.charlesawoodson.barparse.contents.repositories.MusixMatchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.musixmatch.com/ws/1.1/"
    private const val API_KEY_NAME = "apikey"
    private const val API_KEY = "93b4e60cba02807b2774d77f6bc5b23c"

    @Provides
    @Singleton
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
    @Singleton
    fun providesHttpClient(authInterceptor: Interceptor): OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .build()

    @Provides
    @Singleton
    fun providesMusixMatchApi(client: OkHttpClient): MusixMatchApi =
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MusixMatchApi::class.java)

    @Provides
    @Singleton
    fun providesMusixMatchDatabase(@ApplicationContext context: Context): MusixMatchDatabase =
        Room.databaseBuilder(
            context,
            MusixMatchDatabase::class.java,
            context.getString(R.string.app_database_name)
        ).build()

    @Provides
    @Singleton
    fun provideMusixMatchRepository(api: MusixMatchApi, db: MusixMatchDatabase) =
        MusixMatchRepository(api, db)
}