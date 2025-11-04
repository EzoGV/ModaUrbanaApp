````{"id":"52914","variant":"standard","title":"README - ModaUrbanaApp"}
# ModaUrbanaApp

## 1. Caso elegido y alcance
-Una aplicación de catálogo y e-commerce orientada a moda y estilo urbano.  
- **Alcance EP3:** Diseño/UI en Jetpack Compose, validaciones de usuario, navegación estructurada, gestión de estado, persistencia local (token y avatar), integración con cámara/galería y consumo de API remota (autenticación, productos).

---

## 2. Requisitos y ejecución
- **Stack técnico:**
  - **Lenguaje:** Kotlin  
  - **Framework:** Android Jetpack Compose  
  - **Arquitectura:** MVVM  
  - **Networking:** Retrofit + OkHttp + Moshi  
  - **Persistencia:** SharedPreferences (para token y foto de perfil)  
  - **Navegación:** Navigation Compose  
  - **UI:** Material3  

- **Instalación:**
  ```bash
  git clone https://github.com/<usuario>/ModaUrbanaApp.git
  cd ModaUrbanaApp
  ```

- **Ejecución:**
  1. Abrir el proyecto en Android Studio (Giraffe o superior).  
  2. Ejecutar en un emulador o dispositivo físico (API 28+).  
  3. Verificar permisos de cámara y almacenamiento.  

---

## 3. Arquitectura y flujo
- **Estructura de carpetas:**
  ```
  ui/
    screens/
    components/
    navigation/
  data/
    local/
    remote/
    model/
    repository/
  ViewModel/
  ```
- **Gestión de estado:**  
  Cada pantalla cuenta con su propio `ViewModel` que expone un `StateFlow` (para estados `loading`, `success`, `error`). El flujo de datos va desde el *repository* → *ViewModel* → *UI Compose*.

- **Navegación:**  
  Implementada con `NavHost` y `Screen` sealed class.  
  Páginas principales: Login → Home → Catálogo → Perfil.  
  `BottomBar` administra navegación entre Home, Catálogo y Perfil.  

---

## 4. Funcionalidades
- Autenticación con API (login y validación del token con `AuthInterceptor`).
- Catálogo dinámico con productos remotos (`Retrofit + Moshi`).
- Carrito de compras con contador y persistencia temporal.
- Perfil de usuario con foto de perfil persistida localmente.
- Integración con cámara/galería (`FileProvider` + permisos).
- Persistencia de sesión con `SessionManager` (`SharedPreferences`).
- Arquitectura modular con `ViewModel` y `Factory`.

---

## 5. User Flows
**Flujo principal:**
1. Usuario abre la app → LoginScreen.  
2. Se valida usuario → se guarda token.  
3. Navega a HomeScreen → acceso a Catálogo o Perfil.  
4. Desde Catálogo puede añadir productos al carrito.  
5. Desde Perfil puede actualizar su foto con cámara o galería.  
6. El token y avatar quedan guardados localmente entre sesiones.
