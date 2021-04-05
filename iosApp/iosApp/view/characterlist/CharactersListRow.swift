//
//  CountriesListRow.swift
//  iosApp
//
//  Created by Ade Dyas  on 01/04/21.
//

import SwiftUI
import shared
import URLImage

struct CharactersListRow: View {
    var item : CharactersListItem
    var favorite : Bool
    var onFavoriteIconClick: () -> Void
    
    var body: some View {
        HStack {
            if let image = URL(string: item.image){
                URLImage(url: image) { image in
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .frame(width : 100, height: 100)
                }
            }
            VStack{
                Text(item.name).font(Font.subheadline).bold().frame(alignment: .leading)
                Text(item.gender).font(Font.subheadline).bold().frame(alignment: .leading)
            }.frame(maxWidth: .infinity)
            
            Image(systemName: favorite ? "star.fill" : "star").foregroundColor(favorite ? .purple : .gray)
                .highPriorityGesture(TapGesture().onEnded(onFavoriteIconClick))
                .frame(width: 70)
        }
    }
}

