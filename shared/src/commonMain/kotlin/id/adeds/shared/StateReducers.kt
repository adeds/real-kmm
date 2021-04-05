package id.adeds.shared

import id.adeds.shared.datalayer.Repository


class StateReducers (stateManager : StateManager, repo: Repository = Repository()) {

    internal val stateManager by lazy { stateManager }

    internal val dataRepository by lazy { repo }

}