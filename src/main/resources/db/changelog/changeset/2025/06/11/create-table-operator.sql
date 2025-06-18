--liquibase formatted sql
--changeset Denishev-Maxim 2025-06-11:create-table-ticket-category
CREATE TABLE IF NOT EXISTS operator
(
    id             UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id        UUID      NOT NULL,
    specialization VARCHAR(255),
    max_tickets    INTEGER   NOT NULL DEFAULT 10,
    created_at     TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT fk_operator_user FOREIGN KEY (user_id) REFERENCES "user" (id)
    ON DELETE CASCADE
    );