CREATE TABLE subscriptions
(
    id          uuid PRIMARY KEY         DEFAULT gen_random_uuid(),
    name        varchar(250)   NOT NULL,
    description text,
    price       numeric(10, 2) NOT NULL CHECK (price >= 0)
);

CREATE TABLE users
(
    id              uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name      varchar(250)        not null,
    last_name       varchar(250)        not null,
    email           varchar(254) unique not null,
    password        varchar(254)        not null,
    is_active       boolean          default true,
    CONSTRAINT first_name_min_length CHECK (char_length(first_name) >= 2),
    CONSTRAINT last_name_min_length CHECK (char_length(last_name) >= 2),
    CONSTRAINT email_min_length CHECK (char_length(email) >= 6)
);

CREATE TABLE user_subscriptions
(
    user_id         uuid NOT NULL,
    subscription_id uuid NOT NULL,
    start_date      date NOT NULL DEFAULT current_date,
    end_date        date,
    is_active       boolean          default true,
    CONSTRAINT user_subscriptions_pk PRIMARY KEY (user_id, subscription_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_subscription FOREIGN KEY (subscription_id) REFERENCES subscriptions (id) ON DELETE CASCADE
);

