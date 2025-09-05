# BH LearnSphere Backend

A comprehensive Spring Boot 3 + JDK 17 + MySQL backend for the BH LearnSphere AI-Powered Adaptive Learning Platform.

## 🚀 Features

### Core Functionality
- **User Management**: Registration, authentication, profile management with JWT
- **Course Management**: CRUD operations, enrollment, progress tracking
- **Quiz System**: Multiple question types (MCQ, True/False, Short Answer), scoring
- **Badge System**: Achievement tracking and points system
- **Recommendation Engine**: AI-powered course recommendations
- **Real-time Chat**: WebSocket-based communication
- **Forum System**: Community discussions and Q&A

### Technical Features
- **JWT Authentication**: Secure token-based authentication
- **RESTful APIs**: Complete REST endpoints for frontend integration
- **CORS Support**: Configured for React frontend (localhost:3000)
- **Database Integration**: MySQL with JPA/Hibernate
- **Exception Handling**: Global exception handler with proper error responses
- **Data Initialization**: Sample data for testing and development
- **Unit Tests**: Comprehensive test coverage for services

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.5.6**
- **Spring Security 6** with JWT
- **Spring Data JPA**
- **MySQL 8**
- **WebSocket** for real-time features
- **Maven** for dependency management
- **JUnit 5** for testing

## 📁 Project Structure

```
src/main/java/com/bhlearnsphere/
├── entity/                 # JPA Entities
│   ├── User.java
│   ├── Course.java
│   ├── Module.java
│   ├── Lesson.java
│   ├── Quiz.java
│   ├── Question.java
│   ├── Enrollment.java
│   └── Badge.java
├── dto/                    # Data Transfer Objects
│   ├── UserDto.java
│   ├── CourseDto.java
│   ├── QuizDto.java
│   ├── AuthResponse.java
│   └── ...
├── repository/             # JPA Repositories
│   ├── UserRepository.java
│   ├── CourseRepository.java
│   └── ...
├── service/                # Business Logic
│   ├── UserService.java
│   ├── CourseService.java
│   ├── QuizService.java
│   ├── RecommendationService.java
│   └── DataInitializationService.java
├── controller/             # REST Controllers
│   ├── AuthController.java
│   ├── CourseController.java
│   ├── QuizController.java
│   ├── UserController.java
│   └── ...
├── config/                 # Configuration
│   ├── SecurityConfig.java
│   ├── CorsConfig.java
│   └── WebSocketConfig.java
├── util/                   # Utilities
│   └── JwtUtil.java
└── exception/              # Exception Handling
    └── GlobalExceptionHandler.java
```

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd learnsphere
   ```

2. **Database Setup**
   ```sql
   CREATE DATABASE learnsphere;
   ```

3. **Update Configuration**
   Edit `src/main/resources/application.yml`:
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/learnsphere
       username: your_username
       password: your_password
   ```

4. **Build and Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. **Access the Application**
   - Backend API: `http://localhost:8080`
   - Web UI: `http://localhost:8080/`
   - API Documentation: `http://localhost:8080/api/courses`

## 📚 API Endpoints

### Authentication
- `POST /auth/register` - User registration
- `POST /auth/login` - User login
- `GET /auth/verify` - Token verification

### Courses
- `GET /courses` - Get all courses
- `GET /courses/{id}` - Get course by ID
- `POST /courses` - Create course (Instructor)
- `PUT /courses/{id}` - Update course
- `DELETE /courses/{id}` - Delete course
- `POST /courses/{id}/enroll` - Enroll in course
- `GET /courses/enrolled/{userId}` - Get user's enrolled courses

### Quizzes
- `GET /quizzes` - Get all quizzes
- `GET /quizzes/{id}` - Get quiz by ID
- `GET /quizzes/course/{courseId}` - Get quizzes by course
- `POST /quizzes/submit` - Submit quiz answers
- `POST /quizzes/{quizId}/questions` - Add question to quiz

### Users
- `GET /users` - Get all users (Admin)
- `GET /users/{id}` - Get user by ID
- `PUT /users/{id}` - Update user profile
- `POST /users/{id}/points` - Add points to user
- `GET /users/{id}/badges` - Get user badges

### Recommendations
- `GET /recommendations/user/{userId}` - Get recommended courses
- `GET /recommendations/popular` - Get popular courses
- `GET /recommendations/trending` - Get trending courses

## 🔐 Authentication

The API uses JWT (JSON Web Token) for authentication. Include the token in the Authorization header:

```
Authorization: Bearer <your-jwt-token>
```

### Sample Login Request
```json
POST /auth/login
{
  "email": "student@learnsphere.com",
  "password": "student123"
}
```

### Sample Response
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "user": {
    "id": 1,
    "name": "Jane Student",
    "email": "student@learnsphere.com",
    "role": "STUDENT",
    "points": 150
  },
  "message": "Login successful"
}
```

## 🗄️ Database Schema

### Users Table
- `id` (Primary Key)
- `name`, `email`, `password`
- `role` (STUDENT, INSTRUCTOR, ADMIN)
- `points`, `bio`, `location`, `website`

### Courses Table
- `id` (Primary Key)
- `title`, `description`, `price`, `category`
- `instructor_id` (Foreign Key)

### Quizzes Table
- `id` (Primary Key)
- `title`, `course_id` (Foreign Key)

### Questions Table
- `id` (Primary Key)
- `text`, `type`, `options`, `correct_answer`
- `quiz_id` (Foreign Key)

## 🧪 Testing

Run the test suite:
```bash
mvn test
```

Run specific test class:
```bash
mvn test -Dtest=CourseServiceTest
```

## 📊 Sample Data

The application automatically initializes with sample data:
- **Users**: Admin, Instructor, Student accounts
- **Courses**: React Fundamentals, Spring Boot Mastery, JavaScript Basics
- **Quizzes**: Sample quiz with multiple question types
- **Badges**: First Course, Quiz Master, Active Learner

### Default Credentials
- **Admin**: admin@learnsphere.com / admin123
- **Instructor**: instructor@learnsphere.com / instructor123
- **Student**: student@learnsphere.com / student123

## 🔧 Configuration

### JWT Configuration
```yaml
jwt:
  secret: mySecretKeyForJWTTokenGeneration123456789
  expiration: 86400000 # 24 hours
```

### CORS Configuration
Configured to allow requests from `http://localhost:3000` (React frontend).

### Database Configuration
```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

## 🚀 Deployment

### Production Build
```bash
mvn clean package -Pproduction
```

### Docker Deployment
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/bh-learnsphere-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Contact the development team
- Check the API documentation

---

**BH LearnSphere Backend** - Built with ❤️ using Spring Boot, Java 17, and modern web technologies.
