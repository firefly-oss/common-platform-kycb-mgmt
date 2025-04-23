# KYC Process Documentation

This document provides a detailed walkthrough of the Know Your Customer (KYC) process for natural persons using the Firefly KYC/B & AML Management microservice.

## Table of Contents

- [Overview](#overview)
- [Process Flow](#process-flow)
- [Step 1: Create a KYC Verification](#step-1-create-a-kyc-verification)
- [Step 2: Upload and Verify Identity Documents](#step-2-upload-and-verify-identity-documents)
- [Step 3: Perform AML Screening](#step-3-perform-aml-screening)
- [Step 4: Conduct Risk Assessment](#step-4-conduct-risk-assessment)
- [Step 5: Apply Enhanced Due Diligence (if necessary)](#step-5-apply-enhanced-due-diligence-if-necessary)
- [Step 6: Complete the KYC Verification](#step-6-complete-the-kyc-verification)
- [Error Handling](#error-handling)
- [Best Practices](#best-practices)

## Overview

The Know Your Customer (KYC) process is a mandatory procedure for financial institutions and regulated businesses to verify the identity of their clients. This process helps prevent identity theft, financial fraud, money laundering, and terrorist financing.

The Firefly KYC/B & AML Management microservice provides a comprehensive solution for implementing KYC processes in compliance with regulatory requirements.

## Process Flow

The KYC process for natural persons follows these steps:

1. Create a KYC Verification
2. Upload and Verify Identity Documents
3. Perform AML Screening
4. Conduct Risk Assessment
5. Apply Enhanced Due Diligence (if necessary)
6. Complete the KYC Verification

Each step is detailed below with code examples.

## Step 1: Create a KYC Verification

The first step is to create a KYC verification record for the individual. This establishes the verification process and tracks its progress.

### API Endpoint

```
POST /api/v1/identity/parties/{partyId}/kyc
```

### Request Example

```java
// Create a new KYC verification
KycVerificationDTO verification = new KycVerificationDTO();
verification.setPartyId(123L);
verification.setVerificationType(VerificationTypeEnum.IDENTITY);
verification.setStatus(VerificationStatusEnum.PENDING);
verification.setRiskLevel(RiskLevelEnum.MEDIUM);
verification.setVerificationMethod(VerificationMethodEnum.DOCUMENT);

webClient.post()
    .uri("/api/v1/identity/parties/123/kyc")
    .bodyValue(verification)
    .retrieve()
    .bodyToMono(KycVerificationDTO.class)
    .subscribe(response -> {
        System.out.println("Verification created with ID: " + response.getKycVerificationId());
    });
```

### Response Example

```json
{
  "kycVerificationId": 456,
  "partyId": 123,
  "verificationType": "IDENTITY",
  "status": "PENDING",
  "riskLevel": "MEDIUM",
  "verificationMethod": "DOCUMENT",
  "createdDate": "2023-06-15T10:30:45Z",
  "lastModifiedDate": "2023-06-15T10:30:45Z"
}
```

## Step 2: Upload and Verify Identity Documents

After creating the KYC verification, the next step is to upload and verify identity documents such as passports, national ID cards, or driver's licenses.

### API Endpoint

```
POST /api/v1/identity/parties/{partyId}/kyc/{kycVerificationId}/documents
```

### Request Example

```java
// Add a document to a KYC verification
VerificationDocumentDTO document = new VerificationDocumentDTO();
document.setDocumentType(DocumentTypeEnum.PASSPORT);
document.setDocumentNumber("AB123456");
document.setIssuingCountry("US");
document.setIssueDate(LocalDate.now().minusYears(2));
document.setExpiryDate(LocalDate.now().plusYears(8));
document.setDocumentUrl("https://document-storage.example.com/docs/passport-123.pdf");

webClient.post()
    .uri("/api/v1/identity/parties/123/kyc/456/documents")
    .bodyValue(document)
    .retrieve()
    .bodyToMono(VerificationDocumentDTO.class)
    .subscribe(response -> {
        System.out.println("Document added with ID: " + response.getVerificationDocumentId());
    });
```

### Response Example

```json
{
  "verificationDocumentId": 789,
  "kycVerificationId": 456,
  "documentType": "PASSPORT",
  "documentNumber": "AB123456",
  "issuingCountry": "US",
  "issueDate": "2021-06-15",
  "expiryDate": "2031-06-15",
  "documentUrl": "https://document-storage.example.com/docs/passport-123.pdf",
  "isVerified": false,
  "createdDate": "2023-06-15T10:35:22Z",
  "lastModifiedDate": "2023-06-15T10:35:22Z"
}
```

### Verify Document

After uploading the document, it needs to be verified:

```java
// Verify the document
VerificationDocumentDTO verifyDoc = new VerificationDocumentDTO();
verifyDoc.setIsVerified(true);
verifyDoc.setVerificationDate(LocalDateTime.now());
verifyDoc.setVerifiedBy("compliance-officer-1");
verifyDoc.setVerificationNotes("Document appears authentic and matches provided information");

webClient.patch()
    .uri("/api/v1/identity/parties/123/kyc/456/documents/789")
    .bodyValue(verifyDoc)
    .retrieve()
    .bodyToMono(VerificationDocumentDTO.class)
    .subscribe(response -> {
        System.out.println("Document verified: " + response.getIsVerified());
    });
```

## Step 3: Perform AML Screening

Once the identity documents are verified, the individual should be screened against Anti-Money Laundering (AML) watchlists, including sanctions lists, politically exposed persons (PEPs) lists, and adverse media.

### API Endpoint

```
POST /api/v1/compliance/parties/{partyId}/aml-screenings
```

### Request Example

```java
// Perform AML screening
AmlScreeningDTO screening = new AmlScreeningDTO();
screening.setPartyId(123L);
screening.setScreeningType(ScreeningTypeEnum.SANCTIONS);
screening.setScreeningSource("OFAC");
screening.setScreeningDate(LocalDateTime.now());

webClient.post()
    .uri("/api/v1/compliance/parties/123/aml-screenings")
    .bodyValue(screening)
    .retrieve()
    .bodyToMono(AmlScreeningDTO.class)
    .subscribe(response -> {
        System.out.println("Screening completed with ID: " + response.getAmlScreeningId());
        System.out.println("Status: " + response.getScreeningStatus());
    });
```

### Response Example

```json
{
  "amlScreeningId": 321,
  "partyId": 123,
  "screeningType": "SANCTIONS",
  "screeningSource": "OFAC",
  "screeningDate": "2023-06-15T10:40:15Z",
  "screeningStatus": "COMPLETED",
  "matchCount": 0,
  "createdDate": "2023-06-15T10:40:15Z",
  "lastModifiedDate": "2023-06-15T10:40:15Z"
}
```

### Handle Potential Matches

If matches are found, they need to be reviewed and resolved:

```java
// Get all matches for a screening
webClient.get()
    .uri("/api/v1/compliance/parties/123/aml-screenings/321/matches")
    .retrieve()
    .bodyToMono(new ParameterizedTypeReference<PaginationResponse<AmlMatchDTO>>() {})
    .subscribe(response -> {
        response.getContent().forEach(match -> {
            System.out.println("Match ID: " + match.getAmlMatchId());
            System.out.println("Match type: " + match.getMatchType());
            System.out.println("Match score: " + match.getMatchScore());

            // Resolve a match
            AmlMatchDTO updateMatch = new AmlMatchDTO();
            updateMatch.setAmlMatchId(match.getAmlMatchId());
            updateMatch.setResolutionStatus(ResolutionStatusEnum.FALSE_POSITIVE);
            updateMatch.setResolutionNotes("Verified not a match due to different date of birth");
            updateMatch.setResolvedBy("compliance-officer-1");

            webClient.patch()
                .uri("/api/v1/compliance/parties/123/aml-screenings/321/matches/" + match.getAmlMatchId())
                .bodyValue(updateMatch)
                .retrieve()
                .bodyToMono(AmlMatchDTO.class)
                .subscribe(updatedMatch -> {
                    System.out.println("Match resolved: " + updatedMatch.getResolutionStatus());
                });
        });
    });
```

## Step 4: Conduct Risk Assessment

After AML screening, a risk assessment should be performed to determine the risk level of the individual.

### API Endpoint

```
POST /api/v1/compliance/parties/{partyId}/risk-assessments
```

### Request Example

```java
// Perform risk assessment
RiskAssessmentDTO assessment = new RiskAssessmentDTO();
assessment.setPartyId(123L);
assessment.setRiskFactors(Arrays.asList("COUNTRY_RISK", "INDUSTRY_RISK", "TRANSACTION_VOLUME"));
assessment.setAssessmentType(AssessmentTypeEnum.INITIAL);
assessment.setAssessmentDate(LocalDateTime.now());

webClient.post()
    .uri("/api/v1/compliance/parties/123/risk-assessments")
    .bodyValue(assessment)
    .retrieve()
    .bodyToMono(RiskAssessmentDTO.class)
    .subscribe(response -> {
        System.out.println("Risk assessment ID: " + response.getRiskAssessmentId());
        System.out.println("Risk level: " + response.getRiskLevel());
        System.out.println("Risk score: " + response.getRiskScore());
    });
```

### Response Example

```json
{
  "riskAssessmentId": 654,
  "partyId": 123,
  "assessmentType": "INITIAL",
  "assessmentDate": "2023-06-15T10:45:30Z",
  "riskLevel": "MEDIUM",
  "riskScore": 65,
  "riskFactors": ["COUNTRY_RISK", "INDUSTRY_RISK", "TRANSACTION_VOLUME"],
  "createdDate": "2023-06-15T10:45:30Z",
  "lastModifiedDate": "2023-06-15T10:45:30Z"
}
```

## Step 5: Apply Enhanced Due Diligence (if necessary)

If the risk assessment identifies the individual as high-risk, Enhanced Due Diligence (EDD) should be applied.

### API Endpoint

```
POST /api/v1/identity/parties/{partyId}/kyc/{kycVerificationId}/edd
```

### Request Example

```java
// If high risk, trigger Enhanced Due Diligence
if (riskLevel == RiskLevelEnum.HIGH) {
    EnhancedDueDiligenceDTO edd = new EnhancedDueDiligenceDTO();
    edd.setKycVerificationId(456L);
    edd.setEddType(EddTypeEnum.HIGH_RISK_CUSTOMER);
    edd.setEddStatus(EddStatusEnum.PENDING);
    edd.setAssignedTo("senior-compliance-officer");

    webClient.post()
        .uri("/api/v1/identity/parties/123/kyc/456/edd")
        .bodyValue(edd)
        .retrieve()
        .bodyToMono(EnhancedDueDiligenceDTO.class)
        .subscribe(eddResponse -> {
            System.out.println("EDD process initiated: " + eddResponse.getEddId());
        });
}
```

### Response Example

```json
{
  "eddId": 987,
  "kycVerificationId": 456,
  "eddType": "HIGH_RISK_CUSTOMER",
  "eddStatus": "PENDING",
  "assignedTo": "senior-compliance-officer",
  "createdDate": "2023-06-15T10:50:45Z",
  "lastModifiedDate": "2023-06-15T10:50:45Z"
}
```

### Complete EDD Process

Once the EDD process is completed:

```java
// Complete EDD process
EnhancedDueDiligenceDTO completeEdd = new EnhancedDueDiligenceDTO();
completeEdd.setEddStatus(EddStatusEnum.COMPLETED);
completeEdd.setCompletionDate(LocalDateTime.now());
completeEdd.setCompletedBy("senior-compliance-officer");
completeEdd.setNotes("Additional verification completed. Source of funds verified through bank statements.");

webClient.patch()
    .uri("/api/v1/identity/parties/123/kyc/456/edd/987")
    .bodyValue(completeEdd)
    .retrieve()
    .bodyToMono(EnhancedDueDiligenceDTO.class)
    .subscribe(response -> {
        System.out.println("EDD completed: " + response.getEddStatus());
    });
```

## Step 6: Complete the KYC Verification

After all the previous steps are completed, the KYC verification can be finalized.

### API Endpoint

```
PATCH /api/v1/identity/parties/{partyId}/kyc/{kycVerificationId}
```

### Request Example

```java
// Update a KYC verification status
KycVerificationDTO updateDto = new KycVerificationDTO();
updateDto.setStatus(VerificationStatusEnum.COMPLETED);
updateDto.setVerificationDate(LocalDateTime.now());
updateDto.setVerifiedBy("compliance-officer-1");
updateDto.setNotes("All documents verified successfully. AML screening completed with no matches. Risk assessment completed.");

webClient.patch()
    .uri("/api/v1/identity/parties/123/kyc/456")
    .bodyValue(updateDto)
    .retrieve()
    .bodyToMono(KycVerificationDTO.class)
    .subscribe(response -> {
        System.out.println("Verification updated: " + response.getStatus());
    });
```

### Response Example

```json
{
  "kycVerificationId": 456,
  "partyId": 123,
  "verificationType": "IDENTITY",
  "status": "COMPLETED",
  "riskLevel": "MEDIUM",
  "verificationMethod": "DOCUMENT",
  "verificationDate": "2023-06-15T11:00:00Z",
  "verifiedBy": "compliance-officer-1",
  "notes": "All documents verified successfully. AML screening completed with no matches. Risk assessment completed.",
  "createdDate": "2023-06-15T10:30:45Z",
  "lastModifiedDate": "2023-06-15T11:00:00Z"
}
```

## Error Handling

During the KYC process, various errors may occur. Here are some common errors and how to handle them:

### Document Verification Failures

If a document fails verification:

```java
// Handle document verification failure
VerificationDocumentDTO failedDoc = new VerificationDocumentDTO();
failedDoc.setIsVerified(false);
failedDoc.setVerificationDate(LocalDateTime.now());
failedDoc.setVerifiedBy("compliance-officer-1");
failedDoc.setVerificationNotes("Document appears altered. Request a new document.");

webClient.patch()
    .uri("/api/v1/identity/parties/123/kyc/456/documents/789")
    .bodyValue(failedDoc)
    .retrieve()
    .bodyToMono(VerificationDocumentDTO.class)
    .subscribe(response -> {
        System.out.println("Document verification failed: " + response.getVerificationNotes());
        
        // Notify the customer
        // Code to send notification...
    });
```

### AML Screening Matches

If AML screening finds true matches:

```java
// Handle true AML match
AmlMatchDTO trueMatch = new AmlMatchDTO();
trueMatch.setAmlMatchId(match.getAmlMatchId());
trueMatch.setResolutionStatus(ResolutionStatusEnum.TRUE_MATCH);
trueMatch.setResolutionNotes("Confirmed match with OFAC sanctions list");
trueMatch.setResolvedBy("compliance-officer-1");

webClient.patch()
    .uri("/api/v1/compliance/parties/123/aml-screenings/321/matches/" + match.getAmlMatchId())
    .bodyValue(trueMatch)
    .retrieve()
    .bodyToMono(AmlMatchDTO.class)
    .subscribe(updatedMatch -> {
        System.out.println("True match confirmed: " + updatedMatch.getResolutionStatus());
        
        // Create a compliance case
        ComplianceCaseDTO complianceCase = new ComplianceCaseDTO();
        complianceCase.setPartyId(123L);
        complianceCase.setCaseType(CaseTypeEnum.SANCTIONS_MATCH);
        complianceCase.setCasePriority(CasePriorityEnum.HIGH);
        complianceCase.setCaseStatus(CaseStatusEnum.OPEN);
        complianceCase.setAssignedTo("compliance-team-lead");
        complianceCase.setDescription("True match found in OFAC sanctions list");
        
        webClient.post()
            .uri("/api/v1/compliance/cases")
            .bodyValue(complianceCase)
            .retrieve()
            .bodyToMono(ComplianceCaseDTO.class)
            .subscribe(caseResponse -> {
                System.out.println("Compliance case created: " + caseResponse.getComplianceCaseId());
            });
    });
```

## Best Practices

1. **Document Everything**: Maintain detailed records of all verification steps, decisions, and the reasoning behind them.

2. **Four-Eyes Principle**: For high-risk customers, implement a review process where at least two compliance officers verify the information.

3. **Regular Updates**: Re-verify customer information periodically, especially for high-risk customers.

4. **Risk-Based Approach**: Apply more stringent verification for high-risk customers and less stringent for low-risk ones.

5. **Automation**: Automate routine checks while maintaining human oversight for complex cases.

6. **Continuous Monitoring**: Implement ongoing monitoring for changes in customer risk profiles.

7. **Secure Storage**: Ensure all customer data and documents are stored securely and in compliance with data protection regulations.

8. **Audit Trail**: Maintain a comprehensive audit trail of all actions taken during the KYC process.

9. **Training**: Ensure all staff involved in the KYC process are properly trained and updated on the latest regulations.

10. **Feedback Loop**: Continuously improve the KYC process based on feedback and lessons learned.