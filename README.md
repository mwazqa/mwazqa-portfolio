# QA Engineer Portfolio

## Overview

Welcome to my Quality Assurance portfolio. This repository serves as a comprehensive showcase of my technical expertise in modern automation frameworks and strategic testing methodologies.

My primary focus is on Quality Engineering as a value-driven process, ensuring reliability across the entire Software Development Life Cycle (SDLC). I emphasize shifting left—starting with rigorous documentation analysis and following through with robust API and UI automation to ensure a seamless user experience.

---

### API Testing (RestAssured)
**High-performance automated service layer verification.** This suite is designed to validate endpoint reliability, data integrity, and complex business logic by bypassing the graphical user interface.
* **Performance & Efficiency:** Executes tests directly against the backend, providing rapid feedback on system stability.
* **Comprehensive Validation:** Covers status codes, JSON schema validation, response headers, and payload content.
* **Decoupled Architecture:** Built using a modular approach to ensure tests remain maintainable as the API evolves.

#### Project Architecture & Directories
* **src/main/java/models** – **POJO (Plain Old Java Objects)** models used for seamless serialization and deserialization of JSON requests and responses via Jackson.
* **src/main/java/utils** – **Core Utilities** for handling environment-specific configurations, dynamic property loading, and endpoint management.
* **src/test/java/api** – **Functional Test Suite** containing specific test cases that verify business workflows and API contracts.
* **src/test/java/base** – **Test Infrastructure** providing centralized Request/Response Specifications and managing the TestNG lifecycle (Setup/Teardown).
* **src/test/java/base** – **Configuration Hub** storing `allure.properties`, environment settings, and static test data.

#### Build & Output (Maven Target)
* **target/** – The primary output directory for the Maven build process.
    * **allure-results** – Raw JSON and metadata files generated during test execution, used to build interactive Allure reports.
    * **surefire-reports** – Default TestNG execution reports and XML results.
    * **classes / test-classes** – Compiled bytecode for application and test logic.
* **pom.xml** – **Project Object Model** – the heart of the project, managing dependencies (Rest-Assured, Jackson, Allure) and build orchestration.
* **testng.xml** – **Test Suite Orchestrator** defining execution order, test grouping, and parallelization strategy.

---

### Performance Testing (Locust)
**Scalable load and stress testing infrastructure.** This project uses an event-based approach to simulate thousands of concurrent users, pinpointing system bottlenecks and measuring response stability under pressure.
* **High Scalability:** Distributed architecture utilizing Docker containers (Master-Worker) to generate massive traffic.
* **Real-world Simulation:** Uses dynamic data injection to mimic authentic user behavior and avoid server-side caching.
* **Python-based Logic:** Leverage the full power of Python to create complex testing scenarios and asynchronous requests.


#### Project Architecture & Directories
* **tasks/** – **Task Definition Hub** containing modular Python scripts (`api_tasks.py`, `ui_tasks.py`) that define specific user actions and workflows.
* **data/** – **Test Data Repository** storing static resources like books.json used for parameterizing requests and payload injection.
* **locustfile.py** – **Main Entry Point** that orchestrates the test execution, defining user classes, wait times, and the distribution of tasks.
* **.venv/** – **Isolated Virtual Environment** ensuring all Python dependencies are managed separately from the global system.
* **requirements.txt** – **Dependency Manifest** listing required libraries such as `locust`, `faker`, and `beautifulsoup4` with strict versioning.

#### Infrastructure & Deployment (Docker)
* **Dockerfile** – **Container Blueprint** that packages the Python environment, dependencies, and scripts into a portable image.
* **docker-compose.yml** – **Orchestration Layer** (located in the root) that manages the Master and Worker nodes, allowing for instant horizontal scaling.
* **locust-performance-tests.iml** – **JetBrains Configuration** file for project module management within the IntelliJ/PyCharm ecosystem.

---

#TODO: CI/CD Integration (GitHub Actions) for automated test execution on code commits and pull requests.

[//]: # (### Manual Testing Project)

[//]: # (Focuses on the testing process and documentation—the groundwork laid before the first line of test code is written.)

[//]: # ()
[//]: # (* **TestPlan** – Test strategy, scope definition, and risk-based approach.)

[//]: # ()
[//]: # (* **TestCases** – Structured test scenarios &#40;.xlsx / .md&#41; with clear steps and expected results.)

[//]: # ()
[//]: # (* **BugReports** – Professional defect reports featuring screenshots, logs, and reproduction steps.)

[//]: # ()
[//]: # (* **Checklists** – High-level verification sets for Smoke and Regression testing.)

[//]: # ()
[//]: # (---)

[//]: # ()
[//]: # (### Selenium Automation Java)

[//]: # (A comprehensive UI automation framework built with clean code principles and scalability in mind.)

[//]: # ()
[//]: # (* **src/main/java/pages** – Page Object Model &#40;POM&#41; implementation for improved maintainability.)

[//]: # ()
[//]: # (* **src/test/java/ui** – Test classes and assertions.)

[//]: # ()
[//]: # (* **driver** – Browser binaries supported by the project.)

[//]: # ()
[//]: # (* **allure-results** – Raw data used to generate visual, stakeholder-friendly reports.)

[//]: # ()
[//]: # (* **.github/workflows** – CI/CD pipeline for automated test execution on every push.)

[//]: # ()
[//]: # (* **pom.xml** – Project configuration and reporting plugins.)

[//]: # ()
[//]: # (* **README.md** – Technical requirements and setup guide.)

[//]: # ()
[//]: # (---)

[//]: # ()
[//]: # (### SQL / Database Testing)

[//]: # (Data integrity verification and backend testing performed directly on the database.)

[//]: # ()
[//]: # (* **queries** – SQL scripts used to validate data changes after UI/API actions.)

[//]: # (* **README.md** – Database structure overview and examples of database test scenarios.)

[//]: # ()
[//]: # (Each of the modules above is ready for further expansion with new test cases and integrations.)