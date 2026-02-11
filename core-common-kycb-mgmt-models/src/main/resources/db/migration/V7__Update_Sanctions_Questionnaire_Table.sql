-- V7__Update_Sanctions_Questionnaire_Table.sql

-- Create enum type for entity_sanctions_questionnaire
CREATE TYPE entity_sanctions_questionnaire_type AS ENUM (
    'LEGAL_ENTITY_ONLY',
    'LEGAL_ENTITY_AND_WHOLLY_OWNED_SUBSIDIARIES',
    'LEGAL_ENTITY_AND_MAJORITY_OWNED_SUBSIDIARIES',
    'LEGAL_ENTITY_AND_SPECIFIC_SUBSIDIARIES'
);

-- Add a temporary column for the new enum type
ALTER TABLE sanctions_questionnaire ADD COLUMN entity_sanctions_questionnaire_enum entity_sanctions_questionnaire_type;

-- Update the temporary column with the corresponding enum values based on the integer values
UPDATE sanctions_questionnaire SET entity_sanctions_questionnaire_enum = 
    CASE entity_sanctions_questionnaire
        WHEN 1 THEN 'LEGAL_ENTITY_ONLY'::entity_sanctions_questionnaire_type
        WHEN 2 THEN 'LEGAL_ENTITY_AND_WHOLLY_OWNED_SUBSIDIARIES'::entity_sanctions_questionnaire_type
        WHEN 3 THEN 'LEGAL_ENTITY_AND_MAJORITY_OWNED_SUBSIDIARIES'::entity_sanctions_questionnaire_type
        WHEN 4 THEN 'LEGAL_ENTITY_AND_SPECIFIC_SUBSIDIARIES'::entity_sanctions_questionnaire_type
    END;

-- Drop the old column and rename the new one
ALTER TABLE sanctions_questionnaire DROP COLUMN entity_sanctions_questionnaire;
ALTER TABLE sanctions_questionnaire RENAME COLUMN entity_sanctions_questionnaire_enum TO entity_sanctions_questionnaire;
ALTER TABLE sanctions_questionnaire ALTER COLUMN entity_sanctions_questionnaire SET NOT NULL;

-- Update the column comment
COMMENT ON COLUMN sanctions_questionnaire.entity_sanctions_questionnaire IS 'Defines for which part of the entity the user answers the questionnaire: LEGAL_ENTITY_ONLY, LEGAL_ENTITY_AND_WHOLLY_OWNED_SUBSIDIARIES, LEGAL_ENTITY_AND_MAJORITY_OWNED_SUBSIDIARIES, LEGAL_ENTITY_AND_SPECIFIC_SUBSIDIARIES';

-- Convert boolean columns from INTEGER to BOOLEAN
-- First, add temporary boolean columns
ALTER TABLE sanctions_questionnaire ADD COLUMN activity_outside_eu_bool BOOLEAN;
ALTER TABLE sanctions_questionnaire ADD COLUMN economic_sanctions_bool BOOLEAN;
ALTER TABLE sanctions_questionnaire ADD COLUMN resident_countries_sanctions_bool BOOLEAN;
ALTER TABLE sanctions_questionnaire ADD COLUMN involved_sanctions_bool BOOLEAN;

-- Update the temporary columns with the corresponding boolean values
UPDATE sanctions_questionnaire SET 
    activity_outside_eu_bool = (activity_outside_eu = 1),
    economic_sanctions_bool = (economic_sanctions = 1),
    resident_countries_sanctions_bool = (resident_countries_sanctions = 1),
    involved_sanctions_bool = (involved_sanctions = 1);

-- Drop the old columns and rename the new ones
ALTER TABLE sanctions_questionnaire DROP COLUMN activity_outside_eu;
ALTER TABLE sanctions_questionnaire DROP COLUMN economic_sanctions;
ALTER TABLE sanctions_questionnaire DROP COLUMN resident_countries_sanctions;
ALTER TABLE sanctions_questionnaire DROP COLUMN involved_sanctions;

ALTER TABLE sanctions_questionnaire RENAME COLUMN activity_outside_eu_bool TO activity_outside_eu;
ALTER TABLE sanctions_questionnaire RENAME COLUMN economic_sanctions_bool TO economic_sanctions;
ALTER TABLE sanctions_questionnaire RENAME COLUMN resident_countries_sanctions_bool TO resident_countries_sanctions;
ALTER TABLE sanctions_questionnaire RENAME COLUMN involved_sanctions_bool TO involved_sanctions;

ALTER TABLE sanctions_questionnaire ALTER COLUMN activity_outside_eu SET NOT NULL;

-- Update the column comments
COMMENT ON COLUMN sanctions_questionnaire.activity_outside_eu IS 'Whether the entity and its business units, subsidiaries, and joint ventures have a commercial activity outside the European Union.';
COMMENT ON COLUMN sanctions_questionnaire.economic_sanctions IS 'Whether the entity, its subsidiaries, entities, employees, directors, beneficial owners, or joint ventures are subject to Economic Sanctions.';
COMMENT ON COLUMN sanctions_questionnaire.resident_countries_sanctions IS 'Whether the entity, its subsidiaries, or joint ventures, located within or operating from any countries or territories are subject to Comprehensive Sanctions.';
COMMENT ON COLUMN sanctions_questionnaire.involved_sanctions IS 'Whether the entity, its subsidiaries, or joint ventures, are engaged in transactions, investments, business, or other dealings that directly or indirectly involve or benefit any countries or territories subject to Comprehensive Sanctions, or any person or entity which is the target of any Sanctions.';