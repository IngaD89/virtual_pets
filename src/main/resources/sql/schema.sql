CREATE TABLE IF NOT EXISTS petdb.users (
    id BINARY(16) PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    role VARCHAR(50) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    nickname VARCHAR(50) UNIQUE NOT NULL,
    password_salt BINARY(16) NOT NULL,
    password_hash BINARY(16) NOT NULL,
    deleted BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE IF NOT EXISTS petdb.sessions (
    id BINARY(16) PRIMARY KEY,
    user_id BINARY(16) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    expired_at TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES petdb.users(id)
);
