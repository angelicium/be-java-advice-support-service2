--liquibase formatted sql
--changeset Denishev-Maxim 2025-06-23:rename-table-user-to-users
ALTER TABLE operator
DROP CONSTRAINT IF EXISTS fk_operator_user;

ALTER TABLE "user" RENAME TO "users";

ALTER TABLE operator
    ADD CONSTRAINT fk_operator_users FOREIGN KEY (user_id) REFERENCES "users" (id)
        ON DELETE CASCADE;