package moe.mokacchi.japanesedict

import moe.mokacchi.japanesedict.ui.screens.camera.CameraViewModel
import moe.mokacchi.japanesedict.ui.screens.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SearchViewModel() }
    viewModel { CameraViewModel() }
}