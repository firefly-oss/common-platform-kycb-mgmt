# Firefly - KYC/B & AML Management
A comprehensive microservice for managing Know Your Customer (KYC), Know Your Business (KYB), and Anti-Money Laundering (AML) processes in financial institutions.

## Table of Contents

- [Overview](#overview)
- [Quickstart](#quickstart)
- [Key Features](#key-features)
- [Architecture](#architecture)
- [API Documentation](#api-documentation)
- [Setup and Installation](#setup-and-installation)
- [Configuration](#configuration)
- [Usage Examples](#usage-examples)
- [Troubleshooting](#troubleshooting)
- [Development Guidelines](#development-guidelines)
- [Contributing](#contributing)

## Overview

The Firefly KYC/B & AML Management microservice provides a robust, scalable, and secure platform for financial institutions and businesses to comply with regulatory requirements while streamlining customer and business onboarding. It enables organizations to:

- Verify the identity of individuals and businesses
- Conduct risk assessments and due diligence
- Screen against global watchlists for sanctions and PEPs
- Manage compliance cases and regulatory reporting
- Track and verify documents
- Maintain audit trails for regulatory compliance

Built with a reactive architecture using Spring WebFlux, this microservice is designed for high throughput and low latency, making it suitable for enterprise-scale deployments.

## Quickstart

Follow these steps to get the microservice up and running quickly:

1. **Clone the repository**
   ```bash
   git clone https://github.com/firefly-oss/common-platform-kycb-mgmt.git
   cd common-platform-kycb-mgmt
   ```

2. **Set up environment variables**
   ```bash
   # Create a .env file with the following variables
   echo "DB_HOST=localhost
   DB_PORT=5432
   DB_NAME=kycb_mgmt
   DB_USERNAME=postgres
   DB_PASSWORD=postgres
   DB_SSL_MODE=disable" > .env
   ```

3. **Build the application**
   ```bash
   mvn clean install
   ```

4. **Run with Docker**
   ```bash
   docker build -t firefly-kycb-mgmt .
   docker run -p 8080:8080 --env-file .env firefly-kycb-mgmt
   ```

5. **Access the API documentation**

   Open your browser and navigate to [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Key Features

### KYC Management
- **Individual Identity Verification**: Verify the identity of individuals through various methods
- **Document Validation**: Validate identity documents for authenticity
- **Risk Assessment**: Score individuals based on risk factors
- **Verification Status Tracking**: Track the status of verification processes
- **Workflow Management**: Support for both automated and manual verification workflows

### KYB Management
- **Business Identity Verification**: Verify the identity and legitimacy of businesses
- **Corporate Document Management**: Store and verify business registration documents
- **Business Structure Verification**: Validate corporate structures and relationships
- **UBO Verification**: Identify and verify Ultimate Beneficial Owners
- **Regulatory Compliance**: Verify tax IDs, operating licenses, and mercantile registry information

### AML Compliance
- **Global Watchlist Screening**: Screen against sanctions, PEP, and adverse media lists
- **Risk-Based Approach**: Apply different levels of due diligence based on risk
- **Match Management**: Review and resolve potential matches
- **Ongoing Monitoring**: Continuously monitor for changes in risk status

### Risk Management
- **Risk Assessment Framework**: Comprehensive risk scoring methodology
- **Risk Classification**: Categorize entities by risk level
- **Industry Risk Evaluation**: Assess risks specific to different industries
- **Periodic Reviews**: Schedule and manage periodic risk reassessments
- **Enhanced Due Diligence**: Special handling for high-risk entities

### Compliance
- **Case Management**: Create and manage compliance cases
- **Regulatory Reporting**: Generate reports for regulatory submissions
- **Audit Trail**: Maintain detailed records of all compliance activities
- **Action Tracking**: Track compliance actions and their outcomes
- **Case Prioritization**: Prioritize cases based on risk and urgency

### Document Management
- **Document Storage**: Secure storage for all verification documents
- **Document Classification**: Categorize documents by type and purpose
- **Verification Status**: Track the verification status of each document
- **Document Retrieval**: Quick access to stored documents

## Architecture

### Technology Stack
- **Framework**: Spring Boot with WebFlux for reactive programming
- **Database**: PostgreSQL with R2DBC for reactive database access
- **Migration**: Flyway for database schema management
- **Documentation**: OpenAPI/Swagger for API documentation
- **Monitoring**: Spring Actuator for health checks and metrics
- **Security**: JWT-based authentication and authorization
- **Containerization**: Docker for deployment

### Modular Structure
The application follows a clean, modular architecture divided into four main modules:

1. **common-platform-kycb-mgmt-interfaces**
   - Data Transfer Objects (DTOs) for API communication
   - Enumerations for standardized values
   - API contracts defining service interfaces

2. **common-platform-kycb-mgmt-models**
   - Entity definitions representing database tables
   - Repository interfaces for data access
   - Database migrations for schema evolution

3. **common-platform-kycb-mgmt-core**
   - Business logic implementation
   - Service interfaces and implementations
   - Mappers for entity-DTO conversion
   - Validation and business rules

4. **common-platform-kycb-mgmt-web**
   - REST controllers exposing API endpoints
   - Request/response handling
   - Input validation
   - Error handling
   - Application entry point

### Data Flow
1. Client sends a request to an API endpoint
2. Controller validates the request and converts it to DTOs
3. Service layer applies business logic
4. Repository layer interacts with the database
5. Response flows back through the service and controller layers
6. Client receives the response

## API Documentation

The API is documented using OpenAPI/Swagger, providing a comprehensive and interactive documentation of all endpoints.

### Accessing Documentation
When running the application, the documentation is available at:
- `/swagger-ui.html` - Interactive Swagger UI
- `/v3/api-docs` - OpenAPI JSON specification

### API Categories
The API follows RESTful principles and is organized into the following main categories:

1. **Identity Management**
   - KYC verification endpoints
   - KYB verification endpoints
   - Document verification

2. **Compliance**
   - AML screening
   - Risk assessment
   - Compliance case management
   - Regulatory reporting

3. **Document Management**
   - Document upload/download
   - Document verification
   - Document classification

4. **Corporate Structure**
   - Business structure management
   - UBO management
   - Ownership relationships

## Setup and Installation

### Prerequisites
- Java 17 or higher
- PostgreSQL 12 or higher
- Maven 3.6 or higher
- Docker (optional, for containerized deployment)

### Environment Variables
The following environment variables need to be configured:

| Variable | Description | Example |
|----------|-------------|---------|
| `DB_HOST` | Database host | localhost |
| `DB_PORT` | Database port | 5432 |
| `DB_NAME` | Database name | kycb_mgmt |
| `DB_USERNAME` | Database username | postgres |
| `DB_PASSWORD` | Database password | postgres |
| `DB_SSL_MODE` | Database SSL mode | disable |
| `SERVER_PORT` | Application port (optional) | 8080 |
| `LOGGING_LEVEL` | Logging level (optional) | INFO |

### Building the Application
```bash
# Build all modules
mvn clean install

# Skip tests
mvn clean install -DskipTests
```

### Running the Application
```bash
# Run with Java
java -jar common-platform-kycb-mgmt-web/target/common-platform-kycb-mgmt-web.jar

# Run with Maven
mvn spring-boot:run -pl common-platform-kycb-mgmt-web
```

### Docker Deployment
```bash
# Build Docker image
docker build -t firefly-kycb-mgmt .

# Run Docker container
docker run -p 8080:8080 --env-file .env firefly-kycb-mgmt

# Run with Docker Compose
docker-compose up -d
```

## Configuration

The application supports different profiles for various environments:

### Profiles
- `dev` - Development environment with detailed logging
- `testing` - Testing environment with API documentation enabled
- `prod` - Production environment with minimal logging and API documentation disabled

### Configuration Files
Configuration can be customized through:
- `application.yaml` - Main configuration file
- `application-{profile}.yaml` - Profile-specific configurations
- Environment variables - Override configuration at runtime

### Example Configuration
```yaml
spring:
  r2dbc:
    url: r2dbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
    properties:
      sslMode: ${DB_SSL_MODE}

  flyway:
    url: jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
    user: ${DB_USERNAME}
    password: ${DB_PASSWORD}

logging:
  level:
    root: INFO
    com.catalis: DEBUG

springdoc:
  api-docs:
    enabled: true
  swagger-ui:
    enabled: true
```

## Usage Examples

This section provides examples of how to interact with the API using WebClient from Spring WebFlux. All examples use reactive programming patterns.

### KYC Verification

#### Create a New KYC Verification

The following example shows how to create a new KYC verification for a party:

```
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

#### Retrieve KYC Verifications for a Party

To retrieve all KYC verifications for a specific party with pagination:

```
// Get all KYC verifications for a party
webClient.get()
    .uri(uriBuilder -> uriBuilder
        .path("/api/v1/identity/parties/123/kyc")
        .queryParam("page", 0)
        .queryParam("size", 10)
        .build())
    .retrieve()
    .bodyToMono(new ParameterizedTypeReference<PaginationResponse<KycVerificationDTO>>() {})
    .subscribe(response -> {
        System.out.println("Total verifications: " + response.getTotalElements());
        response.getContent().forEach(verification -> {
            System.out.println("Verification ID: " + verification.getKycVerificationId());
            System.out.println("Status: " + verification.getStatus());
        });
    });
```

#### Update KYC Verification Status

When a verification process is completed, you can update its status:

```
// Update a KYC verification status
KycVerificationDTO updateDto = new KycVerificationDTO();
updateDto.setStatus(VerificationStatusEnum.COMPLETED);
updateDto.setVerificationDate(LocalDateTime.now());
updateDto.setVerifiedBy("compliance-officer-1");
updateDto.setNotes("All documents verified successfully");

webClient.patch()
    .uri("/api/v1/identity/parties/123/kyc/456")
    .bodyValue(updateDto)
    .retrieve()
    .bodyToMono(KycVerificationDTO.class)
    .subscribe(response -> {
        System.out.println("Verification updated: " + response.getStatus());
    });
```

#### Add Document to KYC Verification

Documents can be added to a KYC verification for identity proof:

```
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

### AML Screening

#### Perform AML Screening

To screen a party against sanctions lists:

```
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

#### Retrieve AML Screening Results

To check the results of a previous screening:

```
// Get AML screening results
webClient.get()
    .uri("/api/v1/compliance/parties/123/aml-screenings/789")
    .retrieve()
    .bodyToMono(AmlScreeningDTO.class)
    .subscribe(screening -> {
        System.out.println("Screening ID: " + screening.getAmlScreeningId());
        System.out.println("Status: " + screening.getScreeningStatus());
        System.out.println("Matches found: " + screening.getMatchCount());
    });
```

#### Handle AML Matches

When matches are found, they need to be reviewed and resolved:

```
// Get all matches for a screening
webClient.get()
    .uri("/api/v1/compliance/parties/123/aml-screenings/789/matches")
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
                .uri("/api/v1/compliance/parties/123/aml-screenings/789/matches/" + match.getAmlMatchId())
                .bodyValue(updateMatch)
                .retrieve()
                .bodyToMono(AmlMatchDTO.class)
                .subscribe(updatedMatch -> {
                    System.out.println("Match resolved: " + updatedMatch.getResolutionStatus());
                });
        });
    });
```

### Risk Assessment

#### Perform Risk Assessment

To assess the risk level of a party:

```
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

#### Update Risk Assessment with Additional Factors

When new information is discovered, the risk assessment can be updated:

```
// Update risk assessment with additional factors
RiskAssessmentDTO updateAssessment = new RiskAssessmentDTO();
updateAssessment.setRiskFactors(Arrays.asList("COUNTRY_RISK", "INDUSTRY_RISK", "TRANSACTION_VOLUME", "PEP_ASSOCIATION"));
updateAssessment.setNotes("Updated due to newly discovered PEP association");

webClient.patch()
    .uri("/api/v1/compliance/parties/123/risk-assessments/321")
    .bodyValue(updateAssessment)
    .retrieve()
    .bodyToMono(RiskAssessmentDTO.class)
    .subscribe(response -> {
        System.out.println("Updated risk level: " + response.getRiskLevel());
        System.out.println("Updated risk score: " + response.getRiskScore());

        // If high risk, trigger Enhanced Due Diligence
        if (response.getRiskLevel() == RiskLevelEnum.HIGH) {
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
    });
```

### Corporate Structure and UBO Management

#### Register Ultimate Beneficial Owner

For KYB processes, registering UBOs is essential:

```
// Register a UBO for a corporate entity
UboDTO ubo = new UboDTO();
ubo.setPartyId(123L);
ubo.setIndividualPartyId(456L);
ubo.setOwnershipPercentage(new BigDecimal("25.5"));
ubo.setOwnershipType(OwnershipTypeEnum.DIRECT);
ubo.setIsControlling(true);
ubo.setStartDate(LocalDateTime.now());

webClient.post()
    .uri("/api/v1/corporate/parties/123/ubos")
    .bodyValue(ubo)
    .retrieve()
    .bodyToMono(UboDTO.class)
    .subscribe(response -> {
        System.out.println("UBO registered with ID: " + response.getUboId());
    });
```

#### Verify UBO Information

UBO information needs to be verified:

```
// Verify UBO information
UboDTO verifyUbo = new UboDTO();
verifyUbo.setIsVerified(true);
verifyUbo.setVerificationDate(LocalDateTime.now());
verifyUbo.setVerificationMethod(VerificationMethodEnum.DOCUMENT);
verifyUbo.setTitularidadRealDocument("https://document-storage.example.com/docs/ownership-proof-123.pdf");

webClient.patch()
    .uri("/api/v1/corporate/parties/123/ubos/789")
    .bodyValue(verifyUbo)
    .retrieve()
    .bodyToMono(UboDTO.class)
    .subscribe(response -> {
        System.out.println("UBO verified: " + response.getIsVerified());
    });
```

### Document Management

#### Upload Corporate Document

Corporate documents are essential for KYB verification:

```
// Upload a corporate document
CorporateDocumentDTO document = new CorporateDocumentDTO();
document.setPartyId(123L);
document.setDocumentType(CorporateDocumentTypeEnum.CERTIFICATE_OF_INCORPORATION);
document.setDocumentNumber("CORP-2023-12345");
document.setIssueDate(LocalDate.now().minusYears(5));
document.setIssuingAuthority("Delaware Secretary of State");
document.setDocumentUrl("https://document-storage.example.com/docs/certificate-123.pdf");

webClient.post()
    .uri("/api/v1/documents/corporate")
    .bodyValue(document)
    .retrieve()
    .bodyToMono(CorporateDocumentDTO.class)
    .subscribe(response -> {
        System.out.println("Document uploaded with ID: " + response.getCorporateDocumentId());
    });
```

#### Verify Document Authenticity

Documents need to be verified for authenticity:

```
// Verify document authenticity
CorporateDocumentDTO verifyDocument = new CorporateDocumentDTO();
verifyDocument.setIsVerified(true);
verifyDocument.setVerificationDate(LocalDateTime.now());
verifyDocument.setVerificationNotes("Document verified against official registry");
verifyDocument.setVerificationAgent("compliance-officer-2");

webClient.patch()
    .uri("/api/v1/documents/corporate/456")
    .bodyValue(verifyDocument)
    .retrieve()
    .bodyToMono(CorporateDocumentDTO.class)
    .subscribe(response -> {
        System.out.println("Document verified: " + response.getIsVerified());
    });
```

### Compliance Case Management

#### Create Compliance Case

When suspicious activity is detected, a compliance case is created:

```
// Create a compliance case
ComplianceCaseDTO complianceCase = new ComplianceCaseDTO();
complianceCase.setPartyId(123L);
complianceCase.setCaseType(CaseTypeEnum.SUSPICIOUS_ACTIVITY);
complianceCase.setCasePriority(CasePriorityEnum.HIGH);
complianceCase.setCaseStatus(CaseStatusEnum.OPEN);
complianceCase.setAssignedTo("compliance-team-lead");
complianceCase.setDescription("Unusual transaction pattern detected");

webClient.post()
    .uri("/api/v1/compliance/cases")
    .bodyValue(complianceCase)
    .retrieve()
    .bodyToMono(ComplianceCaseDTO.class)
    .subscribe(response -> {
        System.out.println("Case created with ID: " + response.getComplianceCaseId());
    });
```

#### Add Compliance Action

Actions are added to compliance cases to track investigation steps:

```
// Add action to compliance case
ComplianceActionDTO action = new ComplianceActionDTO();
action.setComplianceCaseId(789L);
action.setActionType(ActionTypeEnum.INVESTIGATION);
action.setActionStatus(ActionStatusEnum.IN_PROGRESS);
action.setAssignedTo("investigator-1");
action.setDueDate(LocalDateTime.now().plusDays(3));
action.setDescription("Review transaction history for the last 6 months");

webClient.post()
    .uri("/api/v1/compliance/cases/789/actions")
    .bodyValue(action)
    .retrieve()
    .bodyToMono(ComplianceActionDTO.class)
    .subscribe(response -> {
        System.out.println("Action added with ID: " + response.getComplianceActionId());
    });
```

#### Submit Regulatory Report

For regulatory compliance, reports may need to be submitted:

```
// Submit regulatory report
RegulatoryReportingDTO report = new RegulatoryReportingDTO();
report.setComplianceCaseId(789L);
report.setReportType(ReportTypeEnum.SAR);
report.setReportStatus(ReportStatusEnum.SUBMITTED);
report.setReportDate(LocalDateTime.now());
report.setSubmittedBy("compliance-officer-3");
report.setRegulatoryAuthority("FinCEN");
report.setReferenceNumber("SAR-2023-78901");

webClient.post()
    .uri("/api/v1/compliance/cases/789/reports")
    .bodyValue(report)
    .retrieve()
    .bodyToMono(RegulatoryReportingDTO.class)
    .subscribe(response -> {
        System.out.println("Report submitted with ID: " + response.getReportId());
    });
```

## Complete KYC/KYB Process Workflows

This section illustrates the complete process flows for performing KYC for natural persons and KYB for legal entities, including how legal entities must point to natural persons as directors, shareholders, and Ultimate Beneficial Owners (UBOs).

### KYC Process for Natural Persons

The Know Your Customer (KYC) process for natural persons typically follows these steps:

1. **Create a KYC Verification**
2. **Upload and Verify Identity Documents**
3. **Perform AML Screening**
4. **Conduct Risk Assessment**
5. **Apply Enhanced Due Diligence (if necessary)**
6. **Complete the KYC Verification**

Here's a detailed walkthrough of each step:

#### Step 1: Create a KYC Verification

First, create a new KYC verification for the natural person:

```
// Create a new KYC verification
KycVerificationDTO verification = new KycVerificationDTO();
verification.setPartyId(123L); // ID of the natural person
verification.setVerificationType(VerificationTypeEnum.IDENTITY);
verification.setStatus(VerificationStatusEnum.PENDING);
verification.setRiskLevel(RiskLevelEnum.MEDIUM); // Initial risk level
verification.setVerificationMethod(VerificationMethodEnum.DOCUMENT);

// Submit the verification
Long verificationId = kycVerificationService.create(verification).block().getKycVerificationId();
```

#### Step 2: Upload and Verify Identity Documents

Next, upload and verify the identity documents for the natural person:

```
// Add identity document to the KYC verification
VerificationDocumentDTO document = new VerificationDocumentDTO();
document.setDocumentType(DocumentTypeEnum.PASSPORT);
document.setDocumentNumber("AB123456");
document.setIssuingCountry("US");
document.setIssueDate(LocalDate.now().minusYears(2));
document.setExpiryDate(LocalDate.now().plusYears(8));
document.setDocumentUrl("https://document-storage.example.com/docs/passport-123.pdf");

// Submit the document
Long documentId = verificationDocumentService.create(document).block().getVerificationDocumentId();

// Verify the document
VerificationDocumentDTO verifyDocument = new VerificationDocumentDTO();
verifyDocument.setIsVerified(true);
verifyDocument.setVerificationDate(LocalDateTime.now());
verifyDocument.setVerificationMethod(VerificationMethodEnum.MANUAL);
verifyDocument.setVerificationNotes("Document verified by compliance officer");

// Update the document verification status
verificationDocumentService.update(documentId, verifyDocument).block();
```

#### Step 3: Perform AML Screening

Perform AML (Anti-Money Laundering) screening to check against sanctions lists, PEP lists, and adverse media:

```
// Perform AML screening
AmlScreeningDTO screening = new AmlScreeningDTO();
screening.setPartyId(123L);
screening.setScreeningType(ScreeningTypeEnum.SANCTIONS);
screening.setScreeningSource("OFAC");
screening.setScreeningDate(LocalDateTime.now());

// Submit the screening
Long screeningId = amlScreeningService.create(screening).block().getAmlScreeningId();

// Check for matches
PaginationResponse<AmlMatchDTO> matches = amlMatchService.findAll(
    new FilterRequest<AmlMatchDTO>().setFilters(
        new AmlMatchDTO().setAmlScreeningId(screeningId)
    )
).block();

// Process matches if any
if (matches.getTotalElements() > 0) {
    for (AmlMatchDTO match : matches.getContent()) {
        // Resolve match (e.g., as false positive)
        AmlMatchDTO updateMatch = new AmlMatchDTO();
        updateMatch.setResolutionStatus(ResolutionStatusEnum.FALSE_POSITIVE);
        updateMatch.setResolutionNotes("Verified not a match due to different date of birth");
        updateMatch.setResolvedBy("compliance-officer-1");

        // Update the match resolution
        amlMatchService.update(match.getAmlMatchId(), updateMatch).block();
    }
}
```

#### Step 4: Conduct Risk Assessment

Assess the risk level of the natural person based on various factors:

```
// Perform risk assessment
RiskAssessmentDTO assessment = new RiskAssessmentDTO();
assessment.setPartyId(123L);
assessment.setRiskFactors(Arrays.asList("COUNTRY_RISK", "OCCUPATION_RISK", "TRANSACTION_VOLUME"));
assessment.setAssessmentType(AssessmentTypeEnum.INITIAL);
assessment.setAssessmentDate(LocalDateTime.now());

// Submit the risk assessment
RiskAssessmentDTO result = riskAssessmentService.create(assessment).block();

// Check if high risk
if (result.getRiskLevel() == RiskLevelEnum.HIGH) {
    // Proceed to Step 5 (Enhanced Due Diligence)
} else {
    // Skip to Step 6 (Complete the KYC Verification)
}
```

#### Step 5: Apply Enhanced Due Diligence (if necessary)

If the risk assessment identifies the natural person as high-risk, perform Enhanced Due Diligence (EDD):

```
// Only for high-risk customers
EnhancedDueDiligenceDTO edd = new EnhancedDueDiligenceDTO();
edd.setKycVerificationId(verificationId);
edd.setEddType(EddTypeEnum.HIGH_RISK_CUSTOMER);
edd.setEddStatus(EddStatusEnum.PENDING);
edd.setAssignedTo("senior-compliance-officer");

// Submit the EDD request
Long eddId = enhancedDueDiligenceService.create(edd).block().getEddId();

// After completing EDD investigation, update the EDD status
EnhancedDueDiligenceDTO updateEdd = new EnhancedDueDiligenceDTO();
updateEdd.setEddStatus(EddStatusEnum.COMPLETED);
updateEdd.setCompletionDate(LocalDateTime.now());
updateEdd.setCompletedBy("senior-compliance-officer");
updateEdd.setNotes("All additional checks completed. Customer approved with conditions.");

// Update the EDD status
enhancedDueDiligenceService.update(eddId, updateEdd).block();
```

#### Step 6: Complete the KYC Verification

Finally, update the KYC verification status to complete the process:

```
// Update KYC verification status
KycVerificationDTO updateVerification = new KycVerificationDTO();
updateVerification.setStatus(VerificationStatusEnum.COMPLETED);
updateVerification.setVerificationDate(LocalDateTime.now());
updateVerification.setVerifiedBy("compliance-officer-1");
updateVerification.setNotes("All verification steps completed successfully");

// Update the verification status
kycVerificationService.update(verificationId, updateVerification).block();
```

### KYB Process for Legal Entities

The Know Your Business (KYB) process for legal entities is more complex than KYC for natural persons, as it involves verifying the business itself as well as its ownership structure, including Ultimate Beneficial Owners (UBOs). The process typically follows these steps:

1. **Create a KYB Verification**
2. **Register Business Profile and Locations**
3. **Upload and Verify Corporate Documents**
4. **Define Corporate Structure**
5. **Register Ultimate Beneficial Owners (UBOs)**
6. **Register Directors and Key Personnel**
7. **Perform AML Screening**
8. **Conduct Risk Assessment**
9. **Apply Enhanced Due Diligence (if necessary)**
10. **Complete the KYB Verification**

Here's a detailed walkthrough of each step:

#### Step 1: Create a KYB Verification

First, create a new KYB verification for the legal entity:

```
// Create a new KYB verification
KybVerificationDTO verification = new KybVerificationDTO();
verification.setPartyId(456L); // ID of the legal entity
verification.setVerificationType(VerificationTypeEnum.BUSINESS_IDENTITY);
verification.setStatus(VerificationStatusEnum.PENDING);
verification.setRiskLevel(RiskLevelEnum.MEDIUM); // Initial risk level
verification.setVerificationMethod(VerificationMethodEnum.DOCUMENT);

// Submit the verification
Long verificationId = kybVerificationService.create(verification).block().getKybVerificationId();
```

#### Step 2: Register Business Profile and Locations

Register the business profile and its locations:

```
// Create business profile
BusinessProfileDTO profile = new BusinessProfileDTO();
profile.setPartyId(456L);
profile.setLegalName("Acme Corporation");
profile.setTradingName("Acme Corp");
profile.setLegalForm("Corporation");
profile.setIncorporationCountry("US");
profile.setIncorporationDate(LocalDate.of(2010, 1, 15));
profile.setTaxId("12-3456789");
profile.setRegistrationNumber("C123456-2010");
profile.setIndustryCode("NAICS-541512");
profile.setIndustryName("Computer Systems Design Services");

// Submit the business profile
Long profileId = businessProfileService.create(profile).block().getBusinessProfileId();

// Add business location (headquarters)
BusinessLocationDTO location = new BusinessLocationDTO();
location.setPartyId(456L);
location.setLocationType("HEADQUARTERS");
location.setAddressLine1("123 Main Street");
location.setAddressLine2("Suite 400");
location.setCity("San Francisco");
location.setState("CA");
location.setPostalCode("94105");
location.setCountry("US");
location.setIsPrimary(true);

// Submit the business location
Long locationId = businessLocationService.create(location).block().getBusinessLocationId();
```

#### Step 3: Upload and Verify Corporate Documents

Upload and verify the corporate documents for the legal entity:

```
// Upload certificate of incorporation
CorporateDocumentDTO document1 = new CorporateDocumentDTO();
document1.setPartyId(456L);
document1.setDocumentType(CorporateDocumentTypeEnum.CERTIFICATE_OF_INCORPORATION);
document1.setDocumentNumber("CERT-2010-12345");
document1.setIssueDate(LocalDate.of(2010, 1, 15));
document1.setIssuingAuthority("Delaware Secretary of State");
document1.setDocumentUrl("https://document-storage.example.com/docs/certificate-456.pdf");

// Submit the document
Long docId1 = corporateDocumentService.create(document1).block().getCorporateDocumentId();

// Verify the document
CorporateDocumentDTO verifyDocument = new CorporateDocumentDTO();
verifyDocument.setIsVerified(true);
verifyDocument.setVerificationDate(LocalDateTime.now());
verifyDocument.setVerificationNotes("Document verified against official registry");
verifyDocument.setVerificationAgent("compliance-officer-2");

// Update the document verification status
corporateDocumentService.update(docId1, verifyDocument).block();
```

#### Step 4: Define Corporate Structure

Define the corporate structure, including parent-subsidiary relationships:

```
// If this is a subsidiary, define relationship with parent company
CorporateStructureDTO structure = new CorporateStructureDTO();
structure.setPartyId(456L); // This entity
structure.setRelatedPartyId(789L); // Parent company
structure.setRelationshipType("SUBSIDIARY");
structure.setOwnershipPercentage(new BigDecimal("100.00")); // Wholly owned subsidiary
structure.setStartDate(LocalDateTime.of(2010, 1, 15, 0, 0));
structure.setIsVerified(true);
structure.setVerificationDate(LocalDateTime.now());

// Submit the corporate structure relationship
Long structureId = corporateStructureService.create(structure).block().getCorporateStructureId();
```

#### Step 5: Register Ultimate Beneficial Owners (UBOs)

Register the Ultimate Beneficial Owners (UBOs) of the legal entity. UBOs are natural persons who ultimately own or control the legal entity:

```
// Register a UBO (natural person who owns >25% of the company)
UboDTO ubo1 = new UboDTO();
ubo1.setPartyId(456L); // Legal entity
ubo1.setIndividualPartyId(123L); // Natural person (already KYC verified)
ubo1.setOwnershipPercentage(new BigDecimal("30.00"));
ubo1.setOwnershipType(OwnershipTypeEnum.DIRECT);
ubo1.setIsControlling(true);
ubo1.setStartDate(LocalDateTime.of(2010, 1, 15, 0, 0));

// Submit the UBO
Long uboId = uboService.create(ubo1).block().getUboId();

// Verify the UBO information
UboDTO verifyUbo = new UboDTO();
verifyUbo.setIsVerified(true);
verifyUbo.setVerificationDate(LocalDateTime.now());
verifyUbo.setVerificationMethod(VerificationMethodEnum.DOCUMENT);
verifyUbo.setTitularidadRealDocument("https://document-storage.example.com/docs/ownership-proof-123.pdf");

// Update the UBO verification status
uboService.update(uboId, verifyUbo).block();
```

#### Step 6-9: Complete Remaining Steps

The remaining steps (Register Directors, Perform AML Screening, Conduct Risk Assessment, Apply Enhanced Due Diligence) follow a similar pattern to the corresponding steps in the KYC process, but with business-specific data and checks.

#### Step 10: Complete the KYB Verification

Finally, update the KYB verification status to complete the process:

```
// Update KYB verification status
KybVerificationDTO updateVerification = new KybVerificationDTO();
updateVerification.setStatus(VerificationStatusEnum.COMPLETED);
updateVerification.setVerificationDate(LocalDateTime.now());
updateVerification.setVerifiedBy("compliance-officer-1");
updateVerification.setNotes("All verification steps completed successfully");

// Update the verification status
kybVerificationService.update(verificationId, updateVerification).block();
```

### Linking Natural Persons to Legal Entities

A critical aspect of the KYB process is establishing and verifying the links between legal entities and the natural persons who own or control them. This is done through several mechanisms:

1. **Ultimate Beneficial Owners (UBOs)**: Natural persons who ultimately own or control the legal entity, typically with ownership of 25% or more.
2. **Directors and Officers**: Natural persons who serve as directors, officers, or in other key management positions.
3. **Authorized Signatories**: Natural persons authorized to act on behalf of the legal entity.
4. **Power of Attorney Holders**: Natural persons granted legal authority to act on behalf of the legal entity.

Each of these natural persons should have completed their own KYC process before being linked to the legal entity. The linking process ensures transparency in the ownership and control structure of the legal entity, which is essential for regulatory compliance.

Here's an example of how these relationships are established and maintained:

```
// 1. First, ensure the natural person has completed KYC
// (as shown in the KYC Process for Natural Persons section)

// 2. Then link the natural person to the legal entity as a UBO
UboDTO ubo = new UboDTO();
ubo.setPartyId(456L); // Legal entity
ubo.setIndividualPartyId(123L); // Natural person (already KYC verified)
ubo.setOwnershipPercentage(new BigDecimal("30.00"));
ubo.setOwnershipType(OwnershipTypeEnum.DIRECT);
ubo.setIsControlling(true);
ubo.setStartDate(LocalDateTime.of(2010, 1, 15, 0, 0));

// Submit the UBO relationship
Long uboId = uboService.create(ubo).block().getUboId();

// 3. Link the natural person as a director
DirectorDTO director = new DirectorDTO();
director.setPartyId(456L); // Legal entity
director.setIndividualPartyId(125L); // Natural person (already KYC verified)
director.setPosition("DIRECTOR");
director.setIsExecutive(true);
director.setStartDate(LocalDateTime.of(2010, 1, 15, 0, 0));

// Submit the director relationship
Long directorId = directorService.create(director).block().getDirectorId();

// 4. Grant power of attorney to a natural person
PowerOfAttorneyDTO poa = new PowerOfAttorneyDTO();
poa.setGrantorPartyId(456L); // Legal entity granting the power
poa.setGranteePartyId(127L); // Natural person receiving the power
poa.setPoaType(PoaTypeEnum.BUSINESS);
poa.setScope("LIMITED");
poa.setDescription("Authority to sign contracts up to $10,000");
poa.setEffectiveDate(LocalDate.now());
poa.setExpiryDate(LocalDate.now().plusYears(1));
poa.setDocumentUrl("https://document-storage.example.com/docs/poa-456-127.pdf");

// Submit the power of attorney
Long poaId = powerOfAttorneyService.create(poa).block().getPowerOfAttorneyId();
```

## Troubleshooting

### Common Issues

#### Database Connection Issues
```
Error: Unable to connect to database
```
**Solution**: Verify database credentials and connection settings in environment variables.

#### Application Startup Failures
```
Error: Application failed to start
```
**Solution**: Check logs for specific errors. Ensure all required environment variables are set.

#### API Errors
```
Error: 400 Bad Request
```
**Solution**: Verify request payload matches the expected format. Check API documentation for required fields.

### Logging

To enable debug logging, set the following environment variable:
```bash
LOGGING_LEVEL=DEBUG
```

## Development Guidelines

### Coding Standards
- Follow Java coding conventions
- Use reactive programming patterns with WebFlux
- Implement proper error handling
- Write comprehensive unit and integration tests
- Document public APIs with Javadoc

### Version Control
- Use feature branches for development
- Submit changes through pull requests
- Follow semantic versioning for releases
- Write meaningful commit messages

### Testing
- Write unit tests for business logic
- Implement integration tests for API endpoints
- Use test containers for database testing
- Aim for high test coverage

#### Test Suite
A comprehensive test suite has been implemented for all services in the `com.catalis.core.kycb.core.services` package. The test suite ensures that all service implementations function correctly and maintain their expected behavior over time.

The test suite covers:
- CRUD operations (Create, Read, Update, Delete)
- Error handling and edge cases
- Business logic specific to each service
- Data validation and transformation

Each service implementation has a corresponding test class that follows a consistent structure and testing approach. The tests use JUnit 5, Mockito, and Reactor Test to verify the behavior of the services.

For more details about the test suite, see the [Test Suite README](common-platform-kycb-mgmt-core/src/test/java/com/catalis/core/kycb/core/services/README.md).

To run the tests, use the following Maven command:
```bash
mvn test
```

To run tests for a specific service:
```bash
mvn test -Dtest=*ServiceImplTest
```

To run a specific test class:
```bash
mvn test -Dtest=UboServiceImplTest
```

To run a specific test method:
```bash
mvn test -Dtest=UboServiceImplTest#testCreate
```

To generate a test coverage report:
```bash
mvn test jacoco:report
```

The test suite is integrated into the CI/CD pipeline and runs automatically on every pull request and merge to the main branch, ensuring that all changes to the codebase are properly tested before being deployed to production.

## Contributing

We welcome contributions to improve the Firefly KYC/B & AML Management microservice. Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

Please ensure your code follows our coding standards and includes appropriate tests.
