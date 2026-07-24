-- ==========================================
-- Database: Book Manager
-- PostgreSQL
-- ==========================================


-- ==========================================
-- Tabela de usuários
-- ==========================================

CREATE TABLE IF NOT EXISTS users (

    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(255) NOT NULL,

    email VARCHAR(255) NOT NULL UNIQUE,

    password VARCHAR(255) NOT NULL

);



-- ==========================================
-- Tabela de livros
-- ==========================================

CREATE TABLE IF NOT EXISTS books (

    id BIGSERIAL PRIMARY KEY,

    title VARCHAR(255) NOT NULL,

    author VARCHAR(255) NOT NULL,

    year INTEGER,

    description TEXT,

    user_id BIGINT NOT NULL,


    CONSTRAINT fk_books_users

        FOREIGN KEY (user_id)

        REFERENCES users(id)

        ON DELETE CASCADE

);