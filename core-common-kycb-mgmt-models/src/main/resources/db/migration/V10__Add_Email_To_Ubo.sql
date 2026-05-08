-- V10__Add_Email_To_Ubo.sql
-- Adds the contact email of the Ultimate Beneficial Owner. Optional.

ALTER TABLE ubo
    ADD COLUMN email VARCHAR(255);

COMMENT ON COLUMN ubo.email IS 'Contact email of the Ultimate Beneficial Owner captured at registration';
