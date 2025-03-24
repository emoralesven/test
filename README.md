# 📘 API REST - Registro de Usuarios

Una API RESTful desarrollada con **Spring Boot**, usando arquitectura **hexagonal**, principios **SOLID**, JWT, validaciones personalizadas, y documentación automática con Swagger.

---

## ⚙️ Tecnologías Utilizadas

- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Security (para JWT)
- H2 (base de datos en memoria)
- Lombok
- JJWT (Java JWT)
- Swagger (Springfox 3)
- Maven

---

## 📦 Estructura del Proyecto

```
src/
├── application/
│   ├── dto/                 # Objetos de transferencia (DTO)
│   └── service/             # Casos de uso
├── domain/
│   ├── model/               # Entidades del dominio
│   └── ports/               # Puertos de entrada y salida
├── infrastructure/
│   ├── config/              # Configuraciones (regex, seguridad)
│   ├── controller/          # Adaptadores REST
│   ├── repository/          # Adaptadores de persistencia
│   └── security/            # Utilidad JWT
├── resources/
│   └── application.properties
└── RestApplication.java     # Clase principal
```

---

## 🚀 Cómo ejecutar el proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/tuusuario/tu-repo.git
cd tu-repo
```

### 2. Ejecutar el proyecto

```bash
./mvnw spring-boot:run
```

> O ejecutar la clase `RestApplication` desde tu IDE (IntelliJ, VSCode, etc.)

---

## 🔐 JWT - Configuración

El token JWT se genera automáticamente al crear un usuario y se firma con una clave secreta de al menos 256 bits.

En `application.properties`:

```properties
jwt.secret=U3VwZXJTZWNyZXRLZXlGb3JKd3dUb2tlblNpZ25pbmch
```

Esta clave debe estar codificada en base64 y tener una longitud mínima de 32 bytes.

---

## 🧾 Validaciones Personalizadas

En `application.properties`:

```properties
regex.email=^[\w._%+-]+@\w+\.cl$
regex.password=^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$
```

Estas expresiones pueden modificarse para adaptar el comportamiento sin tocar el código fuente.

---

## 📨 Endpoint: Registro de Usuario

### 🔗 POST `/api/users`

**Request Body:**
```json
{
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.cl",
  "password": "hunter2a",
  "phones": [
    {
      "number": "1234567",
      "cityCode": "1",
      "countryCode": "57"
    }
  ]
}
```

**Response Exitosa:**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.cl",
  "password": "hunter2a",
  "created": "2025-03-24T16:10:00",
  "modified": "2025-03-24T16:10:00",
  "lastLogin": "2025-03-24T16:10:00",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI...",
  "isActive": true,
  "phones": [ {...} ]
}
```

**Errores posibles:**
- Email inválido
- Contraseña inválida
- Email ya registrado

**Formato de error:**
```json
{
  "mensaje": "El correo ya registrado"
}
```

---

## 🧪 Pruebas Unitarias

```bash
./mvnw test
```

Se cubren casos como:
- Creación exitosa de usuario
- Email duplicado
- Validación de expresiones regulares

---

## 📚 Swagger UI

La documentación está disponible automáticamente en:

```
http://localhost:8080/swagger-ui/
```

Incluye ejemplos de request/response para facilitar el testing y la exploración.

---

## 🛢️ Consola de Base de Datos H2

```
http://localhost:8080/h2-console
```

- JDBC URL: `jdbc:h2:mem:testdb`
- Usuario: `sa`
- Contraseña: *(vacía)*

---

## 🧑 Autor

**Eduardo Esteban Morales Venegas**  
Ingeniero Civil Informático - Backend Developer  
📧 emoralesinf@gmail.com

---

## 📝 Licencia

MIT © 2025 Eduardo Morales

