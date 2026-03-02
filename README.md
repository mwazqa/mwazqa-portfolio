# QA Engineer Portfolio

## O projekcie

W tym repozytorium gromadzę swoje projekty techniczne, które najlepiej oddają moje podejście do zapewniania jakości. Skupiam się na dostarczaniu wartości na każdym etapie cyklu życia oprogramowania – od analizy dokumentacji, przez testy API, aż po pełną automatyzację UI.

**Stack technologiczny:**
* **Manual Testing:** Projektowanie strategii i raportowanie.
* **API Testing:** RestAssured (Java).
* **UI Automation:** Selenium WebDriver + Java (Page Object Model).
* **Database:** SQL (walidacja danych).
* **CI/CD:** GitHub Actions + Allure Reports.

### Manual Testing Project
Sekcja skupiona na procesie i dokumentacji, czyli wszystkim tym, co dzieje się przed napisaniem pierwszej linii kodu testowego.

* **TestPlan** – Strategia testów i podejście do analizy ryzyka.
* **TestCases** – Scenariusze testowe (format .xlsx / .md) z jasno określonymi krokami i wynikami.
* **BugReports** – Przykłady raportowania defektów (zrzuty ekranu, logi, kroki do reprodukcji).
* **Checklists** – Zbiory szybkich sprawdzeń (Smoke & Regression), by nie tracić czasu na oczywistości.

### API Testing (RestAssured)
Automatyczna weryfikacja warstwy usług. Testy te sprawdzają niezawodność endpointów oraz poprawność logiki biznesowej bez angażowania interfejsu graficznego.

* **src/main/java/models** – Modele danych (POJO) wykorzystywane do serializacji i deserializacji żądań oraz odpowiedzi.
* **src/main/java/utils** – Klasy pomocnicze, służące do zarządzania konfiguracją środowiskową i plikami właściwości.
* **src/test/java/api** – Funkcjonalne testy API weryfikujące konkretne procesy.
* **src/test/java/base** – Klasy bazowe zawierające wspólną konfigurację, specyfikacje żądań i odpowiedzi oraz logikę przed i po testach.
* **allure-results** – Surowe dane z wykonania testów, wykorzystywane do generowania interaktywnych raportów Allure.
* **pom.xml** – Plik konfiguracyjny Maven definiujący zależności projektu, m.in. RestAssured, Jackson oraz framework testowy TestNG.
* **README.md** – Dokumentacja techniczna zawierająca instrukcję uruchomienia i opis zakresu testów API.

### Selenium Automation Java
Kompleksowy framework do testowania interfejsu użytkownika, zbudowany zgodnie z dobrymi praktykami programistycznymi.

* **src/main/java/pages** – Implementacja wzorca Page Object Model (POM) dla lepszej czytelności kodu.
* **src/test/java/ui** – Klasy testowe z asercjami.
* **drivers** – Binaria przeglądarek wspierane przez projekt.
* **allure-results** – Dane wejściowe do generowania czytelnych raportów graficznych.
* **.github/workflows** – Pipeline CI/CD, dzięki któremu testy uruchamiają się automatycznie w chmurze.
* **pom.xml** – Konfiguracja projektu i pluginów raportujących.
* **README.md** – Instrukcja setupu i wymagania techniczne.

### SQL / Database Testing
Weryfikacja integralności danych i testowanie backendu bezpośrednio na bazie.

* **queries** – Skrypty SQL wykorzystywane do walidacji zmian po akcjach w UI/API.
* **README.md** – Opis struktury bazy i przykłady scenariuszy bazodanowych.

Każdy z powyższych modułów jest gotowy do dalszej rozbudowy o nowe przypadki testowe i integracje.

---

# QA Engineer Portfolio

## Overview

This repository showcases my technical approach to Quality Assurance. My focus is on delivering value across the entire Software Development Life Cycle (SDLC)—from initial documentation analysis and API validation to robust UI automation.

**Core Tech Stack:**
* **Manual Testing:** Strategy design, risk assessment, and defect reporting.
* **API Testing:** RestAssured (Java).
* **UI Automation:** Selenium WebDriver + Java (Page Object Model).
* **Database:** SQL (data integrity and backend validation).
* **CI/CD:** GitHub Actions + Allure Reports.

### Manual Testing Project
Focuses on the testing process and documentation—the groundwork laid before the first line of test code is written.

* **TestPlan** – Test strategy, scope definition, and risk-based approach.
* **TestCases** – Structured test scenarios (.xlsx / .md) with clear steps and expected results.
* **BugReports** – Professional defect reports featuring screenshots, logs, and reproduction steps.
* **Checklists** – High-level verification sets for Smoke and Regression testing.

### API Testing (RestAssured)
Automated service layer verification. These tests validate endpoint reliability and business logic without the overhead of a graphical user interface.

* **src/main/java/models** – Data models (POJO) used for serialization and deserialization of requests and responses.
* **src/main/java/utils** – Utility classes used for managing environment configuration and property files.
* **src/test/java/api** – Functional API tests verifying specific business processes.
* **src/test/java/base** – Base classes containing common configuration, request/response specifications, and setup/teardown logic.
* **allure-results** – Raw test execution data used to generate interactive Allure reports.
* **pom.xml** – Maven configuration file defining project dependencies, including RestAssured, Jackson, and the TestNG testing framework.
* **README.md** – Technical documentation containing execution instructions and API test scope description.

### Selenium Automation Java
A comprehensive UI automation framework built with clean code principles and scalability in mind.

* **src/main/java/pages** – Page Object Model (POM) implementation for improved maintainability.
* **src/test/java/ui** – Test classes and assertions.
* **driver** – Browser binaries supported by the project.
* **allure-results** – Raw data used to generate visual, stakeholder-friendly reports.
* **.github/workflows** – CI/CD pipeline for automated test execution on every push.
* **pom.xml** – Project configuration and reporting plugins.
* **README.md** – Technical requirements and setup guide.

### SQL / Database Testing
Data integrity verification and backend testing performed directly on the database.

* **queries** – SQL scripts used to validate data changes after UI/API actions.
* **README.md** – Database structure overview and examples of database test scenarios.

Each of the modules above is ready for further expansion with new test cases and integrations.
