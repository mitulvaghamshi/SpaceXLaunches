//
//  RocketLaunchRow.swift
//  iosApp
//
//  Created by Mitul Vaghamshi on 2022-11-14.
//  Copyright © 2022 Mitul Vaghamshi. All rights reserved.
//

import SwiftUI
import shared

struct LaunchDetail: View {
    var rocketLaunch: RocketLaunch
    
    var body: some View {
        List {
            Group {
                Label(rocketLaunch.rocket.name, systemImage: "tag.fill")
                Label(rocketLaunch.rocket.type, systemImage: "info.circle.fill")
                if rocketLaunch.launchSuccess?.boolValue == true {
                    Label("Successful", systemImage: "checkmark.seal.fill")
                        .foregroundColor(.green)
                } else {
                    Label("Unsuccessful", systemImage: "xmark.seal.fill")
                        .foregroundColor(.red)
                }
                Label(rocketLaunch.launchDateUTC.toDate(), systemImage: "calendar.badge.clock")
            }
            .font(.headline)
            if let details = rocketLaunch.details {
                Section(header: Text("Discussion")) {
                    Text(details)
                }
            }
        }
        .navigationTitle(rocketLaunch.missionName)
        .toolbar {
            if let patchUrl = rocketLaunch.links.missionPatchUrl {
                Link("Patch", destination: URL(string: patchUrl)!)
            }
            if let articleUrl = rocketLaunch.links.articleUrl {
                Link("Article", destination: URL(string: articleUrl)!)
            }
        }
    }
}

extension String {
    func toDate() -> String {
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        let date = formatter.date(from: self)
        return date?.formatted(date: .abbreviated, time: .shortened) ?? self
    }
}

struct RocketLaunchRow_Previews: PreviewProvider {
    static var previews: some View {
        NavigationView {
            LaunchDetail(rocketLaunch: SampleData().falcon1)
        }
    }
}
