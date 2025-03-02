# Sub Track

## Описание
Sub Track — это REST API-приложение, которое позволяет управлять пользователями и их подписками на цифровые сервисы.

Каждый пользователь может:

Создавать учетную запись

Просматривать свои данные

Обновлять информацию

Удалять свою учетную запись

Каждая подписка включает:

Название сервиса (например, YouTube Premium, VK Музыка, Netflix)

Привязку к пользователю

---

## Стек технологий
- **Java 17**
- **Spring Boot 3.4**
- **Spring Data JPA** (Hibernate)
- **PostgreSQL** (база для тестирования)
- **Lombok** (упрощение работы с POJO)
- **SLF4J** (логирование)
- **Maven** (сборка проекта)
- **FlayWay** (миграция)

---

## API Эндпоинты

👤 **Пользователи**

POST /api/v1/users - создать пользователя

GET /api/v1/users/{id} - получить информацию о пользователе

PUT /api/v1/users/{id} - обновить пользователя

DELETE /api/v1/users/{id} - удалить пользователя

📜 **Подписки**

POST /api/v1/users/{id}/subscriptions - добавить подписку

GET /api/v1/users/{id}/subscriptions - получить подписки пользователя

DELETE /api/v1/users/{id}/subscriptions/{sub_id} - удалить подписку

GET /api/v1/subscriptions/top - получить ТОП-3 популярных подписок