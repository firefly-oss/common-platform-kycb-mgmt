-- V6__Create_Sanctions_Questionnaire_Table.sql

-- Table: sanctions_questionnaire
CREATE TABLE IF NOT EXISTS sanctions_questionnaire (
    sanctions_questionnaire_id BIGSERIAL PRIMARY KEY,
    party_id BIGINT NOT NULL,
    entity_sanctions_questionnaire INTEGER NOT NULL,
    activity_outside_eu INTEGER NOT NULL,
    economic_sanctions INTEGER,
    resident_countries_sanctions INTEGER,
    involved_sanctions INTEGER,
    questionnaire_date TIMESTAMP,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP
);

-- Add comments to the table and columns
COMMENT ON TABLE sanctions_questionnaire IS 'Stores sanctions and embargo questionnaire data for parties';
COMMENT ON COLUMN sanctions_questionnaire.sanctions_questionnaire_id IS 'Primary key for the sanctions questionnaire';
COMMENT ON COLUMN sanctions_questionnaire.party_id IS 'Foreign key to the party this questionnaire belongs to';
COMMENT ON COLUMN sanctions_questionnaire.entity_sanctions_questionnaire IS 'Defines for which part of the entity the user answers the questionnaire: 1 – Only the legal entity, 2 – The legal entity and all its subsidiaries held at 100%, 3 – The legal entity and all its subsidiaries held at 50% or more, 4 – The legal entity and a list of subsidiaries';
COMMENT ON COLUMN sanctions_questionnaire.activity_outside_eu IS 'Whether the entity and its business units, subsidiaries, and joint ventures have a commercial activity outside the European Union. 0 – False, 1 – True';
COMMENT ON COLUMN sanctions_questionnaire.economic_sanctions IS 'Whether the entity, its subsidiaries, entities, employees, directors, beneficial owners, or joint ventures are subject to Economic Sanctions. 0 – False, 1 – True';
COMMENT ON COLUMN sanctions_questionnaire.resident_countries_sanctions IS 'Whether the entity, its subsidiaries, or joint ventures, located within or operating from any countries or territories are subject to Comprehensive Sanctions. 0 – False, 1 – True';
COMMENT ON COLUMN sanctions_questionnaire.involved_sanctions IS 'Whether the entity, its subsidiaries, or joint ventures, are engaged in transactions, investments, business, or other dealings that directly or indirectly involve or benefit any countries or territories subject to Comprehensive Sanctions, or any person or entity which is the target of any Sanctions. 0 – False, 1 – True';
COMMENT ON COLUMN sanctions_questionnaire.questionnaire_date IS 'The date when the questionnaire was completed';
COMMENT ON COLUMN sanctions_questionnaire.date_created IS 'The date when the record was created';
COMMENT ON COLUMN sanctions_questionnaire.date_updated IS 'The date when the record was last updated';