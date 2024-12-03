Proyecto: Horóscopo Chino Web App
Descripción General
Este proyecto es una aplicación web que permite a los usuarios interactuar con el horóscopo chino. Los usuarios pueden registrarse, iniciar sesión, consultar su signo zodiacal, modificar sus datos, eliminar su cuenta, y listar otros usuarios registrados. La aplicación utiliza un diseño dinámico, intuitivo y amigable.

Características
Registro de Usuarios:

Los usuarios pueden registrarse proporcionando su nombre, correo electrónico, fecha de nacimiento, nombre de usuario y contraseña.
Al registrarse, se calcula automáticamente su signo zodiacal basado en su fecha de nacimiento.
Inicio de Sesión:

Los usuarios pueden iniciar sesión con su correo y contraseña.
Al iniciar sesión, el sistema muestra un mensaje personalizado con su signo zodiacal.
Consulta de Horóscopo Chino:

Los usuarios pueden ingresar su fecha de nacimiento para consultar su signo zodiacal.
Se muestra el nombre del signo junto con una imagen correspondiente.
Gestión de Usuarios:

Modificar Datos: Los usuarios pueden actualizar su información personal.
Eliminar Cuenta: Los usuarios pueden eliminar su cuenta y cerrar sesión automáticamente.
Listar Usuarios: Los usuarios registrados pueden ver una lista de otros usuarios del sistema.
Mensajes Informativos:

La aplicación utiliza modales para proporcionar retroalimentación al usuario sobre acciones como registro, modificación, eliminación de cuenta, etc.
Tecnologías Utilizadas
Backend:
Java con Jakarta Servlet
JDBC para la conexión a la base de datos
Frontend:
HTML5, CSS3
Bootstrap 5 para el diseño responsivo
JSP para la generación dinámica de contenido
Base de Datos:
PostgreSQL
Servidor:
Apache Tomcat 10.1

Configuración
Clonar el Proyecto:

bash
Copiar código
git clone https://github.com/tu-repositorio/horoscopo-chino.git
Configurar la Base de Datos:

Crear la base de datos horoscopo_db.
Ejecutar los scripts SQL para crear las tablas necesarias (usuarios, horoscopo, etc.).
Configurar las credenciales de la base de datos en la clase Conexion.java.
Ejecutar el Proyecto:

Compilar el proyecto en tu IDE.
Desplegarlo en Apache Tomcat.
Acceder en el navegador a: http://localhost:8090/HoroscopoChino.
Características Destacadas
Diseño Responsivo: La aplicación se adapta a diferentes tamaños de pantalla gracias a Bootstrap.
Interactividad Dinámica: Uso de modales para mensajes y confirmaciones.
Consulta de Horóscopo: Implementación de una lógica precisa para determinar el signo zodiacal.
Imágenes
Consulta de Horóscopo:

Modal de Eliminación:

Contribución
Haz un fork del proyecto.
Crea una rama para tu funcionalidad: git checkout -b feature/nueva-funcionalidad.
Realiza tus cambios y haz un commit: git commit -m "Añadida nueva funcionalidad".
Envía tus cambios: git push origin feature/nueva-funcionalidad.
Abre un Pull Request.

Información de la base de datos usada
id, nombre, username, email, fecha_nacimiento, password, animal

1	"David Rios"	"davrv1"	"david.r.v@hotmail.com"	"1992-12-01"	"123456789"	"Rata"
2	"Alex Rogget"	"arexdh"	"arexdh@hotmail.com"	"1992-10-03"	"123456789"	"Rata"
3	"Gonzalo Castro"	"Gon"	"gon@hotmail.com"	"1992-11-01"	"123456789"	"Mono"
17	"Koa Barbakoa"	"LobitoPolar"	"koa@hotmail.com"	"2023-02-21"	"123456789"	"Conejo"
18	"Irho"	"ositoPolar"	"osito@gmail.com"	"2023-02-21"	"123456789"	"Conejo"

Autor
David Rios

