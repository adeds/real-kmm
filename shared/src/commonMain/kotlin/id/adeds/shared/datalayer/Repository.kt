package id.adeds.shared.datalayer

import com.russhwolf.settings.Settings
import id.adeds.shared.datalayer.sources.local.MySettings
import id.adeds.shared.datalayer.sources.remote.ApiClient
import id.adeds.shared.datalayer.sources.remote.characters.CharactersListData
import kotlin.native.concurrent.ThreadLocal


class Repository (private val settings : Settings = Settings()) {

    @ThreadLocal
    companion object Data {
        var charactersList: List<CharactersListData> = emptyList()
    }

    internal val webservices by lazy { ApiClient() }
    val localSettings by lazy { MySettings(settings) }

}