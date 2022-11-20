import Foundation
import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        NavigationView {
            LaunchList(state: viewModel.state)
                .navigationTitle("SpaceX Launches")
                .refreshable {
                    viewModel.loadLaunches(forceReload: true, isDev: viewModel.isDev)
                }
#if DEBUG
                .toolbar {
                    Toggle(isOn: $viewModel.isDev) { Text("Test Data") }
                }
#endif
        }
    }
}

extension RocketLaunch: Identifiable {}

struct ContentView_Previews: PreviewProvider {
    static let sdk = SpaceXSDK(databaseDriverFactory: DatabaseDriverFactory())
    static var previews: some View {
        ContentView(viewModel: .init(sdk: sdk))
    }
}
