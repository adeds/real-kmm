package id.adeds.androidapp.di

import id.adeds.androidapp.viewmodel.MainViewModel
import id.adeds.shared.data.remote.CharacterRemoteInterface
import id.adeds.shared.data.remote.CharacterRemoteInterfaceImpl
import id.adeds.shared.data.repository.CharacterRepository
import id.adeds.shared.data.repository.CharacterRepositoryImpl
import id.adeds.shared.domain.interactor.character.GetCharactersUseCase
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Injector {
    private const val END_POINT = "https://rickandmortyapi.com/"
    val baseModule = module {
        single { Dispatchers.Default }
    }

    val clientModule = module {
        single {
            HttpClient {
                install(JsonFeature) {
                    val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
                    serializer = KotlinxSerializer(json)
                }
            }
        }
    }
    val repositoryModule = module {
        single<CharacterRemoteInterface> { CharacterRemoteInterfaceImpl(END_POINT, get()) }
        single<CharacterRepository> { CharacterRepositoryImpl(get()) }
    }

    val useCaseModule = module {
        single { GetCharactersUseCase(get(), get()) }
    }

    val viewModelModule = module {
        viewModel { MainViewModel(get()) }
    }
}