- ModaUrbanaApp

## 1. Caso elegido y alcance
-<ModaUrbana>Una aplicación de catálogo y e-commerce orientada a moda y estilo urbano.  
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
com.example.modaurbanaapp/
│
├── MainActivity.kt                 # Punto de entrada principal de la app (Scaffold + NavHost)
│
├── ui/                             # Capa de interfaz de usuario (Compose)
│   ├── navigation/
│   │   ├── AppNavGraph.kt          # Mapa de navegación (NavHostController + rutas)
│   │   └── Screen.kt               # Rutas centralizadas y tipadas (Login, Home, Profile, etc.)
│   │
│   ├── components/                 # Componentes visuales reutilizables
│   │   ├── BottomBar.kt            # Barra inferior de navegación (Inicio, Catálogo, Carrito, Perfil)
│   │   └── ProductCard.kt          # Tarjeta de producto con imagen, título y precio
│   │
│   ├── screens/                    # Pantallas principales de la aplicación
│   │   ├── LoginScreen.kt          # Pantalla de inicio de sesión (autenticación)
│   │   ├── RegisterScreen.kt       # Pantalla de registro de usuario
│   │   ├── HomeScreen.kt           # Pantalla inicial tras iniciar sesión
│   │   ├── CatalogScreen.kt        # Catálogo de productos (lista, filtros y orden)
│   │   ├── CartScreen.kt           # Pantalla del carrito de compras
│   │   └── ProfileScreen.kt        # Perfil del usuario (foto, cámara, galería)
│   │
│   ├── state/                      # Estados observables para cada vista (StateFlow)
│   │   ├── CatalogUiState.kt       # Estado del catálogo (productos, categorías, error)
│   │   ├── LoginUiState.kt         # Estado del login (usuario, carga, errores)
│   │   └── RegisterUiState.kt      # Estado del registro (validación, mensajes)
│   │
│   └── theme/                      # Configuración visual (paleta, tipografía, estilos)
│       ├── Color.kt                # Colores principales (Fondo crema, verde oscuro, blanco)
│       ├── Theme.kt                # Aplicación del tema global (Material 3)
│       └── Type.kt                 # Tipografías y jerarquías de texto
│
├── ViewModel/                      # Capa lógica y de negocio (MVVM)
│   ├── LoginViewModel.kt           # Controla el flujo de login y sesión de usuario
│   ├── RegisterViewModel.kt        # Maneja registro y validaciones del formulario
│   ├── CatalogViewModel.kt         # Controla productos, filtros y orden de catálogo
│   ├── CartViewModel.kt            # Lógica del carrito de compras (agregar, eliminar, total)
│   ├── ProfileViewModel.kt         # Gestiona foto de perfil, cámara y galería
│   └── Factories.kt                # Fábricas de ViewModel (inyección de dependencias)
│
├── data/                           # Fuente de datos (local + remoto)
│   ├── local/                      # Persistencia interna y preferencias
│   │   ├── SessionManager.kt       # Guarda el estado de sesión del usuario
│   │   └── AvatarManager.kt        # Guarda el URI de la imagen de perfil
│   │
│   └── remote/                     # Comunicación con API (estructura preparada)
│       ├── ApiService.kt           # Definición de endpoints (login, productos, etc.)
│       ├── RetrofitClient.kt       # Configuración base de Retrofit y Gson
│       ├── AuthInterceptor.kt      # Inserta token JWT en encabezado Authorization
│       └── dto/                    # Modelos de transferencia de datos (DTOs)
│           ├── AuthDtos.kt         # Clases para login/register (request y response)
│           └── ProductDto.kt       # Clase de producto remoto (API → modelo local)
│
├── model/                          # Modelos de dominio de la app
│   ├── Product.kt                  # Representa productos del catálogo (nombre, precio, imagen)
│   └── CartItem.kt                 # Representa un producto dentro del carrito
│
└── repository/                     # Repositorios de datos (acceso unificado)
    ├── ProductDataSource.kt        # Interfaz base con métodos de acceso (byCategory, featured)
    ├── ProductRepository.kt        # Repositorio principal del catálogo
    ├── LocalProductRepository.kt   # Implementación local (productos simulados)
    ├── ProductRepositoryImpl.kt    # Implementación futura con Retrofit (API real)
    └── CartRepository.kt           # Repositorio del carrito de compras (manejo local)

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
