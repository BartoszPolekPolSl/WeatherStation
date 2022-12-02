package com.example.weatherstation.di

import android.content.SharedPreferences
import com.example.weatherstation.BuildConfig
import com.example.weatherstation.data.model.settings.Parameters
import com.example.weatherstation.data.model.settings.Settings
import com.example.weatherstation.data.rest.BASE_URL
import com.example.weatherstation.data.rest.WeatherApi
import com.example.weatherstation.data.util.Units
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(okHttpClient: OkHttpClient): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSettings(sharedPreferences: SharedPreferences): Settings {
        return Settings(
            Units.findBySymbol(
                sharedPreferences.getString(
                    Parameters.TEMPERATURE.tag,
                    Units.CELSIUS.symbol
                )!!
            )!!,
            Units.findBySymbol(
                sharedPreferences.getString(
                    Parameters.PRESSURE.tag,
                    Units.CELSIUS.symbol
                )!!
            )!!
        )
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                it.proceed(
                    it.request()
                        .newBuilder()
                        .url(
                            it.request().url().newBuilder()
                                .addQueryParameter("key", BuildConfig.API_KEY).build()
                        )
                        .build()
                )
            }.build()
    }
}