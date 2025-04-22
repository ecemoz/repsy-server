
# Repsy - Junior FullStack Developer Assignment

This project is a solution to the **Junior FullStack Developer Assignment**. It includes a modular Spring Boot application with a PostgreSQL backend and two interchangeable storage strategies (FileSystem and MinIO Object Storage).

---

## 🚀 Features

- Java 17 + Spring Boot 3.2.5
- PostgreSQL for package metadata
- Docker Compose setup (MinIO + PostgreSQL)
- Two REST endpoints:
  - Deployment (`POST /{packageName}/{version}`)
  - Download (`GET /{packageName}/{version}/{fileName}`)
- Strategy Pattern for storage (via `storage-api`)
- Developer-friendly setup (no manual installations required)
- Proper validation and exception handling
- Maven multi-module structure, Repsy-compatible
- Clean and documented codebase

---

## 📦 Project Structure

```
repsy-server/
├── core/                    # Main Spring Boot backend
├── storage-api/            # Shared StorageService interface
├── storage-filesystem/     # File system-based implementation
├── storage-objectstorage/  # MinIO object storage implementation
├── docker-compose.yml      # Service setup (Postgres + MinIO)
└── README.md
```

---

## 🐳 Running with Docker

1. Clone the repo:

```bash
git clone https://github.com/yourname/repsy-server.git
cd repsy-server
```

2. Start services:

```bash
docker-compose up -d
```

3. Run the app:

```bash
cd core
mvn spring-boot:run
```

---

## 📮 API Reference

### Deploy Package

**POST** `/{packageName}/{version}`  
Content-Type: `multipart/form-data`

Fields:
- `packageFile`: `.rep` file
- `metaFile`: `meta.json` file

### Download Package

**GET** `/{packageName}/{version}/{fileName}`

Returns file stream (supports `.rep` or `.json`).

---

## 🧠 Storage Strategy Pattern

We defined `StorageService` in `storage-api` and implemented:
- `FileSystemStorageService` in `storage-filesystem`
- `MinioStorageService` in `storage-objectstorage`

You can switch between them by importing the right implementation into `core`.

---

## ✅ Acceptance Criteria

| Criteria | Status |
|---------|--------|
| Spring Boot app (Java LTS) | ✅ |
| PostgreSQL usage | ✅ |
| 2 REST endpoints | ✅ |
| Repsy-ready Maven libs | ✅ |
| FileSystem + MinIO impl. | ✅ |
| Docker-based local setup | ✅ |
| Input validation & errors | ✅ |
| Clean commit history | ✅ |
| README & developer docs | ✅ |

---

## 🧪 Potential Improvements

- Auth middleware (JWT)
- Upload/download size limits
- Unit/integration test coverage
- Metrics endpoint (Prometheus-ready)

---

## 👤 Author

Developed as part of Repsy’s Junior FullStack Developer Assignment  
Date: April 23, 2025

---

## 🪪 License

MIT
