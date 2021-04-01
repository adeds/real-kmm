//
//  CharactersListScreen.swift
//  iosApp
//
//  Created by Ade Dyas  on 01/04/21.
//

import SwiftUI
import shared

struct CountriesListScreen: View {
    @EnvironmentObject var viewModel: AppViewModel
    
    var body: some View {
        let charactersListState = CharactersListStateProvider(stateProvider : viewModel.stateProvider).getCountriesListState()
        let events = CharactersListEvents(events : viewModel.events)
        if charactersListState.isLoading {
            LoadingElement()
        } else {
            NavigationView {
                List {
                    if charactersListState.charactersListItems.count == 0 {
                        HStack(spacing: 0) {
                            Text("empty list")
                        }
                    } else {
//                        Section(header: CountriesListHeader()) {
                            ForEach (charactersListState.charactersListItems, id: \.name) { item in
//                                NavigationLink(destination: CountryDetailScreen(countryName: item.name)) {
                                    CharactersListRow(
                                        item: item,
                                        favorite: charactersListState.favoriteCharacters[item.name] != nil,
                                        onFavoriteIconClick: { events.selectFavorite(country: item.name) }
                                    )
//                                }
                            }
//                        }
                    }
                }
                .navigationBarTitleDisplayMode(.inline)
                .toolbar {
                    ToolbarItem(placement: .principal) {
                        Text("My Rick Morty Characters List").font(.headline).foregroundColor(.white)
                    }
//                    ToolbarItemGroup(placement: .bottomBar) {
//                        CountriesListBottomBar(
//                            selectedItem : countriesListState.selectedMenuItem,
//                            onItemClick: { menuItem in events.selectMenuItem(menuItem: menuItem) }
//                        )
//                    }
                }
            }
            .navigationBarColor(backgroundColor: "6200EE".toUIColor(), tintColor: .white)
            .toolbarColor(backgroundColor: "6200EE".toUIColor(), tintColor: .white)
        }
    }
    
}
