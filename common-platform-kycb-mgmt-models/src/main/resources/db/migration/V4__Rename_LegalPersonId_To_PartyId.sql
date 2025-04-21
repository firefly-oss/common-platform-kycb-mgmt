-- V4__Rename_LegalPersonId_To_PartyId.sql

-- Rename legal_person_id column to party_id in kyb_verification table
ALTER TABLE kyb_verification RENAME COLUMN legal_person_id TO party_id;