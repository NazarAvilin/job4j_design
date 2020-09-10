--users - role = many-to-one
--role - rules = many-to-many
--item - users = many-to-one
--item - comments = one-to-many
--item - attachments = one-to-many
--item - category = many-to-one
--item - state = many-to-one

CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    role_id INT REFERENCES role(id)
);

CREATE TABLE rules (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE role_rules (
    id SERIAL PRIMARY KEY,
    role_id INT REFERENCES role(id),
    rules_id INT REFERENCES rules(id)
);

CREATE TABLE state (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE item (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    state_id INT REFERENCES  state(id),
    category_id INT REFERENCES  category(id),
    user_id INT REFERENCES  users(id)
);

CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    item_id INT REFERENCES comments(id)
);

CREATE TABLE attachments (
    id SERIAL PRIMARY KEY,
    description TEXT,
    item_id INT REFERENCES comments(id)
);