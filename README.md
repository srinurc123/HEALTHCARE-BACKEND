# Healthcare Appointment Management System

A full-stack web application for managing healthcare appointments, built with Spring Boot and React.

## Features

- User authentication with JWT
- Role-based access control for patients, doctors, and administrators
- Appointment booking with automatic slot generation
- Medical records management
- Prescription management
- Doctor reviews and ratings
- Email notifications for appointments
- Redis caching for improved performance

## Technology Stack

Backend:
- Spring Boot 3.2.0
- MySQL Database
- Spring Security with JWT
- Spring Data JPA
- Redis
- JavaMailSender
- Maven

Frontend:
- React 18
- Material-UI
- React Router
- Axios
- Context API

## Setup Instructions

Backend:
1. Install Java 17, MySQL 8, and Redis
2. Update database credentials in application.properties
3. Run: mvn clean install
4. Start: mvn spring-boot:run
5. Backend runs on http://localhost:8080

Frontend:
1. Install Node.js 16 or higher
2. Navigate to healthcare-frontend directory
3. Run: npm install
4. Update API_BASE_URL in constants.js if needed
5. Start: npm start
6. Frontend runs on http://localhost:3000

## Configuration

Backend application.properties:
- Database URL: jdbc:mysql://localhost:3306/healthcare_db
- JWT Secret: Configure your secret key
- Email SMTP: Configure your email server
- Redis: localhost:6379

## API Endpoints

Authentication:
- POST /api/v1/auth/signup - Register user
- POST /api/v1/auth/login - Login user

Appointments:
- POST /api/v1/appointments/book - Book appointment
- GET /api/v1/appointments/patient/{id} - Get patient appointments
- GET /api/v1/appointments/doctor/{id} - Get doctor appointments
- PUT /api/v1/appointments/cancel/{id} - Cancel appointment

Doctors:
- GET /api/v1/doctors - List all doctors
- GET /api/v1/doctors/search - Search by specialization
- GET /api/v1/availability/doctor/{id} - Get available slots

Medical Records:
- POST /api/v1/medical-records - Create record
- GET /api/v1/medical-records/patient/{id} - Get patient records

Prescriptions:
- POST /api/v1/prescriptions - Create prescription
- GET /api/v1/prescriptions/patient/{id} - Get patient prescriptions

## User Roles

Patient:
- Browse and search doctors
- Book appointments
- View medical records and prescriptions
- Cancel appointments
- Rate doctors

Doctor:
- View daily schedule
- Manage appointments
- Create medical records
- Write prescriptions
- Complete appointments

Admin:
- View system dashboard
- Manage users
- View all appointments

## Default Credentials

After signup, create accounts with:
- Patient role for patient features
- Doctor role with specialization for doctor features
- Admin role for administrative access

## Database Schema

Main tables:
- users, patients, doctors - User management
- appointments - Appointment records
- availability_slots - Doctor availability
- medical_records - Patient medical history
- prescriptions - Medication prescriptions
- reviews - Doctor ratings

## Features Highlights

Automatic slot generation: Doctors have predefined 9 AM to 6 PM slots with 30-minute intervals. Slots are auto-generated when patients check availability.

Concurrent booking protection: Database transactions prevent double-booking of the same slot.

Email notifications: Automated emails sent for appointment confirmations, reminders, and cancellations.

Caching strategy: Redis caches doctor availability to reduce database queries and improve response times.

## License

This project is developed for educational and portfolio purposes.

