CREATE TABLE IF NOT EXISTS petdb.users (
    id BINARY(16) PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    role VARCHAR(50) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    nickname VARCHAR(50) UNIQUE NOT NULL,
    password_salt CHAR(32) NOT NULL,
    password_hash CHAR(64) NOT NULL,
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

CREATE TABLE IF NOT EXISTS petdb.pets (
    id BINARY(16) NOT NULL,
    owner_id BINARY(16) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_fed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_played_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    pet_status ENUM( 'NEWBORN', 'HUNGRY', 'TIRED', 'PLAYFUL', 'HAPPY', 'SAD', 'SLEEPING', 'BORED', 'SICK') NOT NULL,
    pet_character ENUM('KEVIN', 'STUART', 'BOB') NOT NULL,
    pet_type ENUM('YELLOW', 'PURPLE') NOT NULL,
    name VARCHAR(255) NOT NULL,
    energy_level INT NOT NULL,
    hunger_level INT NOT NULL,
    playfulness_level INT NOT NULL,
    max_energy INT NOT NULL,
    max_hunger INT NOT NULL,
    max_playfulness INT NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES petdb.users(id)
);

