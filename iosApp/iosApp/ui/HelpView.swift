import SwiftUI
import shared

struct HelpComposeView: UIViewControllerRepresentable {
    let controller = HelpViewController()
    
    @Binding var sections: [ListItem]
    
    let onClickSection: (_ section: ListItem) -> Void
    
    func makeUIViewController(context: Context) -> some UIViewController {
        controller.statusComposable(onClickSection: onClickSection)
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        controller.updateStatusComposable(sections: sections)
    }
}

struct HelpView: View {
    private let vm = HelpViewModelHelper().get()

    @State var sections: [ListItem] = []

    var body: some View {
        VStack {
            Text("Help")
            HelpComposeView(
                sections: $sections,
                onClickSection: onSectionClicked
            )
            .ignoresSafeArea(.keyboard)
            .edgesIgnoringSafeArea(.all)
            .border(Color.blue, width: 2)
            .onAppear {
                observeViewModel()
                vm.fetchData()
            }
            .onDisappear {
                removeObservers()
            }
        }
    }
    
    private func navigateToContact() {
        print("Navigate to Contact")
    }
    
    private func observeViewModel() {
        vm.sections.addObserver { sectionsList in
            sections = sectionsList as! [ListItem]
        }
    }
    
    private func onSectionClicked(listItem: ListItem) {
        switch (listItem.code) {
        case MenuItem.helpContact.code: navigateToContact()
        default: print("Not possible case")
        }
    }
    
    private func removeObservers() {
        vm.sections.removeObserver { _ in }
    }
}
