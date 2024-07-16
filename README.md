<div align="center">
  <h1 align="center">
    Challenge Foro Hub
  </h1>
  <h3> 
    Un Challenge de Alura - ONE
  </h3>
    <br />
  <img src="https://github.com/user-attachments/assets/50722278-12a3-4206-b75f-a6a2b6d5848d">
   <br />
   <br />
</div>

## Introducción

**Challenge realizado en [Java](https://docs.oracle.com/en/java/javase/17/docs/api/index.html)**

Para este último desafío de Back End con Java, crearemos una API REST usando Spring. 
La API se centra específicamente en los tópicos, y permite a los usuarios:
- Crear un nuevo tópico
- Mostrar todos los tópicos creados
- Mostrar un tópico específico
- Actualizar un tópico
- Eliminar un tópico
Es lo que normalmente conocemos como CRUD (CREATE, READ, UPDATE, DELETE).

Además aplicamos otros temas relacionados, como:
- La implementación de una base de datos relacional para la persistencia de la información
- Incorporación de un servicio de autenticación/autorización para restringir el acceso a la información

 

## ¿Cómo se usa?
A través de los endpoints se pueden realizan las consultas http, las cuales son:

### POST "/login"
(Con los datos del usuario, para obtener el token de autenticación)

![Captura de pantalla 2024-07-16 080554](https://github.com/user-attachments/assets/224c13e8-d5f1-4e35-b456-d35a00cb9fcc)
<br />
<br />

### POST "/topicos"
(Con los datos del nuevo tópico a ingresar)

![Captura de pantalla 2024-07-16 080719](https://github.com/user-attachments/assets/9bd25a88-53a6-4972-8f6d-0d9f7cff7dd2)
### (requiere autenticación, al igual que todos los siguientes)
![Captura de pantalla 2024-07-16 080755](https://github.com/user-attachments/assets/4ea78073-40a8-4c86-b169-ca5be1d70dc0)
<br />
<br />

### GET "/topicos"
(Muestra una paginación con todos los tópicos registrados, formateados de a 10 por página)

![Captura de pantalla 2024-07-16 080849](https://github.com/user-attachments/assets/d2258ee2-45fe-41d3-bec3-a3d0df824244)
<br />
<br />

### DELETE "/topicos/{id}"
(Recibe el id del tópico a eliminar a través de la url)

![Captura de pantalla 2024-07-16 081126](https://github.com/user-attachments/assets/71740eed-657e-43fb-9f43-6f5a77bbb9ad)

(Fue eliminado, ya no aparece al hacer el listado)

![Captura de pantalla 2024-07-16 081302](https://github.com/user-attachments/assets/e6fc21a3-977f-4c08-ae0b-a11296847d4b)
<br />
<br />

### PUT "/topicos/{id}"
(Recibe el id del tópico a modificar a través de la url)

![Captura de pantalla 2024-07-16 085920](https://github.com/user-attachments/assets/d9a5cb0f-5360-44d6-98d9-b29964ae9823)

La modificación es flexible a los campos que el usuario desee ingresar, los que no se envíen conservaran su valor sin sufrir cambios.

## Este proyecto incluye:
- Base de datos relacional MySQL
- Sping Boot con dependencias como Spring Data JPA y MySQL Driver para la persistencia de datos
- Spring Security y auth0 para las autenticaciones y seguridad de la aplicación
- CRUD completo
- Interacción con el usuario a través de peticiones
- Aplicación de los conceptos de abstracción, encapsulamiento y polimorfismo
