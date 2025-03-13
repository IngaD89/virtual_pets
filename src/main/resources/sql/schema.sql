CREATE TABLE petdb.users (
    id char(36) PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    role VARCHAR(50) NOT NULL,
    nickname VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_salt VARBINARY(16) NOT NULL,
    password_hash VARBINARY(64) NOT NULL,
    deleted BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE petdb.sessions(
    id CHAR(36) PRIMARY KEY,
    user_id CHAR(36),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    expired_at TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) CONSTRAINT fk_user_id REFERENCES users(id)
);

