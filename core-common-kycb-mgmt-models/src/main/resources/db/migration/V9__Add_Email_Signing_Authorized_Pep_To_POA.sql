-- V9__Add_Email_Signing_Authorized_Pep_To_POA.sql
-- Adds the signer's email, signing-authorized flag and quick-capture PEP flag
-- to the power_of_attorney table. The is_pep column here is a fast-triage flag
-- captured at signer registration; the canonical PEP record lives in
-- core-common-customer-mgmt.PoliticallyExposedPerson.

ALTER TABLE power_of_attorney
    ADD COLUMN email VARCHAR(255),
    ADD COLUMN signing_authorized BOOLEAN,
    ADD COLUMN is_pep BOOLEAN;

COMMENT ON COLUMN power_of_attorney.email IS 'Contact email of the attorney/signer captured at registration';
COMMENT ON COLUMN power_of_attorney.signing_authorized IS 'Indicates whether this attorney is authorized to sign on behalf of the entity';
COMMENT ON COLUMN power_of_attorney.is_pep IS 'Quick-capture PEP flag at signer registration. Canonical PEP record lives in core-common-customer-mgmt.PoliticallyExposedPerson; this flag is for fast triage during onboarding.';
