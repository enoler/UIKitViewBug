import SwiftUI

extension Color {
    init(hex: UInt64, alpha: Double = 1) {
        let hexadecimal = UInt(hex)
        self.init(
            .sRGB,
            red: Double((hexadecimal >> 16) & 0xff) / 255,
            green: Double((hexadecimal >> 08) & 0xff) / 255,
            blue: Double((hexadecimal >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }
}

extension View {
    func getRootViewController() -> UIViewController {
        guard let screen = UIApplication.shared.connectedScenes.first as? UIWindowScene else {
            return .init()
        }

        guard let root = screen.windows.first?.rootViewController else {
            return .init()
        }
        
        return root
    }
}
