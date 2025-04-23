package com.catalis.core.kycb.interfaces.enums.sanctions.v1;

/**
 * Enum representing the scope of the entity for which the sanctions questionnaire is answered.
 */
public enum EntitySanctionsQuestionnaireTypeEnum {
    /**
     * Only the legal entity itself.
     */
    LEGAL_ENTITY_ONLY,
    
    /**
     * The legal entity and all its subsidiaries held at 100%.
     */
    LEGAL_ENTITY_AND_WHOLLY_OWNED_SUBSIDIARIES,
    
    /**
     * The legal entity and all its subsidiaries held at 50% or more.
     */
    LEGAL_ENTITY_AND_MAJORITY_OWNED_SUBSIDIARIES,
    
    /**
     * The legal entity and a specific list of subsidiaries.
     */
    LEGAL_ENTITY_AND_SPECIFIC_SUBSIDIARIES
}