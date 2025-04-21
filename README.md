# Firefly - KYC/B & AML Management

## Overview
This is a microservice for managing Know Your Customer (KYC), Know Your Business (KYB), and Anti-Money Laundering (AML) processes. It provides a robust, scalable, and secure platform for financial institutions and businesses to comply with regulatory requirements while streamlining customer and business onboarding.

## Key Features

### KYC Management
- Individual identity verification
- Document validation and verification
- Risk assessment and scoring
- Verification status tracking
- Automated and manual verification workflows

### KYB Management
- Business identity verification
- Corporate document management
- Business structure verification
- Ultimate Beneficial Owner (UBO) verification
- Mercantile registry verification
- Tax ID verification
- Operating license verification

### AML Compliance
- AML screening against global watchlists
- Sanctions screening
- PEP (Politically Exposed Person) screening
- Adverse media screening
- Risk-based approach to AML
- Match management and resolution

### Risk Management
- Risk assessment and scoring
- Risk level classification
- Industry risk evaluation
- Periodic review scheduling
- Enhanced Due Diligence (EDD) for high-risk entities

### Compliance
- Compliance case management
- Regulatory reporting
- Audit trail and record keeping
- Compliance action tracking
- Case prioritization and status tracking

### Document Management
- Corporate document storage and retrieval
- Verification document management
- Document type classification
- Document verification status tracking

## Architecture

### Technology Stack
- **Framework**: Spring Boot with WebFlux (reactive programming)
- **Database**: PostgreSQL with R2DBC for reactive database access
- **Migration**: Flyway for database schema management
- **Documentation**: OpenAPI/Swagger for API documentation
- **Monitoring**: Spring Actuator for health checks and metrics

### Modular Structure
The application is organized into four main modules:

1. **common-platform-kycb-mgmt-interfaces**
   - DTOs (Data Transfer Objects)
   - Enumerations
   - API contracts

2. **common-platform-kycb-mgmt-models**
   - Entity definitions
   - Repository interfaces
   - Database migrations

3. **common-platform-kycb-mgmt-core**
   - Business logic implementation
   - Service interfaces and implementations
   - Mappers for entity-DTO conversion

4. **common-platform-kycb-mgmt-web**
   - REST controllers
   - API endpoints
   - Request/response handling
   - Application entry point

## API Documentation
The API is documented using OpenAPI/Swagger. When running the application, the documentation is available at:
- `/swagger-ui.html` - Swagger UI
- `/v3/api-docs` - OpenAPI JSON

The API follows RESTful principles and is organized into the following main categories:
- Identity (KYC/KYB verification)
- Compliance (AML screening, risk assessment)
- Document management
- Corporate structure and UBO

## Setup and Installation

### Prerequisites
- Java 17 or higher
- PostgreSQL 12 or higher
- Maven 3.6 or higher

### Environment Variables
The following environment variables need to be configured:
- `DB_HOST` - Database host
- `DB_PORT` - Database port
- `DB_NAME` - Database name
- `DB_USERNAME` - Database username
- `DB_PASSWORD` - Database password
- `DB_SSL_MODE` - Database SSL mode

### Building the Application
```bash
mvn clean install
```

### Running the Application
```bash
java -jar common-platform-kycb-mgmt-web/target/common-platform-kycb-mgmt-web.jar
```

### Docker Deployment
```bash
docker build -t firefly-kycb-mgmt .
docker run -p 8080:8080 --env-file .env firefly-kycb-mgmt
```

## Configuration
The application supports different profiles:
- `dev` - Development environment with detailed logging
- `testing` - Testing environment with API documentation enabled
- `prod` - Production environment with minimal logging and API documentation disabled

Configuration can be customized through the `application.yaml` file or environment variables.

## Development Guidelines

### Coding Standards
- Follow Java coding conventions
- Use reactive programming patterns with WebFlux
- Implement proper error handling
- Write comprehensive unit and integration tests

### Version Control
- Use feature branches for development
- Submit changes through pull requests
- Follow semantic versioning for releases

### Testing
- Write unit tests for business logic
- Implement integration tests for API endpoints
- Use test containers for database testing