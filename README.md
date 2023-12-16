## API de Usuarios
Bienvenido a la API de Usuarios. Esta API proporciona endpoints para gestionar usuarios en nuestra aplicación.

# Endpoints Disponibles

## Crear un Nuevo Usuario

 POST api/usuarios/sign-up

Descripción:
- Este endpoint permite la creación de un nuevo usuario. Se deben proporcionar los detalles del usuario en el cuerpo de la solicitud.

Ejemplo de body para crear usuario

	{
		"name":"Pablo Hincapie",
		"email":"pablohincapie@hotmail.com",
		"password": "A12dfgra",
	    	"phones":[
			{
		            "number":10,
		            "citycode":20,
		            "contrycode":"45"
			}
		    ]
	
	}
## Consultar usuario por token

GET /api/usuarios/login

 Descripción:
- Este endpoint devuelve información detallada sobre un usuario específico enviando su token.

{
	"token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYWJsb2hpbmNmZ2FnZ2dwZGZmd2llMjExQGhvdG1haWwuY29tIiwiaWF0IjoxNzAyNjY3MTQ1LCJleHAiOjE3MDM1MzExNDV9.5w3B3TWrsJDduQMztXaoI6L--b_-UAXvvrMAqKLRAeo"
}  

## Requisitos
- Java 11
- Spring Boot 2.5.14
- Gradle
- BD en H2(base de datos en memoria)

## Configuración
- Clonar el repositorio (git clone https://github.com/pablohincapie/bci-exercise-user.git)
- Configura la base de datos en application.properties.
- Ejecutar la aplicación probando los endpoints en local, se sugiere la herramienta postman

 - http://localhost:8080/api/usuarios/sign-up
 - http://localhost:8080/api/usuarios/login

- Revisar en la base de datos H2 con los datos por defecto que hay en el application.properties o cambiar credenciales segun desee.

  http://localhost:8080/h2-console 