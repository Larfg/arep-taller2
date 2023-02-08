# Taller 2: Taller diseño y estructuración de aplicaciones distribuidas en internet 

## Diseño y descripción del diseño: 
Para este taller modificamos el servidor del taller 1, de tal manera que podamos acceder a distintos servicios a partir de un path en la URL, ademas implementamos un servicio que permite leer el disco local en el cual se está ejecutando el servidor, nos permite leer varios tipos de archivos, por ultimo creamos un cliente web para realizar la busqueda en el disco duro.
Todo el taller se realizo sin nungún framework, utilizando las librerias de java.
El cliente web se integro dentro del primer reto del laboratorio, es decir que se incluyo como un servicio dentro del servidor al cual podemos acceder por un path.

Este taller se construllo con una arquitectura de aplicaciones distribuidas, donde tenemos varios servicios de facil acceso y modulares dentro de nuestra aplicación.

### Extensibilidad 
Para asegurar la extensibilidad se utilizo la interfaz de servicio la que nos permite una estructura de retorno de un mensaje HTTP con header y body, estructura que nos permite inyectar los servicios que deseemos en cualquier momento.

### Uso de patrones 
Utilizamos un patrón **SINGLETON** para asegurarnos de que solo exista una instancia del servidor y usamos un patrón **ABSTRACT FACTORY** a la hora de manejar nuestros servicios. 

### Modularidad 
Al tener las aplicaciones distribuidas, podemos cambiar cualquiera de los elementos y estos tiene poca cohesión o ninguna cohesión entre los elementos (módulos), por ejemplo podemos añadir uns servicio cuando queramos, y utilizarlo facilmente en la aplicación. 

## Prerrequisitos: 
Debe tener java, Maven y un navegador web, preferiblemente Firefox.

## Instalación: 
Clone el repositorio.

## Realizando pruebas: 
Para probar el funcionamiento del servidor creamos cliente web como servicio dentro de nuestro servidor, al cual puede acceder una vez funcione el servidor, con (http://localhost:35000/search/). Tamien los invitamos a buscar cualquier path en el navegador, así no tenga servicio definido.
  
## Despliegue: 
- Para poder iniciar el servidor se puede iniciar por Maven, con el siguiente comando "mvn compile exec:java" o puede iniciarlo directamente con la clase ejecutiva del proyecto. 
- Para probar los servicios pruebe el siguiente enlace despúes de ejecutar el servidor (http://localhost:35000/).
  
## Construido con: 
[Maven](https://maven.apache.org/) - Dependency Management   

## Autores: 
- Luis Felipe Andres Giraldo Rodriguez 
