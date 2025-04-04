# 🔐 EDU-ERP 인증 서비스

## 📋 프로젝트 개요
EDU-ERP 시스템의 인증 서비스 컴포넌트입니다. 이 서비스는 사용자 인증, 권한 관리 및 보안 기능을 담당합니다.

## 🛠️ 기술 스택
- **☕ Java 21**
- **🍃 Spring Boot 3**
- **🛡️ Spring Security**
- **💾 Spring Data JPA**
- **🐘 PostgreSQL**
- **📦 Gradle**

## ✨ 주요 기능
- **🔑 사용자 인증** (로그인/로그아웃)
- **👥 사용자 등록 및 관리**
- **👮‍♂️ 역할 기반 접근 제어** (RBAC)
- **🎫 JWT 토큰 기반 인증**
- **🔄 OAuth2 지원** (예정)

## 🚀 개발 환경 설정

### 📋 필수 요구사항
- **☕ JDK 21 이상**
- **🐘 MariaDB**
- **📦 Gradle**

### 🏗️ 빌드 및 실행 방법
```bash
# 프로젝트 빌드
./gradlew build

# 프로젝트 실행
./gradlew bootRun
```

### 🗃️ 데이터베이스 설정
`application.properties` 또는 `application.yml` 파일에서 데이터베이스 연결 정보를 설정할 수 있습니다.

## 📚 API 문서
API 문서는 Swagger UI를 통해 제공됩니다. 서비스 실행 후 다음 URL에서 확인할 수 있습니다:
`http://localhost:8080/swagger-ui.html`

## 📂 폴더 구조
```
src/main/java/com/eduerp/auth_service/
├── config/         # 애플리케이션 설정
├── controller/     # REST API 컨트롤러
├── entity/         # JPA 엔티티 클래스
├── init/           # 초기화 데이터 및 스크립트
├── repository/     # 데이터 액세스 객체
├── security/       # 보안 관련 클래스
├── service/        # 비즈니스 로직
└── AuthServiceApplication.java  # 메인 애플리케이션 클래스
```

## 📜 라이센스
이 프로젝트는 [라이센스 정보]에 따라 라이센스가 부여됩니다.