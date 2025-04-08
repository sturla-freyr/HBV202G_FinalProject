# HBV202G_FinalProject

# Task Management System (TMS)

A Java-based command-line application for creating, updating, and organizing tasks and projects.  
The system follows an MVC-inspired structure and uses the **Observer Design Pattern** for UI updates.

---

## 🚀 Features

- Create and manage **tasks** with:
    - Title
    - Description
    - Deadline
    - Status (`NOT_STARTED`, `IN_PROGRESS`, `COMPLETED`)
    - Priority (`LOW`, `MEDIUM`, `HIGH`)
- Create and manage **projects** that group tasks
- Assign tasks to projects
- Track project progress (based on task completion %)
- Sort/filter tasks by status, priority, or deadline
- Receive live **console notifications** for changes via Observer pattern

---

## 🧠 Design Highlights

- **Observer Pattern**
    - `Subject`: `TaskService`, `ProjectService`
    - `Observer`: `TextUI` receives update messages when data changes
- **Layered architecture**
    - `domain`: core data models
    - `service`: business logic
    - `ui`: text-based user interface

---

## 📂 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── is/hi/tms/
│   │       ├── domain/      # Data models: Task, Project, Enums
│   │       ├── service/     # Application logic: TaskService, ProjectService
│   │       ├── ui/          # Text-based interface and observer interfaces
│   │       └── Main.java    # Application entry point
│   └── resources/
├── test/
│   └── java/                # Unit tests
```

---

## 🔧 Build & Run

### 🛠 Requirements:
- Java 17+
- Maven 3.8+

### 🔄 Compile:
```bash
mvn compile
```

### ▶️ Run the app:
```bash
mvn exec:java -Dexec.mainClass="is.hi.tms.Main"
```

> Make sure the `exec-maven-plugin` is configured in your `pom.xml`.

---

## 📦 Maven Goals

| Goal             | Description                                |
|------------------|--------------------------------------------|
| `mvn compile`    | Compile the application                    |
| `mvn test`       | Run unit tests (if any)                    |
| `mvn package`    | Build a JAR (if configured)                |
| `mvn javadoc:javadoc` | Generate Javadoc to `target/site/apidocs` |
| `mvn site`       | Generate project reports & documentation   |

---

## 📄 Documentation

- [Design Documentation](docs/design.md)
- [UML Class Diagram](docs/UML.png)
- Javadoc: `target/site/apidocs/index.html` (after `mvn javadoc:javadoc`)

---

## 📘 License

This project is dedicated to the public domain under the [CC0 1.0 Universal (Public Domain Dedication)](LICENSE).  
You can copy, modify, distribute, and perform the work, even for commercial purposes, all without asking permission.