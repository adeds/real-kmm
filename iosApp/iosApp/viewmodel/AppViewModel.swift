//
//  AppViewModel.swift
//  iosApp
//
//  Created by Ade Dyas  on 01/04/21.
//

import Foundation
import shared

class AppViewModel: ObservableObject {
    private let coreModel : KMPViewModel = KMPViewModel()
    var events : Events {
        return self.coreModel.events
    }
    var stateProvider : StateProvider {
        return self.appState.getStateProvider(model: self.coreModel)
    }
    @Published var appState : AppState
    
    init() {
        // "getDefaultAppState" and "onChange" are iOS-only KMPViewModel's extension functions, defined in shared/iosMain
        self.appState = coreModel.getDefaultAppState()
        coreModel.onChange { newState in
            self.appState = newState
            NSLog("Real-KMM: recomposition Index: "+String(newState.recompositionIndex))
        }
    }

}
