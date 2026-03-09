[![Website](https://img.shields.io/badge/Website-dragonflyqa.xyz-8a2be2?style=for-the-badge&labelColor=000000&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAyNCAyNCIgZmlsbD0id2hpdGUiPjxwYXRoIGQ9Ik0xMiAxTDE1IDRMMTggMUwyMSA0TDE4IDdMMjEgMTBMMTggMTNMMTUgMTBMMTIgMTNMMTkgMjBMMTcgMjJMMTIgMTdMNyAyMkw1IDIwTDEyIDEzTDkgMTBMNiAxM0wzIDEwTDYgN0wzIDRMNiAxTDkgNEwxMiAxWiIvPjwvc3ZnPg==)](https://dragonflyqa.xyz)

# QA Engineer Portfolio

## Overview

This repository showcases my technical approach to Quality Assurance. My focus is on delivering value across the entire Software Development Life Cycle (SDLC)—from initial documentation analysis and API validation to robust UI automation.

---

**Core Tech Stack:**
* **Manual Testing:** Strategy design, risk assessment, and defect reporting.
* **API Testing:** RestAssured (Java).
* **UI Automation:** Selenium WebDriver + Java (Page Object Model).
* **Database:** SQL (data integrity and backend validation).
* **CI/CD:** GitHub Actions + Allure Reports.

---

### Manual Testing Project
Focuses on the testing process and documentation—the groundwork laid before the first line of test code is written.

* **TestPlan** – Test strategy, scope definition, and risk-based approach.
* **TestCases** – Structured test scenarios (.xlsx / .md) with clear steps and expected results.
* **BugReports** – Professional defect reports featuring screenshots, logs, and reproduction steps.
* **Checklists** – High-level verification sets for Smoke and Regression testing.

---

### API Testing (RestAssured)
Automated service layer verification. These tests validate endpoint reliability and business logic without the overhead of a graphical user interface.

* **src/main/java/models** – Data models (POJO) used for serialization and deserialization of requests and responses.
* **src/main/java/utils** – Utility classes used for managing environment configuration and property files.
* **src/test/java/api** – Functional API tests verifying specific business processes.
* **src/test/java/base** – Base classes containing common configuration, request/response specifications, and setup/teardown logic.
* **allure-results** – Raw test execution data used to generate interactive Allure reports.
* **pom.xml** – Maven configuration file defining project dependencies, including RestAssured, Jackson, and the TestNG testing framework.
* **README.md** – Technical documentation containing execution instructions and API test scope description.

---

### Selenium Automation Java
A comprehensive UI automation framework built with clean code principles and scalability in mind.

* **src/main/java/pages** – Page Object Model (POM) implementation for improved maintainability.
* **src/test/java/ui** – Test classes and assertions.
* **driver** – Browser binaries supported by the project.
* **allure-results** – Raw data used to generate visual, stakeholder-friendly reports.
* **.github/workflows** – CI/CD pipeline for automated test execution on every push.
* **pom.xml** – Project configuration and reporting plugins.
* **README.md** – Technical requirements and setup guide.

---

### SQL / Database Testing
Data integrity verification and backend testing performed directly on the database.

* **queries** – SQL scripts used to validate data changes after UI/API actions.
* **README.md** – Database structure overview and examples of database test scenarios.

Each of the modules above is ready for further expansion with new test cases and integrations.
