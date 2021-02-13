package id.adeds.androidapp.di

import id.adeds.androidapp.viewmodel.MainViewModel
import id.adeds.shared.data.cache.`interface`.CharacterCacheInterface
import id.adeds.shared.data.cache.`interface`.CharacterCacheInterfaceImpl
import id.adeds.shared.data.cache.DatabaseDriverFactory
import id.adeds.shared.data.remote.CharacterRemoteInterface
import id.adeds.shared.data.remote.CharacterRemoteInterfaceImpl
import id.adeds.shared.data.repository.CharacterRepository
import id.adeds.shared.data.repository.CharacterRepositoryImpl
import id.adeds.shared.domain.interactor.character.GetCharactersUseCase
import id.adeds.shared.domain.interactor.character.UpdateCharactersUseCase
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
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
        single<CharacterCacheInterface> { CharacterCacheInterfaceImpl(DatabaseDriverFactory(androidContext())) }
        single<CharacterRemoteInterface> { CharacterRemoteInterfaceImpl(END_POINT, get()) }
        single<CharacterRepository> { CharacterRepositoryImpl(get(), get()) }
    }

    val useCaseModule = module {
        single { GetCharactersUseCase(get(), get()) }
        single { UpdateCharactersUseCase(get(), get()) }
    }

    val viewModelModule = module {
        viewModel { MainViewModel(get(), get()) }
    }
}