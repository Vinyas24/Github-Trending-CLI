# 🚀 GitHub Trending CLI

[![Java CI with Maven](https://github.com/Vinyas24/Github-Trending-CLI/actions/workflows/maven.yml/badge.svg)](https://github.com/Vinyas24/Github-Trending-CLI/actions/workflows/maven.yml)

A Java-based Command Line Interface (CLI) application that fetches trending GitHub repositories using the GitHub Search API.

The application allows users to search repositories by programming language and customize results using sorting, ordering, pagination, and filtering options.

---

## ✨ Features

- 🔍 Search repositories by programming language
- ⭐ Sort by stars, forks, or last updated
- 📈 Sort results in ascending or descending order
- 📄 Pagination support
- ⭐ Filter repositories by minimum stars
- 🧩 Clean Builder Pattern for API URL generation
- ⚠️ Custom exception handling
- 📖 Built-in `--help` command
- 🖥️ Clean and formatted CLI output

---

## 📂 Project Structure

```
src
└── main
    └── java
        └── com.githubtrends
            ├── builder
            ├── cli
            ├── client
            ├── exceptions
            ├── model
            ├── service
            ├── ui
            └── App.java
```

---

## 🛠️ Technologies Used

- Java 21
- Maven
- GitHub Search API
- Jackson Databind
- Java HttpClient

---

## 📦 Installation

Clone the repository

```bash
git clone https://github.com/Vinyas24/Github-Trending-CLI.git
```

Move into the project

```bash
cd Github-Trending-CLI
```

Compile the project

```bash
mvn clean compile
```

---

## ▶️ Running the Application

Basic Usage

```bash
java App <language> <count>
```

Example

```bash
java App java 10
```

---

## ⚙️ Available Options

| Option | Description | Default |
|---------|-------------|----------|
| `--sort` | stars, forks, updated | stars |
| `--order` | asc, desc | desc |
| `--page` | Page number | 1 |
| `--min-stars` | Minimum repository stars | 0 |
| `--help` | Show help information | - |

---

## 💡 Examples

Fetch 10 Java repositories

```bash
java App java 10
```

Sort by forks

```bash
java App java 10 --sort forks
```

Ascending order

```bash
java App java 10 --order asc
```

Second page

```bash
java App java 10 --page 2
```

Repositories with at least 50,000 stars

```bash
java App java 100 --min-stars 50000
```

Combine multiple options

```bash
java App java 20 --sort stars --order desc --page 2 --min-stars 10000
```

Display help

```bash
java App --help
```

---

## 🖥️ Sample Output

```
====================================
    GitHub Trending Repositories
====================================

#1 hello-algo

👤 Owner      : krahets
💻 Language   : Java
⭐ Stars       : 128,154
📎 Repository : https://github.com/krahets/hello-algo

---------------------

#2 spring-boot

👤 Owner      : spring-projects
💻 Language   : Java
⭐ Stars       : 81,063
📎 Repository : https://github.com/spring-projects/spring-boot

====================================
Total Repositories : 10
====================================
```

---

## ⚠️ Error Handling

The application validates user input and displays meaningful error messages.

Examples:

- Invalid repository count
- Invalid sort option
- Invalid order option
- Invalid page number
- Invalid minimum stars value
- Unknown CLI flags
- GitHub API rate limit exceeded
- Authentication failure
- GitHub server errors

---

## 🏗️ Design Highlights

The project follows clean object-oriented design principles.

- Builder Pattern for API URL creation
- Separate CLI parser
- Service layer for filtering
- Dedicated UI layer for printing
- Custom exception classes
- Immutable CLI argument object

---

## 📚 Future Improvements

- Unit Tests (JUnit 5)
- GitHub Personal Access Token support
- Colored terminal output
- Repository topics filter
- Export results to JSON/CSV
- Logging
- GitHub Actions CI/CD
- Executable JAR release
- Docker support

---

## 🤝 Contributing

Contributions, issues, and suggestions are welcome.

Feel free to fork the repository and submit a pull request.

---

## 📄 License

This project is licensed under the MIT License.

---

## 👨‍💻 Author

**Vinyas**

GitHub: https://github.com/Vinyas24