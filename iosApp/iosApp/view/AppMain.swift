//
//  AppMain.swift
//  iosApp
//
//  Created by Ade Dyas  on 01/04/21.
//

import SwiftUI
import shared

@main
struct AppMain: App {
    @StateObject var vm = AppViewModel()
    var body: some Scene {
        WindowGroup {
            ContentView().environmentObject(vm)
        }
    }
}
