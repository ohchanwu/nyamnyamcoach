# Project Specification — Nyamnyamcoach (Java Track)

> Korean version: [`프로젝트_명세서.md`](프로젝트_명세서.md)

> A personalized health-coaching service driven by diet analysis
> "Java Project" Round 1 · 16th-cohort capstone (관통프로젝트) · Confidential (對外秘)

This document is a Markdown transcription of `16기_관통프로젝트_01_Java.pdf`. Future sessions can read this file instead of re-parsing the PDF.

---

## Our Team's Scope (instructor's directive — per CLAUDE.md)

- **For this initial version, build only `Member Management` (회원 관리) and `Community` (커뮤니티 기능).**
- Partner: owns the **Community** feature
- Me (the user): owns the **Member Management** feature
- Claude does not write code. It handles the work outside Java planning/coding — reading the PDF, understanding the spec, etc.

---

## 1. Java Project

### 1.1 Goals

- Design/implement a **Java console application** based on diet information.
- Apply object-oriented concepts (**encapsulation / inheritance / polymorphism**) to a real project structure.
- Implement **CRUD-based data management** using the **Collection Framework (List/Map)** and **File I/O**.
- Understand structured data formats (**JSON/XML/CSV**, etc.) and perform **serialization/deserialization** with a library.
- Experience the full development cycle: **requirements → design → implementation → verification**.
- Use **generative AI** as a design aid and feature-extension tool.

### 1.2 Prerequisites

#### 1) Project Structure

Implement a simple structure suited to a console application, but separate responsibilities for maintainability.

| Layer | Responsibility |
|-------|----------------|
| **UI (Menu)** | Print console menus and handle user input · Validate input (format/empty values, etc.) then call the Manager · Print result messages to the console |
| **Manager (business/domain)** | Handle core logic such as member management and diet CRUD · Enforce the rules needed when creating/updating/deleting data |
| **Repository (storage)** | Own in-memory storage (Collection: List/Map) · Provide file (JSON) save/load · Load on program start, save on exit/change |
| **Domain** | Define the User and diet-info classes · Define DTOs in Domain or a separate `dto` package if needed |
| **Util** | File I/O · JSON parsing · Common utility functions |

#### 2) Data Used

- Food DB (provided as CSV data)
- 2023 National Health Statistics data (provided as XLSX data)
- Member data — member-info JSON file (write it yourself if needed)

> ※ The default storage method is **file-based (File I/O)**, not a database.

#### 3) Language & Tools

- **Java 17+**
- **STS (Spring Tool Suite)**
- Console-based runtime environment

#### 4) Required Libraries / Open Source

- **GSON or Jackson** (JSON parsing and serialization)

### 1.3 Order of Work

1. Review the structure of the provided food DB and design the diet information.
2. Define the project's core features and scope.
3. Derive the core entities for member and diet.
4. Write requirements, categorized as required / additional / advanced.
5. List the data that must be stored and write an entity-definition table.
6. Based on the entity-definition table, design domain classes (User, DomainEntity, etc.).
7. Distinguish the UI (Menu), Manager, and Repository roles and write a class diagram.
8. Use generative AI to review and improve the class diagram.
9. Create the Java console project and design the package structure.
10. Implement the basic features.
11. Implement the additional features.
12. Implement (or draft) the advanced features (generative-AI-powered features/ideas).
13. Test the project and submit the deliverables.

### 1.4 Requirements

This project implements a personalized health-coaching service driven by diet analysis (hereafter **"Nyamnyamcoach"**). Using per-food nutrition data provided by the Ministry of Food and Drug Safety, it analyzes a user's diet according to their chosen goals (exercise, disease, etc.) and provides information. It also implements a Java console-based management system offering various features such as challenges for health goals, diet reviews, and an information-sharing community.

> The requirements below are reference examples. You may add/modify requirements for additional ideas, but **the required features must be implemented and cannot be changed.**

#### Functional Requirements

| No. | Category | Name | Detail | Priority |
|-----|----------|------|--------|:--------:|
| F101 | Diet | Create diet | Select consumed foods from the DB and create a diet record | **Required** |
| F102 | Diet | View diet | View a diet record's details by diet ID | **Required** |
| F103 | Diet | Edit diet | Edit the content of a created diet record | **Required** |
| F104 | Diet | Delete diet | Delete a created diet record | **Required** |
| F105 | Diet | Analyze diet | Analyze the entered diet record (by formula or AI) and present nutrition info | **Required** |
| F106 | Member | Create member | Register User info via sign-up (manage personal profile — height/weight/conditions, etc.) | **Required** |
| F107 | Member | View member | View member info | **Required** |
| F108 | Member | Edit member | Edit member info | **Required** |
| F109 | Member | Delete member | Delete member info (may switch to 'deactivated' status instead of deleting) | **Required** |
| F110 | Member | Login/Logout | Login/logout functionality | **Required** |
| F111 | Member | Follow/Following | Add/cancel follows and view the follow list | Additional |
| F112 | Challenge | Challenge info mgmt | Challenge info CRD (manage period, description, image, etc.) | Additional |
| F113 | Challenge | Challenge participation | Manage user participation, achievement progress, etc. | Additional |
| F114 | Community | Board | Create/view/edit/delete posts on diet-review, expert, and free boards | Additional |
| F115 | Community | Comment | Create/view/edit/delete comments on posts | Additional |
| F116 | AI | AI diet analysis | Diet analysis powered by generative AI | Advanced |
| F117 | AI | AI exercise coaching | Exercise coaching powered by generative AI | Advanced |

#### Non-Functional Requirements

| No. | Category | Name | Detail |
|-----|----------|------|--------|
| NF101 | UX | Usability | Must be easy to use through console menus even without prior knowledge |
| NF102 | UI | Consistency | Console menu layout and output format must stay consistent |
| NF103 | Efficiency | Responsiveness | Data lookups and analysis results must be output quickly without delay |
| NF104 | Efficiency | Accuracy | Analysis and calculation results based on stored data must be accurate |
| NF105 | Maintainability | Separation of structure | UI, Manager, Repository, and Domain roles must be clearly separated |

---

### Requirement Details

#### 1. Basic Features (Required)

**Diet Information Management**

- **F101** — Must be able to create a new record by entering diet data. The user must be able to enter data through console menus.
- **F102** — Must be able to view stored diet data. A full-list view must show all stored data; each record is distinguished by an identifiable **ID**, and a single record's details must be viewable by that ID.
- **F103** — Must be able to edit existing diet data. The user selects the **ID** of the record to edit.
- **F104** — Must be able to delete existing diet data. The user selects the **ID** of the record to delete.
- **F105** — The user must be able to analyze the entered diet record and present nutrition info (a score, etc.).

**Member Management** *(← owned by the user)*

- **F106** — Register user info via sign-up. Sign-up is performed by entering info such as ID, password, and name.
- **F107** — View member info. A logged-in user must be able to view their own member info.
- **F108** — Edit member info. The user must be able to edit their own member info.
- **F109** — Delete or deactivate member info. The user must be able to end service use via a withdrawal (account-deletion) feature.
- **F110** — Provide login/logout. The user logs in with ID and password, and **the accessible menus must differ based on login state.**

#### 2. Additional Features

- **1) Additional Member Features**
  - **F111** — The user must be able to follow other users. Implement add, view, and delete.
- **2) Challenge Features**
  - **F112** — Register/view/delete challenge info (period, description, image, etc.).
  - **F113** — The user must be able to join challenges and edit/manage info such as achievement progress.
- **3) Community Features** *(← owned by the partner)*
  - **F114** — Design boards such as diet-review, expert, and free boards; each board must support post create/view/edit/delete.
  - **F115** — The user must be able to create/view/edit/delete comments on posts.

#### 3. Advanced Features

Draft or implement features that use generative AI to extend the diet data or the system's user experience.

- **F116** — Implement a diet-analysis feature powered by generative AI.
- **F117** — Implement an exercise-coaching feature powered by generative AI.

---

### 1.5 References

- Official Java API docs by version — https://docs.oracle.com/en/java/javase/index.html
- SSAFY AI Capstone Design Guide

### 1.6 Project Deliverables & Submission

The final deliverables include the items below; organize them in the Git `README.md` or a Word/PowerPoint document, then upload to `lab.ssafy.com` to submit.

1. **Requirements definition** — required/additional/advanced requirements, with a per-feature requirements table (F101 ~ ).
2. **Class diagram** — including the Domain/UI/Manager/Repository structure, plus a summary of the generative-AI-assisted design review and improvements.
3. **Source code** — the full Java console-based project source, including Collection- and File-I/O-based data-processing logic.
4. **README.md** — project overview and how to run, full feature descriptions (split into basic/additional/advanced), and folder structure with class-role descriptions.
5. **Submission method** — submit as `Java_[topic]_region_class_name1_name2.zip`, with all deliverables included.

---

## Appendix: Provided Files (`assets/`)

The data files referenced in the spec. The JSON files below are reference material for data structures/samples. (Originally provided under `냠냠코치_제공파일/`; moved to `assets/` in this repo.)

| File | Contents |
|------|----------|
| `20241227_음식DB.xlsx` | Food DB (nutrition). Source for selecting foods when creating a diet. |
| `2023+국민건강통계.zip` | 2023 National Health Statistics data (reference for analysis/coaching). |
| `diet-data.json` | Diet-record samples + nutrition goals + weekly stats. |
| `challenges.json` | Challenge samples. |
| `community.json` | Community post/comment samples. |

### Data Structure Reference

**Diet (`diet-data.json`)** — each `meals[]` item:
```
id, date, type(아침/점심/저녁 = breakfast/lunch/dinner), foods[]{ name, calories, protein, carbs, fat }
```
- `nutritionGoals`: `{ dailyCalories, protein, carbs, fat }`
- `weeklyStats`: `{ startDate, endDate, total*, average* }`

**Challenge (`challenges.json`)** — each `challenges[]` item:
```
id, title, description, type(protein/calories/carbs/fat/meals),
goal, current, startDate, endDate, status(active), participants
```

**Community (`community.json`)** — each `posts[]` item:
```
id, title, content, author, date, category(질문/정보/식단 = question/info/diet),
likes, comments[]{ id, author, content, date }
```
