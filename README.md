# Person REST API

Этот проект реализует CRUD-операции над сущностью `Person` в соответствии с архитектурным стилем REST.

## 📦 Эндпоинты

- `GET /person/` — Получить список всех пользователей
- `GET /person/{id}` — Получить пользователя по ID
- `POST /person/` — Создать нового пользователя
- `PUT /person/` — Обновить данные пользователя
- `DELETE /person/` — Удалить пользователя

## ⚙️ Примеры curl-запросов

### 📤 Создать пользователя
```bash
curl -X POST http://localhost:8080/person/ \
     -H "Content-Type: application/json" \
     -d '{"name": "Петр", "age": 28}'
```

### 📥 Получить всех пользователей

```bash
curl -X GET http://localhost:8080/person/
```

### 📥 Получить пользователя по ID
```bash
curl -X GET http://localhost:8080/person/1
```

### 🔄 Обновить пользователя
```curl -X PUT http://localhost:8080/person/ \
     -H "Content-Type: application/json" \
     -d '{"id": 3, "name": "Петр Иванов", "age": 29}'
```

### ❌ Удалить пользователя
```curl -X DELETE http://localhost:8080/person/ \
     -H "Content-Type: application/json" \
     -d '{"id": 3}'
```

### 🧪 Используемые технологии

- Java 17+
- Spring Boot
- Spring Web
- Postgresql
- Spring data jpa