import SwiftUI
import FirebaseCore // Bu satırı ekle!

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        
        // Firebase'i burada başlatıyoruz.
        FirebaseApp.configure()
        return true
    }
}

@main
struct iOSApp: App {
    
    // AppDelegate'i SwiftUI yaşam döngüsüne entegre etmek için bu satırı ekle
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    
    var body: some Scene {
        WindowGroup {
            // ... KMP içeriğinin çağrıldığı yer
            ContentView()
        }
    }
}
