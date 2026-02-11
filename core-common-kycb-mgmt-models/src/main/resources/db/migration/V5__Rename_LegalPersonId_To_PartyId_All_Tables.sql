-- V5__Rename_LegalPersonId_To_PartyId_All_Tables.sql

-- Rename legal_person_id column to party_id in all remaining tables

-- Table: corporate_document
ALTER TABLE corporate_document RENAME COLUMN legal_person_id TO party_id;

-- Table: power_of_attorney
ALTER TABLE power_of_attorney RENAME COLUMN legal_person_id TO party_id;

-- Table: ubo
ALTER TABLE ubo RENAME COLUMN legal_person_id TO party_id;

-- Table: corporate_structure
ALTER TABLE corporate_structure RENAME COLUMN legal_person_id TO party_id;

-- Table: business_profile
ALTER TABLE business_profile RENAME COLUMN legal_person_id TO party_id;

-- Table: economic_activity
ALTER TABLE economic_activity RENAME COLUMN legal_person_id TO party_id;

-- Table: business_location
ALTER TABLE business_location RENAME COLUMN legal_person_id TO party_id;