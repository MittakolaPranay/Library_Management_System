#  Library Management System — Backend

Java Servlets + JDBC backend for a simple Library Management System.  
It provides authentication (register/login), book CRUD APIs, and serves dFeatures
-  User authentication (Register/Login with sessions).  
-  CRUD operations for books (Add, View, Update, Delete).  
-  Search books by title, author, or ISBN.  
-  Image upload & static serving via Tomcat context.  
-  Session-based authentication for protected endpoints.  

##  Tech Stack
- **Java** (JDK 17+)  
- **Servlet API** (Jakarta EE / Tomcat 10+)  
- **JDBC** for database access  
- **MySQL** (8.x)  
- **Tomcat** as servlet container  

---

##  Project Structure

backend/
       src/#models,utils,servlets
       config.properties
       lib/#JDBC Driver,JSON-20250517.jar
       README.md


##  Configurations

    'config.properties' : 
              db.url=jdbc:mysql://localhost:3306/library_db
              db.username=root
              db.password=password



##  Database Schema (Overview)
- **users** → `id, name, email, password, role`  
- **books** → `id, title, author, isbn, copies, availabe, imagePath`  
- **transactions** → `id, userId, bookId, issue_dAte, due_date, return_date, status(enum('issued','returned','overdue'))`



##  Deployment
- Build project into a WAR file.  
- Copy WAR to Tomcat’s `webapps/`.  
- Configure context for images on server.  
- Restart Tomcat.  


