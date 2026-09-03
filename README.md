# Campus Placement Management System

A web-based **Campus Placement Management System** developed to simplify the placement process for students and companies. The application allows students to view available companies, apply for job opportunities, and track their application status. Companies can view applicants and accept or reject student applications.

## Features

### Student Module
- Student login
- Student dashboard
- View student profile
- View available companies
- Apply for companies
- View submitted applications
- Track application status

### Company Module
- Company login
- Company dashboard
- View students who applied
- View applicant details
- Accept student applications
- Reject student applications
- Update application status

## Technologies Used

- **Java**
- **Java Servlets**
- **JDBC**
- **MySQL**
- **HTML**
- **CSS**
- **Apache Tomcat 10**
- **Git & GitHub**
- **Visual Studio Code**

## How It Works

1. A student logs into the Campus Placement Portal.
2. The student views available companies and job opportunities.
3. The student applies to a company.
4. The application is stored in the MySQL database.
5. The company logs into the Company Portal.
6. The company views students who have applied.
7. The company accepts or rejects an application.
8. The updated application status is displayed to the student.

## Database

The project uses a **MySQL database** to store:

- Student information
- Company information
- Placement applications
- Application status

Java Servlets communicate with the MySQL database using **JDBC**.

## Project Structure

```text
Campus-Placement-Management-System
│
├── src/
│   ├── DBConnection.java
│   ├── StudentLoginServlet.java
│   ├── CompanyLoginServlet.java
│   ├── ApplyServlet.java
│   ├── MyApplicationsServlet.java
│   ├── ViewApplicantsServlet.java
│   └── UpdateStatusServlet.java
│
├── webapp/
│   ├── index.html
│   ├── student-login.html
│   ├── student-dashboard.html
│   ├── student-profile.html
│   ├── available-companies.html
│   ├── company-login.html
│   ├── company-dashboard.html
│   ├── css/
│   │   └── style.css
│   └── WEB-INF/
│
└── .gitignore
APPLICATION FLOW
Student Login
      ↓
Student Dashboard
      ↓
Available Companies
      ↓
Apply for Company
      ↓
Application Stored in MySQL
      ↓
Company Login
      ↓
View Applicants
      ↓
Accept / Reject
      ↓
Student Views Updated Status
