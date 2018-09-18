package com.devjcastro.androidtestmovies.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieApiFactory {

    init {
        initRetrofit()
    }

    companion object {

        var BASE_URL = "https://api.themoviedb.org/3/"
        private var retrofit: Retrofit.Builder? = null

        fun build(): MovieService {
            return initRetrofit()
                    ?.build()?.create<MovieService>(MovieService::class.java)!!
        }

        private fun initRetrofit() : Retrofit.Builder? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()

                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BASIC
                val client = OkHttpClient.Builder()
                        .addInterceptor(logging)
                        .build()

                retrofit?.client(client)
                        ?.baseUrl(BASE_URL)
                        ?.addConverterFactory(GsonConverterFactory.create())
                        ?.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            }

            return retrofit
        }


        /*private fun initRetrofitWithToken(): Retrofit.Builder? {
            if (retrofitWithToken == null) {
                retrofitWithToken = Retrofit.Builder()


                val access_token = Prefs.getString(PreferencesMethods.KeyPreferences.ACCESS_TOKEN, null)
                val okhttpclient = OkHttpClient().newBuilder()
                okhttpclient.addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                            .addHeader("token", access_token)
                            .build()
                    chain.proceed(newRequest)
                }.build()

                okhttpclient.readTimeout(30, TimeUnit.SECONDS)
                okhttpclient.connectTimeout(60, TimeUnit.SECONDS)

                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                okhttpclient.addInterceptor(interceptor)





                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BASIC
                val client = OkHttpClient.Builder()
                        .addInterceptor(logging)
                        .build()

                retrofitWithToken?.client(client)
                        ?.baseUrl(BASE_URL)
                        ?.client(okhttpclient.build())
                        ?.addConverterFactory(GsonConverterFactory.create())
                        ?.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            }
            return retrofitWithToken
        }*/

    }

}