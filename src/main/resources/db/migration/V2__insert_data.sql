INSERT INTO subscriptions (id, name, price, description)
VALUES (gen_random_uuid(), 'YouTube Premium', 199.00, 'Подписка на YouTube без рекламы и с фоновым воспроизведением'),
       (gen_random_uuid(), 'VK Музыка', 169.00, 'Музыкальная подписка на ВКонтакте'),
       (gen_random_uuid(), 'Яндекс.Плюс', 299.00, 'Подписка на сервисы Яндекса: КиноПоиск, Музыка, Диск и кешбэк'),
       (gen_random_uuid(), 'Netflix', 899.00, 'Популярный видеостриминговый сервис с фильмами и сериалами'),
       (gen_random_uuid(), 'Spotify Premium', 169.00, 'Музыкальный стриминговый сервис без рекламы'),
       (gen_random_uuid(), 'Apple Music', 169.00, 'Музыкальная подписка от Apple с миллионами треков'),
       (gen_random_uuid(), 'ivi', 399.00, 'Подписка на фильмы и сериалы в онлайн-кинотеатре ivi'),
       (gen_random_uuid(), 'Okko', 299.00, 'Онлайн-кинотеатр с фильмами и сериалами в HD качестве'),
       (gen_random_uuid(), 'Амедиатека', 599.00, 'Доступ к сериалам HBO, Showtime и другим эксклюзивным проектам'),
       (gen_random_uuid(), 'Start', 299.00, 'Подписка на российские сериалы и фильмы от START'),
       (gen_random_uuid(), 'Megogo', 197.00, 'Кино и ТВ в одном сервисе с подпиской'),
       (gen_random_uuid(), 'Wink', 399.00, 'Медиаплатформа с подпиской на ТВ и фильмы от Ростелекома');

INSERT INTO users (id, first_name, last_name, email, password)
VALUES (gen_random_uuid(), 'Иван', 'Иванов', 'ivan.ivanov@example.com', '12345'),
       (gen_random_uuid(), 'Мария', 'Петрова', 'maria.petrova@example.com', '12345'),
       (gen_random_uuid(), 'Алексей', 'Сидоров', 'alexey.sidorov@example.com', '12345'),
       (gen_random_uuid(), 'Екатерина', 'Смирнова', 'ekaterina.smirnova@example.com', '12345'),
       (gen_random_uuid(), 'Дмитрий', 'Фёдоров', 'dmitry.fedorov@example.com', '12345');


INSERT INTO user_subscriptions (user_id, subscription_id, start_date, end_date)
VALUES
    ((SELECT id FROM users WHERE email = 'ivan.ivanov@example.com'),
     (SELECT id FROM subscriptions WHERE name = 'YouTube Premium'), current_date, current_date + INTERVAL '1 month'),
    ((SELECT id FROM users WHERE email = 'ivan.ivanov@example.com'),
     (SELECT id FROM subscriptions WHERE name = 'Netflix'), current_date, current_date + INTERVAL '1 month'),
    ((SELECT id FROM users WHERE email = 'ivan.ivanov@example.com'),
     (SELECT id FROM subscriptions WHERE name = 'Apple Music'), current_date, current_date + INTERVAL '1 month'),

    ((SELECT id FROM users WHERE email = 'maria.petrova@example.com'),
     (SELECT id FROM subscriptions WHERE name = 'VK Музыка'), current_date, current_date + INTERVAL '1 month'),
    ((SELECT id FROM users WHERE email = 'maria.petrova@example.com'),
     (SELECT id FROM subscriptions WHERE name = 'Яндекс.Плюс'), current_date, current_date + INTERVAL '1 month'),
    ((SELECT id FROM users WHERE email = 'maria.petrova@example.com'),
     (SELECT id FROM subscriptions WHERE name = 'Okko'), current_date, current_date + INTERVAL '1 month'),

    ((SELECT id FROM users WHERE email = 'alexey.sidorov@example.com'),
     (SELECT id FROM subscriptions WHERE name = 'Netflix'), current_date, current_date + INTERVAL '1 month'),
    ((SELECT id FROM users WHERE email = 'alexey.sidorov@example.com'),
     (SELECT id FROM subscriptions WHERE name = 'Spotify Premium'), current_date, current_date + INTERVAL '1 month'),
    ((SELECT id FROM users WHERE email = 'alexey.sidorov@example.com'),
     (SELECT id FROM subscriptions WHERE name = 'Амедиатека'), current_date, current_date + INTERVAL '1 month'),

    ((SELECT id FROM users WHERE email = 'ekaterina.smirnova@example.com'),
     (SELECT id FROM subscriptions WHERE name = 'Start'), current_date, current_date + INTERVAL '1 month'),
    ((SELECT id FROM users WHERE email = 'ekaterina.smirnova@example.com'),
     (SELECT id FROM subscriptions WHERE name = 'Megogo'), current_date, current_date + INTERVAL '1 month'),
    ((SELECT id FROM users WHERE email = 'ekaterina.smirnova@example.com'),
     (SELECT id FROM subscriptions WHERE name = 'Wink'), current_date, current_date + INTERVAL '1 month'),

    ((SELECT id FROM users WHERE email = 'dmitry.fedorov@example.com'),
     (SELECT id FROM subscriptions WHERE name = 'ivi'), current_date, current_date + INTERVAL '1 month'),
    ((SELECT id FROM users WHERE email = 'dmitry.fedorov@example.com'),
     (SELECT id FROM subscriptions WHERE name = 'Netflix'), current_date, current_date + INTERVAL '1 month'),
    ((SELECT id FROM users WHERE email = 'dmitry.fedorov@example.com'),
     (SELECT id FROM subscriptions WHERE name = 'Apple Music'), current_date, current_date + INTERVAL '1 month');


