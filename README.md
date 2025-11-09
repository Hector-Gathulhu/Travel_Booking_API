# 🧱 Travel Booking API — Initial Entities & Repositories

## 📌 Branch
`initial-entities-repositories`

This branch represents the **foundation of the Travel Booking API project**, focusing on defining the **domain model** and the **persistence layer** using Spring Data JPA.

---

## 🧩 Current Scope

### 🗂️ Entities
Located in: `src/main/java/com/hector/travelbooking/model` (or `entities`)

Defines the core data structures of the system:
- `User` (represents a customer who can make bookings)
- `Booking` (represents a travel reservation)
- `Destination` (represents travel destinations)
- `Trip` or `TravelPackage` (represents available travel offers)

Each entity includes:
- `@Entity` and `@Table` annotations
- Proper primary key definition with `@Id` and `@GeneratedValue`
- Relationships (`@OneToMany`, `@ManyToOne`, etc.)

---

### 🧠 Repositories
Located in: `src/main/java/com/hector/travelbooking/repository`

Implements repository interfaces extending `JpaRepository<T, ID>`, providing standard CRUD operations.

Examples:
- `UserRepository`
- `BookingRepository`
- `DestinationRepository`
- `TripRepository`

---

## ⚙️ Environment Setup

**Requirements:**
- Java 17+
- Spring Boot 3+
- Maven
- Database: H2 (for development/testing) or MySQL (for production)

Sample `application.properties` (H2 setup):
```properties
spring.datasource.url=jdbc:h2:mem:traveldb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
