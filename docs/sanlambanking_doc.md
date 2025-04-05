# Banking Withdrawal System - Code Improvement Submission

## 1. Summary of Approach

This refactor improves a basic banking withdrawal operation by:

- Separating concerns into Controller, Service, Repository, and Publisher layers.
- Adding transaction management, logging, and exception handling.
- Introducing a loosely coupled event publishing mechanism via AWS SNS.
- Ensuring testability, auditability, and maintainability.
- Using Spring idioms and domain-driven principles.

The core business capability -- debit an account and publish a withdrawal event -- remains unchanged.

---

## 2. Implementation Highlights

| Area             | Improvements Made                                                              |
|------------------|---------------------------------------------------------------------------------|
| Architecture     | Layered structure (Controller -> Service -> Repo/EventPublisher)               |
| Fault Tolerance  | Exception handling for DB access, insufficient funds, SNS failures             |
| Data Integrity   | Ensured atomic updates via @Transactional                                       |
| Observability    | Introduced structured logging via SLF4J                                         |
| Maintainability  | Domain-specific exceptions, POJOs, and interface-driven components              |
| Flexibility      | Easily swappable SNS logic (via EventPublisher interface)                       |
| Portability      | No hard AWS coupling in core domain logic                                       |
| Auditability     | Withdrawal events now explicitly recorded as JSON payloads                      |

---

## 3. Unclear Library Usages Clarified

| Library/Tool     | Purpose                                 |
|------------------|------------------------------------------|
| SnsClient        | AWS SDK v2 for sending events to SNS     |
| ObjectMapper     | Jackson JSON serialization               |
| JdbcTemplate     | Spring JDBC helper for DB queries        |
| @Transactional   | Ensures DB operations are atomic         |
| Slf4j Logger     | Structured, pluggable logging            |

---


