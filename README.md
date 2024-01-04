# Título: BiblioControl - Automatización de la Gestión de Bibliotecas
## Introducción
La aplicación BiblioControl se ha creado con el propósito de simplificar y automatizar la gestión de bibliotecas, llegando incluso a suplir la necesidad de un bibliotecario. Esta aplicación ofrece varias funcionalidades esenciales que permiten a los administradores y usuarios de la biblioteca llevar a cabo sus tareas de manera eficiente.

<p align="center">
<img width="654" alt="Esquema Inicio" src="https://github.com/ac-14/TrabajoJava/assets/119895282/a47e674a-dcc3-441a-beef-1ae9a2af8f91">
</p>

## Funcionalidades Principales
Inicio de Sesión y Administración de Usuarios: La aplicación presenta una ventana principal con tres opciones: Iniciar sesión, crear usuario y recuperar acceso en caso de olvido de la contraseña. Para crear un usuario se necesitará un DNI y una contraseña. Al iniciar sesión, se distinguen dos tipos de usuarios: el administrador y los usuarios de la biblioteca.
### Administador
El administrador tiene acceso a un conjunto de herramientas para gestionar la biblioteca:<br />
•	Gestión de Libros: Puede añadir, eliminar y reubicar libros en la biblioteca.<br />
•	Configuración: Accede a una pestaña de configuración para ajustar parámetros.<br />
•	Gestión de Usuarios: Puede bloquear el acceso de ciertos usuarios por razones específicas, cambiar contraseñas, etc.<br />
•	Gestión de Peticiones: Visualiza las solicitudes de libros de los usuarios, permitiendo la compra y adición posterior de los mismos.<br />
<p align="center">
<img width="215" alt="Menu_Administrador" src="https://github.com/ac-14/TrabajoJava/assets/119895282/014aefd7-13d8-4e73-9222-ca43875786d6">
</p>

<p align="center">
<img width="246" alt="Busqueda_Usuario" src="https://github.com/ac-14/TrabajoJava/assets/119895282/7cf253fb-96a8-47d7-bb82-f70fec184be9"><img width="197" alt="Resultado_Usuario" src="https://github.com/ac-14/TrabajoJava/assets/119895282/3db13d71-ab07-4f87-8794-23097e2803b9">
</p>

### Usuario
Los usuarios de la biblioteca, al iniciar sesión con su DNI y contraseña, pueden realizar las siguientes acciones:<br />
•	Comprobar Disponibilidad: Verificar la disponibilidad de libros en la biblioteca.<br />
•	Reservar Libros: Reservar libros que estén disponibles.<br />
•	Solicitar Libros: Pedir libros que no estén en la biblioteca.<br />
•	Recuperar Contraseña: En caso de olvidar la contraseña, le llegará la solicitud al administrador para reestablecer el acceso al usuario.<br />
•	Recomendación de Libro: Si el usuario quiere disfrutar un libro aleatorio, podrá el programa le sugerirá uno.<br />

<p align="center">
<img width="220" alt="Menu_Usuario" src="https://github.com/ac-14/TrabajoJava/assets/119895282/5e9563a5-5b8e-430d-917b-226ad9cf6636">
</p>

### Conexión con base de datos
Además, la aplicación se conecta a una base de datos de libros, permitiendo la búsqueda de información a través del ISBN y la descarga automática de datos como el título, la portada y el género.

<p align="center">
<img width="190" alt="Busqueda_Libro" src="https://github.com/ac-14/TrabajoJava/assets/119895282/386e0d7c-b6c7-4da6-8769-9a0bca430854"><img width="153" alt="Resultado_Libro" src="https://github.com/ac-14/TrabajoJava/assets/119895282/c5510627-51e3-4615-a5dc-0a956fe925a1">
</p>

### Funcionalidad adicional: Control de ruido
Además, nuestra aplicación incluye una función que, utilizando el micrófono del ordenador la aplicación mide los niveles de ruido. Si los decibelios superan un umbral establecido por el administrador, la aplicación emitirá un mensaje de advertencia en voz alta, solicitando silencio en la biblioteca. Además, el administrador recibirá una notificación si se ha excedido el límite de ruido.
<p align="center">
<img width="217" alt="imagen" src="https://github.com/ac-14/TrabajoJava/assets/119895282/65600a45-5800-4690-b2cf-a19cb9fd6c06">
</p>

### Diagrama UML
<p align="center">
<img width="882" alt="imagen" src="https://github.com/ac-14/TrabajoJava/assets/119895282/76e9251f-ae99-4fd7-aa04-c55cbe996f9f">
</p>

### Práctica 2
Hemos agregado las clases principales del programa. Hemos hecho los menus de todas las clases y agregado algunas funcionalidades. A continuación agregamos imagenes del funcionamiento.

**Menú Usuario**
<p align="center">
<img width="198" alt="Captura de pantalla 2023-10-30 a las 9 18 36" src="https://github.com/ac-14/TrabajoJava/assets/119895282/ac78a651-ca01-4400-82bb-62f0cb272eba"><img width="230" alt="Captura de pantalla 2023-10-30 a las 9 18 46" src="https://github.com/ac-14/TrabajoJava/assets/119895282/1c89d0a6-d28f-4339-b607-3d43889099aa">
</p>

**Menú Admin**
<p align="center">
<img width="214" alt="Captura de pantalla 2023-10-30 a las 9 23 39" src="https://github.com/ac-14/TrabajoJava/assets/119895282/594892ce-a126-47bd-adf9-eb9a903ae613"><img width="198" alt="Captura de pantalla 2023-10-30 a las 9 23 50" src="https://github.com/ac-14/TrabajoJava/assets/119895282/ee21356b-d572-48e2-befb-17583cf0ea1f">
</p>

**Reserva libro**
<p align="center">
<img width="391" alt="Captura de pantalla 2023-10-30 a las 9 22 35" src="https://github.com/ac-14/TrabajoJava/assets/119895282/3718993d-c7e5-488a-977d-4268339f0435">
</p>

**Añadir libro**
<p align="center">
<img width="234" alt="Captura de pantalla 2023-10-30 a las 9 30 26" src="https://github.com/ac-14/TrabajoJava/assets/119895282/f27dd1a9-71e4-4a2d-b877-0113266c4708"><img width="386" alt="Captura de pantalla 2023-10-30 a las 9 32 02" src="https://github.com/ac-14/TrabajoJava/assets/119895282/1cff6f67-42b7-4946-a786-9c044c2ffba4">

### Práctica 3
Hemos agregado las funciones de Sonido y Gestión de peticiones. Además hemos creado 3 interfaces: Menú inicial, Menú de administrador y el Menú de usuario

Menús 
<p align="center">
<img width="321" alt="Captura de pantalla 2023-11-09 a las 11 10 52" src="https://github.com/ac-14/TrabajoJava/assets/119895282/eb071fbe-d54f-43d5-a9e2-520723f02716"><img width="323" alt="Captura de pantalla 2023-11-09 a las 11 12 10" src="https://github.com/ac-14/TrabajoJava/assets/119895282/dba8929f-c758-4d95-a2f0-e971e81e7379"><img width="320" alt="Captura de pantalla 2023-11-09 a las 11 11 55" src="https://github.com/ac-14/TrabajoJava/assets/119895282/8cf59ab9-e508-4773-9b5d-5a001d41472c">
</p>

**Función Peticiones**
<p align="center">
<img width="558" alt="Captura de pantalla 2023-11-09 a las 12 05 41" src="https://github.com/ac-14/TrabajoJava/assets/119895282/b101e54c-0bd0-41f3-bca3-c97732cec004"><img width="656" alt="Captura de pantalla 2023-11-09 a las 12 05 58" src="https://github.com/ac-14/TrabajoJava/assets/119895282/cec8caec-d69d-46ec-851c-60c4db97be40">
</p>

**Medición de Sonido**
<p align="center">
<img width="490" alt="Captura de pantalla 2023-11-10 a las 11 48 33" src="https://github.com/ac-14/TrabajoJava/assets/119895282/3b341846-9258-41b4-9aef-f4e6e31fe1ae">
</p>

### Práctica 4
Hemos creado las interfaces para cada método

**Funciones de administrador**
<p align="center">
<img width="462" alt="Captura de pantalla 2023-11-19 a las 16 01 37" src="https://github.com/ac-14/TrabajoJava/assets/119895282/3f1ddae5-3e1f-4c6e-9338-5349349fec94"><img width="512" alt="Captura de pantalla 2023-11-19 a las 15 59 19" src="https://github.com/ac-14/TrabajoJava/assets/119895282/3c098e07-a268-40ce-b276-60f4710d2328"><img width="462" alt="Captura de pantalla 2023-11-19 a las 15 58 59" src="https://github.com/ac-14/TrabajoJava/assets/119895282/65241b66-2860-4d5b-a4cc-98d5e0ad4572"><img width="462" alt="Captura de pantalla 2023-11-19 a las 15 58 53" src="https://github.com/ac-14/TrabajoJava/assets/119895282/94a5d375-3373-4e94-86c5-8492d11c0420"><img width="462" alt="Captura de pantalla 2023-11-19 a las 15 58 44" src="https://github.com/ac-14/TrabajoJava/assets/119895282/cf7ace64-f05a-4f39-8a5f-beff2fdd4b73"><img width="462" alt="Captura de pantalla 2023-11-19 a las 15 58 29" src="https://github.com/ac-14/TrabajoJava/assets/119895282/9729c9ef-87e6-43f3-84db-971e5ffeee45">
</p>

**Funciones de Usuario**
<p align="center">
<img width="412" alt="Captura de pantalla 2023-11-19 a las 16 02 38" src="https://github.com/ac-14/TrabajoJava/assets/119895282/16ba3010-733b-4436-a397-ad69622e05c9"><img width="412" alt="Captura de pantalla 2023-11-19 a las 16 02 27" src="https://github.com/ac-14/TrabajoJava/assets/119895282/639983b1-7542-48a5-bae9-f5d7f56dd0fb"><img width="512" alt="Captura de pantalla 2023-11-19 a las 16 02 23" src="https://github.com/ac-14/TrabajoJava/assets/119895282/7005bb8a-95f0-4ac6-bfe9-bee223a3747e"><img width="462" alt="Captura de pantalla 2023-11-19 a las 16 02 11" src="https://github.com/ac-14/TrabajoJava/assets/119895282/1d3a2e85-09c9-4c98-b125-d22c22cffc73">
</p>

Hemos implementado el patrón de diseño **"Singleton"**, que nos sirve para que una clase tenga una única instancia, en este caso **Admin**
```Java
private static Admin instance;
/**
     * Metodo para obtener la instancia de Admin (Singleton)
     * @return devuelve la instancia de Admin
     */
    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin("admin", "adminpassword");
        }
        return instance;
    }
```
### Práctica 5
Hemos agregado la función de conexión con internet, que era lo último que nos quedaba.

**Conexión a la API de Google Books**: Para ello hemos utilizado una biblioteca externa, que nos permite poder buscar libros a través del titulo por internet:
```Java
import com.google.gson.*;
```
Mostramos un ejemplo del funcionamiento:
<p align="center">
<img width="499" alt="Captura de pantalla 2023-11-29 a las 19 54 59" src="https://github.com/ac-14/TrabajoJava/assets/119895282/15f0e12c-31c9-42fa-b736-2ed31b4a506d">
</p>

Además, hemos agregado una Interfaz Gráfica de Configuración para que el administrador pueda modificar algunos parámetros:
<p align="center">
<img width="432" alt="Captura de pantalla 2023-12-01 a las 10 26 15" src="https://github.com/ac-14/TrabajoJava/assets/119895282/fa878e60-4448-45d4-afe2-d2217f2c3b57">
</p>

También se ha mejorado el aspecto del menú inicial: 
<p align="center">
<img width="362" alt="Captura de pantalla 2023-12-01 a las 10 27 25" src="https://github.com/ac-14/TrabajoJava/assets/119895282/25ef5100-cd4a-421c-be23-8206a10c45fc">
</p>

