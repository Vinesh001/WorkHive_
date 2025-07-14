# 💼 WorkHive - Freelance Project Management System

WorkHive is a Java-based freelance job management system that simulates a real-world freelancing platform. It allows clients to post projects, freelancers to bid on them, and both parties to manage contracts, progress, and payments through a clean, menu-driven console application.

---

## 📁 Project Structure


---

## 🎯 Features

### 👤 User Roles:
- **Client**: Posts projects, views bids, accepts bids, pays freelancers.
- **Freelancer**: Views available projects, places bids, submits progress, requests completion.
- **Admin** *(optional)*: Can be extended for moderation, analytics, or reports.

---

### ✅ Functional Highlights:

#### For Clients:
- Post new projects with title, description, budget, and deadline.
- View all posted projects.
- View bids placed by freelancers.
- Accept a bid to generate a contract.
- View freelancer progress updates.
- Pay freelancers and mark contracts as completed.
- View active vs. completed contracts and payment history.

#### For Freelancers:
- Browse open projects.
- Place a bid with proposal and bid amount.
- Track all your bids.
- View active contracts.
- Submit progress updates.
- Mark contract as complete (request payment).
- View closed contracts and payment history.

---

## 🧠 Technologies Used

- **Java 8+**
- **JDBC** for database interaction
- **MySQL** as relational DB
- **MVC-inspired architecture**
- Clean **OOP design** with separation of concerns

---

## 🗄️ Database Schema

Use the SQL script in `src/main/resources/` or below to initialize the database:

```sql
CREATE DATABASE workhive;
USE workhive;

-- Table definitions: users, projects, bids, contracts, milestones, payments
-- See /resources/schema.sql for full DDL
