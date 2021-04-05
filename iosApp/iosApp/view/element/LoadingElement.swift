//
//  LoadingElement.swift
//  iosApp
//
//  Created by Ade Dyas  on 01/04/21.
//

import SwiftUI

struct LoadingElement: View {
    var body: some View {
        VStack {
            Spacer()
            Text("loading...")
            Spacer().frame(height: 30)
            ProgressView().progressViewStyle(CircularProgressViewStyle())
            Spacer()
        }
    }
}
