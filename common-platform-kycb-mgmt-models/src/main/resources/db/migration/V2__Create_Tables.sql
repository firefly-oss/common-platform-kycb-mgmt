-- V2__Create_Tables.sql

-- Table: kyc_verification
CREATE TABLE IF NOT EXISTS kyc_verification (
                                                kyc_verification_id BIGSERIAL PRIMARY KEY,
                                                party_id BIGINT NOT NULL,
                                                verification_status verification_status NOT NULL,
                                                verification_date TIMESTAMP,
                                                verification_method verification_method,
                                                verification_agent TEXT,
                                                rejection_reason TEXT,
                                                risk_score INTEGER,
                                                risk_level risk_level,
                                                enhanced_due_diligence BOOLEAN,
                                                next_review_date TIMESTAMP,
                                                date_created TIMESTAMP NOT NULL,
                                                date_updated TIMESTAMP
);

-- Table: verification_document
CREATE TABLE IF NOT EXISTS verification_document (
                                                     verification_document_id BIGSERIAL PRIMARY KEY,
                                                     kyc_verification_id BIGINT REFERENCES kyc_verification(kyc_verification_id),
    identity_document_id BIGINT,
    document_type document_type,
    verification_purpose verification_purpose,
    document_reference TEXT,
    document_system_id TEXT,
    is_verified BOOLEAN,
    verification_notes TEXT,
    expiry_date TIMESTAMP,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP
    );

-- Table: corporate_document
CREATE TABLE IF NOT EXISTS corporate_document (
                                                  corporate_document_id BIGSERIAL PRIMARY KEY,
                                                  legal_person_id BIGINT NOT NULL,
                                                  document_type corporate_document_type,
                                                  document_reference TEXT,
                                                  document_system_id TEXT,
                                                  notary_name TEXT,
                                                  notary_division_code TEXT,
                                                  commercial_registry TEXT,
                                                  registry_entry TEXT,
                                                  issue_date TIMESTAMP,
                                                  registry_date TIMESTAMP,
                                                  expiry_date TIMESTAMP,
                                                  is_verified BOOLEAN,
                                                  verification_notes TEXT,
                                                  verification_date TIMESTAMP,
                                                  verification_agent TEXT,
                                                  date_created TIMESTAMP NOT NULL,
                                                  date_updated TIMESTAMP
);

-- Table: power_of_attorney
CREATE TABLE IF NOT EXISTS power_of_attorney (
                                                 power_of_attorney_id BIGSERIAL PRIMARY KEY,
                                                 corporate_document_id BIGINT REFERENCES corporate_document(corporate_document_id),
    legal_person_id BIGINT NOT NULL,
    attorney_id BIGINT NOT NULL,
    power_type power_type,
    power_scope TEXT,
    joint_signature_required BOOLEAN,
    joint_signature_count INTEGER,
    joint_signature_notes TEXT,
    financial_limit NUMERIC,
    currency TEXT,
    effective_date TIMESTAMP,
    expiry_date TIMESTAMP,
    is_verified BOOLEAN,
    is_bastanteo_completed BOOLEAN,
    verification_method TEXT,
    verification_date TIMESTAMP,
    verifying_legal_counsel TEXT,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP
    );

-- Table: aml_screening
CREATE TABLE IF NOT EXISTS aml_screening (
                                             aml_screening_id BIGSERIAL PRIMARY KEY,
                                             party_id BIGINT NOT NULL,
                                             screening_date TIMESTAMP,
                                             screening_type screening_type,
                                             matches_found BOOLEAN,
                                             match_count INTEGER,
                                             screening_provider TEXT,
                                             reference_id TEXT,
                                             screening_result screening_result,
                                             next_screening_date TIMESTAMP,
                                             date_created TIMESTAMP NOT NULL,
                                             date_updated TIMESTAMP
);

-- Table: aml_match
CREATE TABLE IF NOT EXISTS aml_match (
                                         aml_match_id BIGSERIAL PRIMARY KEY,
                                         aml_screening_id BIGINT REFERENCES aml_screening(aml_screening_id),
    list_type list_type,
    list_source TEXT,
    matched_name TEXT,
    match_score NUMERIC,
    match_details TEXT,
    resolution_status resolution_status,
    resolution_notes TEXT,
    resolution_agent TEXT,
    resolution_date TIMESTAMP,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP
    );

-- Table: risk_assessment
CREATE TABLE IF NOT EXISTS risk_assessment (
                                               risk_assessment_id BIGSERIAL PRIMARY KEY,
                                               party_id BIGINT NOT NULL,
                                               assessment_type assessment_type,
                                               assessment_date TIMESTAMP,
                                               risk_category risk_category,
                                               risk_score INTEGER,
                                               risk_level risk_level,
                                               risk_factors TEXT,
                                               assessment_notes TEXT,
                                               assessment_agent TEXT,
                                               next_assessment_date TIMESTAMP,
                                               date_created TIMESTAMP NOT NULL,
                                               date_updated TIMESTAMP
);

-- Table: ubo
CREATE TABLE IF NOT EXISTS ubo (
                                   ubo_id BIGSERIAL PRIMARY KEY,
                                   legal_person_id BIGINT NOT NULL,
                                   natural_person_id BIGINT NOT NULL,
                                   ownership_percentage NUMERIC,
                                   ownership_type ownership_type,
                                   control_structure TEXT,
                                   is_verified BOOLEAN,
                                   verification_method TEXT,
                                   titularidad_real_document TEXT,
                                   verification_date TIMESTAMP,
                                   start_date TIMESTAMP,
                                   end_date TIMESTAMP,
                                   date_created TIMESTAMP NOT NULL,
                                   date_updated TIMESTAMP
);

-- Table: corporate_structure
CREATE TABLE IF NOT EXISTS corporate_structure (
                                                   corporate_structure_id BIGSERIAL PRIMARY KEY,
                                                   legal_person_id BIGINT NOT NULL,
                                                   parent_entity_id BIGINT NOT NULL,
                                                   ownership_percentage NUMERIC,
                                                   relationship_type relationship_type,
                                                   control_notes TEXT,
                                                   is_verified BOOLEAN,
                                                   verification_date TIMESTAMP,
                                                   start_date TIMESTAMP,
                                                   end_date TIMESTAMP,
                                                   date_created TIMESTAMP NOT NULL,
                                                   date_updated TIMESTAMP
);

-- Table: source_of_funds
CREATE TABLE IF NOT EXISTS source_of_funds (
                                               source_of_funds_id BIGSERIAL PRIMARY KEY,
                                               party_id BIGINT NOT NULL,
                                               source_type source_type,
                                               source_description TEXT,
                                               estimated_annual_amount NUMERIC,
                                               currency TEXT,
                                               is_verified BOOLEAN,
                                               verification_method TEXT,
                                               supporting_documents TEXT,
                                               verification_date TIMESTAMP,
                                               date_created TIMESTAMP NOT NULL,
                                               date_updated TIMESTAMP
);

-- Table: compliance_case
CREATE TABLE IF NOT EXISTS compliance_case (
                                               compliance_case_id BIGSERIAL PRIMARY KEY,
                                               party_id BIGINT NOT NULL,
                                               case_type case_type,
                                               case_status case_status,
                                               case_priority case_priority,
                                               case_reference TEXT,
                                               case_summary TEXT,
                                               assigned_to TEXT,
                                               due_date TIMESTAMP,
                                               resolution_date TIMESTAMP,
                                               resolution_notes TEXT,
                                               report_to_sepblac_required BOOLEAN,
                                               date_created TIMESTAMP NOT NULL,
                                               date_updated TIMESTAMP
);

-- Table: compliance_action
CREATE TABLE IF NOT EXISTS compliance_action (
                                                 compliance_action_id BIGSERIAL PRIMARY KEY,
                                                 compliance_case_id BIGINT REFERENCES compliance_case(compliance_case_id),
    action_type action_type,
    action_status action_status,
    action_description TEXT,
    action_agent TEXT,
    due_date TIMESTAMP,
    completion_date TIMESTAMP,
    result TEXT,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP
    );

-- Table: regulatory_reporting
CREATE TABLE IF NOT EXISTS regulatory_reporting (
                                                    report_id BIGSERIAL PRIMARY KEY,
                                                    compliance_case_id BIGINT REFERENCES compliance_case(compliance_case_id),
    report_type report_type,
    report_reference TEXT,
    regulatory_authority TEXT,
    report_status report_status,
    submission_date TIMESTAMP,
    submitting_agent TEXT,
    acknowledgment_date TIMESTAMP,
    report_content_summary TEXT,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP
    );

-- Table: enhanced_due_diligence
CREATE TABLE IF NOT EXISTS enhanced_due_diligence (
                                                      edd_id BIGSERIAL PRIMARY KEY,
                                                      kyc_verification_id BIGINT REFERENCES kyc_verification(kyc_verification_id),
    edd_reason edd_reason,
    edd_status edd_status,
    edd_description TEXT,
    approving_authority TEXT,
    approval_date TIMESTAMP,
    edd_notes TEXT,
    internal_committee_approval BOOLEAN,
    committee_approval_date TIMESTAMP,
    completion_date TIMESTAMP,
    completed_by TEXT,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP
    );

-- Table: kyb_verification
CREATE TABLE IF NOT EXISTS kyb_verification (
                                                kyb_verification_id BIGSERIAL PRIMARY KEY,
                                                legal_person_id BIGINT NOT NULL,
                                                verification_status verification_status,
                                                verification_date TIMESTAMP,
                                                mercantile_registry_verified BOOLEAN,
                                                deed_of_incorporation_verified BOOLEAN,
                                                business_structure_verified BOOLEAN,
                                                ubo_verified BOOLEAN,
                                                tax_id_verified BOOLEAN,
                                                operating_license_verified BOOLEAN,
                                                verification_notes TEXT,
                                                risk_score INTEGER,
                                                risk_level risk_level,
                                                next_review_date TIMESTAMP,
                                                date_created TIMESTAMP NOT NULL,
                                                date_updated TIMESTAMP
);

-- Table: business_profile
CREATE TABLE IF NOT EXISTS business_profile (
                                                business_profile_id BIGSERIAL PRIMARY KEY,
                                                legal_person_id BIGINT NOT NULL,
                                                legal_form_code TEXT,
                                                business_description TEXT,
                                                website_url TEXT,
                                                incorporation_year INTEGER,
                                                employee_count INTEGER,
                                                employee_range_code TEXT,
                                                annual_revenue NUMERIC,
                                                currency_iso_code TEXT,
                                                revenue_range_code TEXT,
                                                stock_exchange TEXT,
                                                stock_symbol TEXT,
                                                is_regulated BOOLEAN,
                                                regulatory_authority TEXT,
                                                company_status_code TEXT,
                                                company_size_code TEXT,
                                                is_public_entity BOOLEAN,
                                                registration_number TEXT,
                                                date_created TIMESTAMP NOT NULL,
                                                date_updated TIMESTAMP
);

-- Table: economic_activity
CREATE TABLE IF NOT EXISTS economic_activity (
                                                 economic_activity_id BIGSERIAL PRIMARY KEY,
                                                 legal_person_id BIGINT NOT NULL,
                                                 activity_code TEXT,
                                                 is_primary BOOLEAN,
                                                 sector_code TEXT,
                                                 subsector TEXT,
                                                 high_risk_activity BOOLEAN,
                                                 activity_details TEXT,
                                                 geographic_scope_code TEXT,
                                                 export_markets TEXT,
                                                 import_markets TEXT,
                                                 regulated_activity BOOLEAN,
                                                 regulatory_details TEXT,
                                                 date_created TIMESTAMP NOT NULL,
                                                 date_updated TIMESTAMP
);

-- Table: business_location
CREATE TABLE IF NOT EXISTS business_location (
                                                 business_location_id BIGSERIAL PRIMARY KEY,
                                                 legal_person_id BIGINT NOT NULL,
                                                 location_type_code TEXT,
                                                 is_primary BOOLEAN,
                                                 address_line_1 TEXT,
                                                 address_line_2 TEXT,
                                                 postal_code TEXT,
                                                 city TEXT,
                                                 division_code TEXT,
                                                 country_iso_code TEXT,
                                                 phone_number TEXT,
                                                 email TEXT,
                                                 employee_count INTEGER,
                                                 activities_conducted TEXT,
                                                 is_verified BOOLEAN,
                                                 verification_method TEXT,
                                                 verification_date TIMESTAMP,
                                                 date_created TIMESTAMP NOT NULL,
                                                 date_updated TIMESTAMP
);

-- Table: expected_activity
CREATE TABLE IF NOT EXISTS expected_activity (
                                                 expected_activity_id BIGSERIAL PRIMARY KEY,
                                                 party_id BIGINT NOT NULL,
                                                 activity_type_code TEXT,
                                                 expected_monthly_volume NUMERIC,
                                                 expected_annual_volume NUMERIC,
                                                 expected_transaction_count INTEGER,
                                                 currency_iso_code TEXT,
                                                 transaction_freq_code TEXT,
                                                 anticipated_countries TEXT,
                                                 is_high_value BOOLEAN,
                                                 high_value_details TEXT,
                                                 expected_counterparties TEXT,
                                                 cash_intensive BOOLEAN,
                                                 tax_haven_transactions BOOLEAN,
                                                 declaration_model_code TEXT,
                                                 declaration_details TEXT,
                                                 verification_notes TEXT,
                                                 date_created TIMESTAMP NOT NULL,
                                                 date_updated TIMESTAMP
);

-- Table: industry_risk
CREATE TABLE IF NOT EXISTS industry_risk (
                                             industry_risk_id BIGSERIAL PRIMARY KEY,
                                             activity_code TEXT,
                                             industry_name TEXT,
                                             inherent_risk_level risk_level,
                                             risk_score INTEGER,
                                             risk_factors TEXT,
                                             mitigating_factors TEXT,
                                             sepblac_high_risk BOOLEAN,
                                             eu_high_risk BOOLEAN,
                                             fatf_high_risk BOOLEAN,
                                             cash_intensive BOOLEAN,
                                             complex_structures BOOLEAN,
                                             assessment_date TIMESTAMP,
                                             assessed_by TEXT,
                                             next_assessment_date TIMESTAMP,
                                             requires_edd BOOLEAN,
                                             date_created TIMESTAMP NOT NULL,
                                             date_updated TIMESTAMP
);
