//
//  LaunchList.swift
//  iosApp
//
//  Created by Mitul Vaghamshi on 2022-11-19.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LaunchList: View {
    let state: DataState
    
    var body: some View {
        switch state {
        case .loading:
            ProgressView()
        case .error(let error):
            Text(error).multilineTextAlignment(.center)
        case .result(let launches):
            List(launches) { launch in
                NavigationLink(launch.missionName) {
                    LaunchDetail(rocketLaunch: launch)
                }.font(.headline)
            }
        }
    }
}

struct LaunchList_Previews: PreviewProvider {
    static var previews: some View {
        NavigationView {
            LaunchList(state: .result([SampleData().falcon1]))
        }
    }
}
