//
//  ViewModel.swift
//  iosApp
//
//  Created by Mitul Vaghamshi on 2022-11-19.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared

@MainActor
class ViewModel: ObservableObject{
    @Published var state = DataState.loading
    @Published var isDev = true
    
    let sdk: SpaceXSDK
    
    init(sdk: SpaceXSDK) {
        self.sdk = sdk
        loadLaunches(forceReload: false, isDev: isDev)
    }
    
    func loadLaunches(forceReload: Bool, isDev: Bool) {
        self.state = .loading
        DispatchQueue.main.async { [weak self] in
            self?.sdk.getLaunches(forceReload: forceReload, isDev: isDev) { launches, error in
                if let launches = launches {
                    self?.state = .result(launches)
                } else {
                    self?.state = .error(error?.localizedDescription ?? "Error...")
                }
            }
        }
    }
}
