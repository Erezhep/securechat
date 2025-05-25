# SecureChat

SecureChat — это современное и безопасное веб-приложение для обмена сообщениями в реальном времени, разработанное с использованием Spring Boot, PostgreSQL, WebSocket и AES-GCM шифрования. Приложение легко разворачивается в контейнеризированной среде через Docker Compose.

## Быстрый старт

1. Клонируйте репозиторий:

```bash
git clone https://repo-url.git
cd securechat
```

2. Запустите приложение в Docker:

```bash
docker-compose up --build
```

После запуска:

- Приложение: http://localhost:9090
- PostgreSQL: порт 5433 (в контейнере 5432)

## Технологии

- Java 17
- Spring Boot 3.5.0
- Spring Security
- Spring Data JPA
- Spring WebSocket
- PostgreSQL 15
- Thymeleaf
- Docker + Docker Compose
- Crypto Subtle API (AES-GCM шифрование на клиенте)

## Структура проекта

```
securechat/
├── src/
│   ├── main/
│   │   ├── java/com/chat/securechat/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── entity/
│   │   │   ├── repository/
│   │   │   └── SecureChatApplication.java
│   │   └── resources/
│   │       ├── templates/
│   │       └── application.yml
├── Dockerfile
├── docker-compose.yml
└── README.md
```

## Безопасность

- Аутентификация: Spring Security (форма логина)
- Шифрование сообщений: AES-GCM (на клиенте)
- Публичный ключ выдается только авторизованным пользователям

## API и WebSocket

### Получение публичного ключа

```http
GET /api/public-key
```

Пример ответа:
```json
{
  "key": "base64-ключ"
}
```

### WebSocket

**Подписка на сообщения:**
```
/topic/messages
```

**Отправка сообщения:**
```
/app/chat.send
```

Формат сообщения:
```json
{
  "sender": "Username",
  "content": "EncryptedText",
  "timestamp": "2025-05-26T10:00:00Z"
}
```

### Загрузка последних сообщений

```http
GET /api/messages/latest
```

## Работа с PostgreSQL (в контейнере)

Подключиться к БД:

```bash
docker exec -it securechat-db-1 psql -U postgres
```

Внутри `psql`:

```sql
\l                      -- список баз данных
\c securechat           -- подключиться к базе
\dt                     -- список таблиц
SELECT * FROM messages; -- просмотреть сообщения
\q                      -- выход
```

## Разработка

Для локальной разработки (без Docker) можно использовать, но нужно настроить некоторые настройки:

```bash
./mvnw spring-boot:run
```

или запустить проект через вашу IDE (IntelliJ IDEA и т.д.)

## Очистка и перезапуск

```bash
docker-compose down
docker-compose up --build
```

## Статус

- [x] Регистрация и вход
- [x] Шифрование сообщений на клиенте
- [x] WebSocket чат
- [x] Хранение сообщений в PostgreSQL
- [x] Docker-окружение

## Обратная связь

Если вы нашли баг или у вас есть предложения по улучшению, создайте issue или отправьте pull request.
