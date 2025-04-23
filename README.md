# Firefly - KYC/B & AML Management

A comprehensive microservice for managing Know Your Customer (KYC), Know Your Business (KYB), and Anti-Money Laundering (AML) processes in financial institutions.

## Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Technology Stack](#technology-stack)
- [Quickstart](#quickstart)
- [Project Structure](#project-structure)
- [API Documentation](#api-documentation)
- [Usage Examples](#usage-examples)
- [Complete Process Workflows](#complete-process-workflows)
- [Configuration](#configuration)
- [Development Guidelines](#development-guidelines)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

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

Get up and running with the KYC/B & AML Management microservice in minutes:

### Prerequisites
- Java 21 or higher
- Docker and Docker Compose
- PostgreSQL 12+ (or use the provided Docker Compose setup)
- Maven 3.6+

### Clone and Build
```bash
# Clone the repository
git clone https://github.com/firefly-oss/common-platform-kycb-mgmt.git
cd common-platform-kycb-mgmt

# Build the application
mvn clean install
```

### Run with Docker
```bash
# Create a .env file with required environment variables
cat > .env << EOF
DB_HOST=postgres
DB_PORT=5432
DB_NAME=kycb_mgmt
DB_USERNAME=postgres
DB_PASSWORD=postgres
DB_SSL_MODE=disable
EOF

# Start the application and database
docker-compose up -d
```

### Run Locally
```bash
# Set environment variables
export DB_HOST=localhost
export DB_PORT=5432
export DB_NAME=kycb_mgmt
export DB_USERNAME=postgres
export DB_PASSWORD=postgres
export DB_SSL_MODE=disable

# Run the application
java -jar common-platform-kycb-mgmt-web/target/common-platform-kycb-mgmt.jar
```

### Access the API
- API Documentation: http://localhost:8080/swagger-ui.html
- OpenAPI Specification: http://localhost:8080/v3/api-docs
- Health Check: http://localhost:8080/actuator/health

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
- **Sanctions Questionnaire**: Collect and manage sanctions and embargo information for legal entities and self-employed individuals

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

## Technology Stack

### Core Technologies
- **Java 21**: Latest LTS version of Java
- **Spring Boot 3.x**: Framework for building microservices
- **Spring WebFlux**: Reactive web framework
- **Project Reactor**: Reactive programming library
- **R2DBC**: Reactive Relational Database Connectivity
- **PostgreSQL**: Relational database
- **Flyway**: Database migration tool
- **Maven**: Build and dependency management

### API & Documentation
- **OpenAPI/Swagger**: API documentation
- **Spring Validation**: Input validation

### Monitoring & Metrics
- **Spring Actuator**: Health checks and metrics
- **Micrometer**: Metrics collection
- **Prometheus**: Metrics storage

### Development Tools
- **Lombok**: Reduces boilerplate code
- **MapStruct**: Object mapping
- **JUnit 5**: Testing framework
- **Mockito**: Mocking framework
- **Reactor Test**: Testing reactive code

### Containerization
- **Docker**: Containerization
- **Docker Compose**: Multi-container applications

## Project Structure
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

### API Categories

The API follows RESTful principles and is organized into the following main categories:

#### Identity Management
- `/api/v1/identity/parties/{partyId}/kyc`: KYC verification endpoints
- `/api/v1/identity/parties/{partyId}/kyb`: KYB verification endpoints
- `/api/v1/identity/parties/{partyId}/sanctions-questionnaire`: Sanctions questionnaire endpoints

#### Compliance
- `/api/v1/compliance/parties/{partyId}/aml-screenings`: AML screening endpoints
- `/api/v1/compliance/parties/{partyId}/risk-assessments`: Risk assessment endpoints
- `/api/v1/compliance/cases`: Compliance case management endpoints
- `/api/v1/compliance/cases/{caseId}/reports`: Regulatory reporting endpoints

#### Document Management
- `/api/v1/documents/verification`: Identity document endpoints
- `/api/v1/documents/corporate`: Corporate document endpoints
- `/api/v1/documents/power-of-attorney`: Power of attorney document endpoints

#### Corporate Structure
- `/api/v1/corporate/parties/{partyId}/structure`: Business structure endpoints
- `/api/v1/corporate/parties/{partyId}/ubos`: UBO management endpoints
- `/api/v1/corporate/parties/{partyId}/locations`: Business location endpoints
- `/api/v1/corporate/parties/{partyId}/economic-activities`: Economic activity endpoints

### Accessing Documentation
When running the application, the documentation is available at:
- `/swagger-ui.html` - Interactive Swagger UI
- `/v3/api-docs` - OpenAPI JSON specification


## Configuration

The application supports different profiles for various environments:

### Profiles
- `dev` - Development environment with detailed logging
- `testing` - Testing environment with API documentation enabled
- `prod` - Production environment with minimal logging and API documentation disabled

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

### Configuration Files
Configuration can be customized through:
- `application.yaml` - Main configuration file
- `application-{profile}.yaml` - Profile-specific configurations
- Environment variables - Override configuration at runtime

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

## Complete Process Workflows

This section illustrates the complete process flows for performing KYC for natural persons and KYB for legal entities.

### KYC Process for Natural Persons

The Know Your Customer (KYC) process for natural persons typically follows these steps:

1. **Create a KYC Verification**
2. **Upload and Verify Identity Documents**
3. **Perform AML Screening**
4. **Conduct Risk Assessment**
5. **Apply Enhanced Due Diligence (if necessary)**
6. **Complete the KYC Verification**

For a detailed walkthrough with code examples, see the [KYC Process Documentation](docs/kyc-process.md).

### KYB Process for Legal Entities

The Know Your Business (KYB) process for legal entities follows these steps:

1. **Create a KYB Verification**
2. **Register Business Profile and Locations**
3. **Complete Sanctions and Embargo Questionnaire**
4. **Upload and Verify Corporate Documents**
5. **Define Corporate Structure**
6. **Register Ultimate Beneficial Owners (UBOs)**
7. **Register Directors and Key Personnel**
8. **Perform AML Screening**
9. **Conduct Risk Assessment**
10. **Apply Enhanced Due Diligence (if necessary)**
11. **Complete the KYB Verification**

For a detailed walkthrough with code examples, see the [KYB Process Documentation](docs/kyb-process.md).

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

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
