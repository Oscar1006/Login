# 🛡️ Sistema de Autenticación Segura con Spring Boot + H2 + Thymeleaf

Este proyecto es una aplicación web desarrollada con **Spring Boot**, que implementa un sistema de login local seguro utilizando **BCrypt** para el hashing de contraseñas. Cuenta con un rol `USER` por defecto y un panel exclusivo para usuarios con rol `ADMIN`.

---

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot 3+
- Spring Security
- Spring Data JPA
- H2 Database (modo en memoria)
- Thymeleaf
- Maven

---

## ⚙️ Funcionalidades principales

### 👤 Usuarios comunes (`ROLE_USER`)
- Registro de cuenta
- Login mediante formulario web
- Consulta de su perfil personal (`/profile`)
- Visualización de la fecha y hora del último login
- Cambio de contraseña mediante formulario

### 🛠 Administrador (`ROLE_ADMIN`)
- Acceso a un panel exclusivo en `/admin`
- Visualización de todos los usuarios registrados
- Eliminación de usuarios
- Reseteo de contraseñas (ponerlas en blanco)

---

## 🗂️ Estructura de carpetas destacada

```

src/main/java/com/cibersecurity/login/
├── config/              # Configuración de seguridad
├── controller/          # Controladores web
├── dto/                 # Objetos de transferencia de datos
├── model/               # Entidades JPA (ej: User)
├── repository/          # Repositorios JPA
├── security/            # Clases de seguridad personalizadas
├── service/             # Lógica de negocio
└── LoginApplication.java

````

---

## 🧪 Cómo ejecutar el proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/tuusuario/proyecto-login.git
cd proyecto-login
````

### 2. Ejecutar con Maven

```bash
./mvnw spring-boot:run
```


## 🧰 Acceso a la base de datos H2

1. Inicia la app y ve a: `http://localhost:8080/h2-console`
2. Conéctate con:

```
JDBC URL: jdbc:h2:mem:miappdb
User: sa
Password: (vacío)
```

---

## 🔐 Credenciales de prueba

Puedes crear usuarios desde la interfaz de registro. Para probar el rol `ADMIN`, cambia manualmente el rol en la base de datos:

```sql
UPDATE users SET role = 'ROLE_ADMIN' WHERE username = 'admin';
```

---

## 🛡️ Seguridad

* Las contraseñas se almacenan usando el algoritmo **BCrypt**
* Spring Security maneja el control de acceso por roles
* Se usa un `AuthenticationSuccessHandler` para registrar la fecha de último login

---

## 🧑‍🏫 Recomendado por el profesor

El algoritmo PBKDF2 fue explorado como alternativa, pero se optó por **BCrypt** por su integración nativa con Spring y resistencia a ataques por GPU.

---

## ✅ Mejoras futuras

* Integración con OAuth2 (Google, GitHub)

---



