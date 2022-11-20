//
//  DataState.swift
//  iosApp
//
//  Created by Mitul Vaghamshi on 2022-11-19.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared

enum DataState {
    case loading
    case result([RocketLaunch])
    case error(String)
}
