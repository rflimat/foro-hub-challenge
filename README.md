# Foro Hub Challenge

Este proyecto corresponde a un desafío de programación backend desarrollado en Java y Spring Boot, cuyo objetivo es la construcción de una API REST para Foro Hub, aplicando buenas prácticas, persistencia de datos, validaciones y seguridad.

## Estructura del proyecto

```
foro-hub-challenge/
│
├── .mvn/
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── rflimat
│   │   │           └── foro_hub_challenge
│   │   │               ├── common
│   │   │               │   ├── exception
│   │   │               │   │   ├── GestorDeErrores.java
│   │   │               │   │   └── ValidacionException.java
│   │   │               │   └── security
│   │   │               │       ├── DatosTokenJWT.java
│   │   │               │       ├── SecurityConfigurations.java
│   │   │               │       ├── SecurityFilter.java
│   │   │               │       └── TokenService.java
│   │   │               ├── controller
│   │   │               │   ├── AutenticacionController.java
│   │   │               │   ├── CursoController.java
│   │   │               │   ├── TopicoController.java
│   │   │               │   └── UsuarioController.java
│   │   │               ├── dto
│   │   │               │   ├── curso
│   │   │               │   │   ├── Categoria.java
│   │   │               │   │   ├── DatosActualizacionCurso.java
│   │   │               │   │   ├── DatosDetalleCurso.java
│   │   │               │   │   ├── DatosListaCurso.java
│   │   │               │   │   └── DatosRegistroCurso.java
│   │   │               │   ├── topico
│   │   │               │   │   ├── DatosActualizacionTopico.java
│   │   │               │   │   ├── DatosDetalleTopico.java
│   │   │               │   │   ├── DatosListaTopico.java
│   │   │               │   │   ├── DatosRegistroTopico.java
│   │   │               │   │   └── Status.java
│   │   │               │   └── usuario
│   │   │               │       ├── DatosActualizacionUsuario.java
│   │   │               │       ├── DatosAutenticacion.java
│   │   │               │       ├── DatosDetalleUsuario.java
│   │   │               │       ├── DatosListaUsuario.java
│   │   │               │       └── DatosRegistroUsuario.java
│   │   │               ├── model
│   │   │               │   ├── Curso.java
│   │   │               │   ├── Perfil.java
│   │   │               │   ├── Topico.java
│   │   │               │   └── Usuario.java
│   │   │               ├── repository
│   │   │               │   ├── CursoRepository.java
│   │   │               │   ├── PerfilRepository.java
│   │   │               │   ├── TopicoRepository.java
│   │   │               │   └── UsuarioRepository.java
│   │   │               ├── service
│   │   │               │   ├── AutenticacionService.java
│   │   │               │   ├── CursoService.java
│   │   │               │   ├── TopicoService.java
│   │   │               │   └── UsuarioService.java
│   │   │               └── ForoHubChallengeApplication.java
│   │   └── resources
│   │       ├── db
│   │       │   └── migration
│   │       │       ├── V1__create-table-perfiles.sql
│   │       │       ├── V2__create-table-usuarios.sql
│   │       │       ├── V3__create-table-cursos.sql
│   │       │       ├── V4__create-table-topicos.sql
│   │       │       ├── V5__create-table-usuarios_perfiles.sql
│   │       │       └── V6__insert-initial-data-perfiles.sql
│   │       ├── static
│   │       ├── templates
│   │       └── application.properties
│   └── test/
├── target/
├── .gitattributes
├── .gitignore
├── HELP.md
├── LICENSE
├── mvmw       
├── mvmw.cmd
├── pom.xml
└── README.md               # Documentación del proyecto
```

## Ejecución de la aplicación
1. Clona o descarga este repositorio.
2. Realize la configuración de las variables de entorno en su sistema operativo que estan en el archivo `config.properties` las cuales son `DB_HOST`, `DB_NAME`, `DB_USER`, `DB_PASSWORD`, `JWT_SECRET`.
3. Ejecuta el proyecto en el archivo `ForoHubChallengeApplication.java`.
4. Realize la prueba de los endpoints de la API Rest mediante Postman o Insomnia en la ruta `http://127.0.0.1:8080/`.
5. Para acceder a los endpoints, hacer una petición POST en la ruta `http://127.0.0.1:8080/login`, asignar en el cuerpo de la petición (body) ``{ "correoElectronico": "user@mail.com", "contrasena": "123456789" }``, que es el usuario previo creado a la primera ejecución del proyecto.

## Documentación de la aplicación
Para acceder a la documentación de la API REST para la ejecución de los endpoints, ir al navegador web y acceder al enlace: `http://127.0.0.1:8080/swagger-ui/index.html`.

## Tecnologías utilizadas

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- Flyway Migration
- MySQL
- Swagger

## Créditos

Desarrollado por [rflimat](https://github.com/rflimat) como parte del Challenge de Alura Latam.  
