-- V1__Create_Enums.sql

-- ================================
-- Enum: verification_status
-- ================================
CREATE TYPE verification_status AS ENUM ('PENDING', 'IN_PROGRESS', 'VERIFIED', 'REJECTED');

-- ================================
-- Enum: verification_method
-- ================================
CREATE TYPE verification_method AS ENUM ('MANUAL', 'AUTOMATED', 'HYBRID');

-- ================================
-- Enum: risk_level
-- ================================
CREATE TYPE risk_level AS ENUM ('LOW', 'MEDIUM', 'HIGH', 'EXTREME');

-- ================================
-- Enum: document_type
-- ================================
CREATE TYPE document_type AS ENUM (
    'DNI', 'NIE', 'PASSPORT', 'UTILITY_BILL', 'BANK_STATEMENT',
    'DEED_OF_INCORPORATION', 'POWER_OF_ATTORNEY', 'BOARD_RESOLUTION', 'BYLAW', 'TAX_ID'
);

-- ================================
-- Enum: corporate_document_type
-- ================================
CREATE TYPE corporate_document_type AS ENUM (
    'DEED_OF_INCORPORATION', 'BYLAWS', 'POWER_OF_ATTORNEY', 'BOARD_RESOLUTION', 'TAX_ID'
);

-- ================================
-- Enum: verification_document_type
-- ================================
CREATE TYPE verification_document_type AS ENUM (
    'DNI', 'PASSPORT', 'DRIVER_LICENSE', 'UTILITY_BILL', 'BANK_STATEMENT'
);

-- ================================
-- Enum: verification_purpose
-- ================================
CREATE TYPE verification_purpose AS ENUM (
    'IDENTITY', 'ADDRESS', 'INCOME', 'SOURCE_OF_FUNDS'
);

-- ================================
-- Enum: power_type
-- ================================
CREATE TYPE power_type AS ENUM ('GENERAL', 'LIMITED', 'SPECIAL', 'TRADING');

-- ================================
-- Enum: screening_type
-- ================================
CREATE TYPE screening_type AS ENUM ('INITIAL', 'PERIODIC', 'EVENT_DRIVEN');

-- ================================
-- Enum: screening_result
-- ================================
CREATE TYPE screening_result AS ENUM ('CLEAR', 'REVIEW_REQUIRED', 'POSITIVE_HIT');

-- ================================
-- Enum: list_type
-- ================================
CREATE TYPE list_type AS ENUM ('SANCTIONS', 'PEP', 'ADVERSE_MEDIA', 'WATCHLIST');

-- ================================
-- Enum: resolution_status
-- ================================
CREATE TYPE resolution_status AS ENUM ('PENDING', 'FALSE_POSITIVE', 'CONFIRMED_HIT');

-- ================================
-- Enum: assessment_type
-- ================================
CREATE TYPE assessment_type AS ENUM ('INITIAL', 'PERIODIC', 'EVENT_DRIVEN');

-- ================================
-- Enum: risk_category
-- ================================
CREATE TYPE risk_category AS ENUM ('CUSTOMER', 'GEOGRAPHY', 'PRODUCT', 'CHANNEL');

-- ================================
-- Enum: ownership_type
-- ================================
CREATE TYPE ownership_type AS ENUM ('DIRECT', 'INDIRECT');

-- ================================
-- Enum: relationship_type
-- ================================
CREATE TYPE relationship_type AS ENUM ('SUBSIDIARY', 'BRANCH', 'AFFILIATE', 'JOINT_VENTURE');

-- ================================
-- Enum: source_type
-- ================================
CREATE TYPE source_type AS ENUM (
    'SALARY', 'BUSINESS_INCOME', 'INHERITANCE', 'INVESTMENT'
);

-- ================================
-- Enum: case_type
-- ================================
CREATE TYPE case_type AS ENUM ('KYC_REVIEW', 'AML_ALERT', 'SUSPICIOUS_ACTIVITY');

-- ================================
-- Enum: case_status
-- ================================
CREATE TYPE case_status AS ENUM ('OPEN', 'IN_REVIEW', 'ESCALATED', 'CLOSED');

-- ================================
-- Enum: case_priority
-- ================================
CREATE TYPE case_priority AS ENUM ('LOW', 'MEDIUM', 'HIGH', 'CRITICAL');

-- ================================
-- Enum: action_type
-- ================================
CREATE TYPE action_type AS ENUM ('DOCUMENT_REQUEST', 'CUSTOMER_CONTACT', 'ESCALATION');

-- ================================
-- Enum: action_status
-- ================================
CREATE TYPE action_status AS ENUM ('PENDING', 'IN_PROGRESS', 'COMPLETED', 'FAILED');

-- ================================
-- Enum: report_type
-- ================================
CREATE TYPE report_type AS ENUM (
    'COMUNICACION_SEPBLAC', 'COMUNICACION_COMISION', 'RESPUESTA_REQUERIMIENTO'
);

-- ================================
-- Enum: report_status
-- ================================
CREATE TYPE report_status AS ENUM ('DRAFT', 'SUBMITTED', 'ACKNOWLEDGED', 'SUPPLEMENTED');

-- ================================
-- Enum: edd_reason
-- ================================
CREATE TYPE edd_reason AS ENUM ('HIGH_RISK', 'PEP', 'SANCTIONS', 'COMPLEX_STRUCTURE');

-- ================================
-- Enum: edd_status
-- ================================
CREATE TYPE edd_status AS ENUM ('PENDING', 'IN_PROGRESS', 'COMPLETED', 'WAIVED');
