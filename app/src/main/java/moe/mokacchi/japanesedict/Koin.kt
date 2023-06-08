package moe.mokacchi.japanesedict

import moe.mokacchi.japanesedict.data.apis.JotobaApi
import moe.mokacchi.japanesedict.ui.screens.camera.CameraViewModel
import moe.mokacchi.japanesedict.ui.screens.search.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    viewModel { SearchViewModel(get()) }
    viewModel { CameraViewModel() }
}

val networkModule = module {
    single { provideJotobaApi() }
}

fun provideJotobaApi(): JotobaApi {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    return Retrofit.Builder()
        .baseUrl("https://jotoba.de/api/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JotobaApi::class.java)
}