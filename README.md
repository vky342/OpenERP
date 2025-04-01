# 📱 OpenERP - Free & Open Source ERP for MSMEs

OpenERP is an **Android-native application** designed to empower **Micro, Small, and Medium Enterprises (MSMEs)** with an easy-to-use **Enterprise Resource Planning (ERP)** solution. This app is completely **free** and **open-source**, built for businesses that lack the budget or technical expertise for traditional PC-based ERP software.

With OpenERP, businesses can efficiently **manage sales, purchases, accounting, and more**—all from their smartphones or tablets. 🚀

---

## 📌 Features
✅ **Offline-First**: Works seamlessly without an internet connection using Room Database.  
✅ **MVVM Architecture**: Ensures clean and maintainable code.  
✅ **Jetpack Compose UI**: Modern UI with a smooth user experience.  
✅ **Navigation with NavGraph**: Streamlined user flow.  
✅ **Modular Codebase**: Separation of concerns with Repository & ViewModel pattern.  
✅ **Completely Free & Open Source**: No hidden charges—forever free for MSMEs!  

---

## 🛠️ Tech Stack
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room Library (SQLite-based local storage)
- **Navigation**: Jetpack Navigation Component
- **Dependency Injection**: Dagger Hilt 2

---

## 📂 App Architecture

The app follows **MVVM (Model-View-ViewModel)** architecture:
```
UI  <-  ViewModel  <-  Repository  <-  Database
```

### **1. UI Layer (Jetpack Compose)**
- Uses **StateFlow** or **LiveData** for reactive UI updates.
- Handles UI logic and user interactions.

### **2. ViewModel Layer**
- Fetches and transforms data for the UI.
- Uses **Use Cases (optional)** for better separation of concerns.

### **3. Repository Layer**
- Acts as a **single source of truth** for data.
- Fetches data from **Room Database** or future API integrations.

### **4. Database Layer (Room)**
- Uses **DAO interfaces** to manage database queries.
- Supports **coroutines & Flow** for reactive data updates.

### **5. Dependency Injection (Dagger Hilt 2)**
Hilt is used for dependency injection to ensure modular and testable code.

Example Hilt module for Room Database:
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "openerp.db").build()
    }

    @Provides
    fun provideDao(db: AppDatabase): AccountDao {
        return db.accountDao()
    }
}
```

---

## 📅 Roadmap
🚀 **Phase 1: Database & Architecture**
- [x] Design Database Schema 📌
- [x] Implement Room Database 🏗️
- [x] Create Repository & Interfaces ⚙️
- [x] Implement ViewModel & UI Integration 🖥️

🚀 **Phase 2: UI & Navigation**
- [x] Design and Implement UI Screens 🎨
- [x] Implement Navigation (NavGraph & NavHost) 🔄
- [x] Enhance UI with animations and styling ✨

🚀 **Phase 3: Business Logic & Optimization**
- [x] Set up Dependency Injection for better modularity 🏗️
- [ ] Implement business logic in Repositories 📊
- [ ] Optimize performance & fix bugs 🛠️

🚀 **Phase 4: Beta Testing & Release**
- [ ] Internal Testing 🧪
- [ ] Public Beta Release 📱
- [ ] Official Release 🚀

---

## 🤝 Contribution & Support
We **welcome all contributors** to improve OpenERP! If you'd like to contribute:
1. **Fork the repository**
2. **Make changes & submit a Pull Request (PR)**
3. **Discuss & collaborate** with the community

📧 **For more details, contact:** [kunalsahu071@gmail.com](mailto:kunalsahu071@gmail.com)

---

## 📜 License
OpenERP is licensed under the **MIT License**, allowing anyone to use and modify it freely. 

🌟 **Star this repo** if you find it useful! 🚀




