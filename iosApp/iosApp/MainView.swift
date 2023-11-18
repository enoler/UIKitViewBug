import SwiftUI
import shared

class MainPresentedView: ObservableObject {
    enum AvailableView {
        case help
    }
    
    @Published var currentView: AvailableView = .help
}

struct MainContentView: View {
    @EnvironmentObject var presentedView: MainPresentedView
    
    var body: some View {
        switch presentedView.currentView {
            case .help: HelpView()
        }
    }
}

struct MainComposeView: UIViewControllerRepresentable {
    let controller = MainViewController()

    @Binding var menuItems: [DrawerMenuItem]
    
    func makeUIViewController(context: Context) -> some UIViewController {
        controller.statusComposable(
            initialView: SwiftUIInUIView(content: MainContentView())
        )
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        controller.updateStatusComposable(menuItems: menuItems)
    }
}

struct MainView: View {
    @EnvironmentObject var presentedView: MainPresentedView

    let vm = MainViewModelHelper().get()
    
    @State var menuItems: [DrawerMenuItem] = []
    
    var body: some View {
        ZStack(alignment: .top) {
            Color(UIColor(named: "PrimaryColor")!)
                .ignoresSafeArea()
            MainComposeView(menuItems: $menuItems)
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
                .onAppear {
                    observeViewModel()
                    initialize()
                }
                .onDisappear {
                    removeObservers()
                }
        }
    }
        
    private func initialize() {
        vm.getMenuItems()
    }

    
    private func observeViewModel() {
        vm.menuItems.addObserver { items in
            menuItems = items as! [DrawerMenuItem]
        }
    }
    
    private func removeObservers() {
        vm.menuItems.removeObserver { _ in }
    }
}
