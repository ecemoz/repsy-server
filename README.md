
![image](https://github.com/user-attachments/assets/fc5970bd-09a5-45ac-bd25-93051281c8bd)
# Repsy Package Repository Backend

This project is a backend system designed for handling package deployment and download operations, with pluggable storage strategies using the Strategy Design Pattern.

## 📦 Features
- Deploy and download `.rep` package files and accompanying `meta.json` metadata.
- Modular storage layer: file system and object storage (MinIO) implementations.
- PostgreSQL database integration to persist metadata.
- Two REST endpoints: `POST /{packageName}/{version}` and `GET /{packageName}/{version}/{fileName}`.
- Docker Compose setup for seamless local development.
- Maven multi-module architecture.
- Libraries deployed to Repsy private Maven repository.

## 📂 Module Structure
```
repsy-server/
├── core/                      # Main Spring Boot application
├── storage-api/              # Storage interface
├── storage-filesystem/       # Filesystem implementation
├── storage-objectstorage/    # MinIO (object storage) implementation
└── docker-compose.yml        # Local development setup
```

## 🚀 Running Locally

### Prerequisites
- Java 17
- Maven
- Docker + Docker Compose

### Start with Docker
```bash
docker-compose up --build
```
This will start PostgreSQL, MinIO, and the Spring Boot app on port `8080`.

### Application Endpoints

#### Deploy a Package
```
POST /{packageName}/{version}
Multipart Form Data:
- packageFile: your .rep file
- metafile: your meta.json file
```

#### Download a Package or Meta File
```
GET /{packageName}/{version}/{fileName}
```

## 🔐 MinIO Configuration
MinIO runs on `http://localhost:9000`
- Access Key: `minioadmin`
- Secret Key: `minioadmin`

## 🧪 Testing
Run tests with:
```bash
mvn clean test
```

## 📥 Deploying Libraries to Repsy

1. Configure `~/.m2/settings.xml`:
```xml
<settings>
  <servers>
    <server>
      <id>repsy</id>
      <username>ecemnurozen</username>
      <password>[your-password]</password>
    </server>
  </servers>
</settings>
```

2. Add to each module's `pom.xml`:
```xml
<distributionManagement>
  <repository>
    <id>repsy</id>
    <name>Repsy Private Repo</name>
    <url>https://repo.repsy.io/mvn/ecemnurozen/[repo-name]</url>
  </repository>
</distributionManagement>
```

3. Deploy:
```bash
mvn clean deploy
```

## ✅ Acceptance Criteria Checklist
- [x] Spring Boot REST API in Java 17
- [x] 2 Endpoints for deploy/download
- [x] PostgreSQL integration
- [x] MinIO-based object storage
- [x] Repsy deployment for libraries
- [x] Docker Compose for developer-friendliness
- [x] Clean commit history
- [x] Robust input validation

---

**Author:** Ecem Nur Özen  
**Position:** Junior Full Stack Developer Assignment – Repsy
