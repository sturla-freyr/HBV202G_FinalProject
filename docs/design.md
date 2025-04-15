# Design Documentation – Task Management System (TMS)

This document describes the architecture, patterns, and key components used in the Task Management System project.

---

## 📐 Architecture Overview

The project follows a **layered architecture** separating:

- **Domain Layer (`domain`)**: Core models such as `Task`, `Project`, and their attributes.
- **Service Layer (`service`)**: Business logic for managing tasks and projects.
- **UI Layer (`ui`)**: Text-based user interface and observer implementation.

---

## 🧩 Design Patterns Used

### ✅ Observer Pattern

Used to notify the user interface of changes in task/project data. This keeps the UI reactive and decoupled from business logic.

**Participants:**

- **Subject interface**: Defines `addObserver()`, `removeObserver()`, `notifyObservers(String message)`
- **Observer interface**: Defines `update(String message)`

**Implementations:**

| Role     | Class                  |
|----------|------------------------|
| Subject  | `TaskService`, `ProjectService` |
| Observer | `TextUI`               |

When a task or project is added, removed, or changed, the relevant service notifies all observers, and the UI prints a message.

---

### ✅ Composite Pattern

The Composite Pattern is used to represent the hierarchical relationship between `Project` and `Task`. Both `Project` and `Task` implement the `WorkItem` interface, allowing them to be treated uniformly. A `Project` can contain multiple `WorkItem` instances, enabling nested structures of tasks and sub-projects.

**Participants:**

- **Component interface**: `WorkItem` – defines common operations for both `Task` and `Project`.
- **Leaf**: `Task` – represents individual tasks with no children.
- **Composite**: `Project` – represents a collection of `WorkItem` instances, which can include both `Task` and `Project`.

**Implementations:**

| Role       | Class       |
|------------|-------------|
| Component  | `WorkItem`  |
| Leaf       | `Task`      |
| Composite  | `Project`   |

**Example Usage:**

- A `Project` can contain multiple `Task` objects or even other `Project` objects, forming a tree-like structure.
- Operations like `getDetails()` or `calculateTotalEffort()` can be called on a `Project`, which will recursively aggregate data from its children.

---

## 📦 Packaging and Execution

- The application is packaged using the **Maven Assembly Plugin** into a "fat JAR" that includes all dependencies.
- A `run.sh` script allows users to run the system without needing Maven or an IDE.

```bash
java -jar target/task-management-system-1.0-SNAPSHOT-jar-with-dependencies.jar
```

---

## 🔧 Refactoring & Code Quality

Effort was made to ensure:

- Clear and consistent **method naming** (`createTask`, `updateProject`)
- **Separation of concerns** between logic (services) and UI
- Use of **Java coding conventions** taught in HBV101G (e.g., camelCase, PascalCase, proper indentation)

The code is modular, reusable, and easy to extend (e.g., adding support for deadlines, categories, or persistence).

---

## 🗂️ Class Overview

| Class/Interface      | Description                                      |
|----------------------|--------------------------------------------------|
| `Main`               | Entry point – starts the UI                      |
| `TextUI`             | Console interface; observes services             |
| `Task`, `Project`    | Domain models                                    |
| `TaskStatus`, `TaskPriority` | Enumerations for task state and urgency       |
| `TaskService`, `ProjectService` | Business logic and state for tasks/projects |
| `Observer`, `Subject`| Observer pattern interfaces                      |
| `WorkItem`           | Interface for both tasks and projects            |

---

## 📊 UML Class Diagram

The full UML class diagram representing this architecture is located here:

📁 `docs/UML.png`

This diagram includes:

- Class relationships
- Interface implementations
- Enumerations (`TaskStatus`, `TaskPriority` using «enumeration» stereotype)
- `WorkItem` as the common interface.
- `Task` and `Project` as its implementations.
- A recursive relationship where `Project` can contain other `WorkItem` instances.

---

## ✅ Summary

The Task Management System is a cleanly structured, pattern-driven project designed to demonstrate strong fundamentals in:

- Java OOP design
- Design patterns (Observer, Composite)
- MVC-style separation
- Build automation and documentation