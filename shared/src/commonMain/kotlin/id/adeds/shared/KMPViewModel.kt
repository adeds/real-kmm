package id.adeds.shared

import kotlinx.coroutines.flow.StateFlow

class KMPViewModel {

    val stateFlow: StateFlow<AppState>
        get() = stateManager.mutableStateFlow

    internal val stateManager by lazy { StateManager() }

    internal val stateReducers by lazy { StateReducers(stateManager) }

    val events by lazy { Events(stateReducers) }

    internal val stateProvider by lazy { StateProvider(stateManager, events) }

}
