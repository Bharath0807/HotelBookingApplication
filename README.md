
## 🔧 Pre-requisites

Before running the application, make sure you have the following installed:

### ✅ Local Setup (Linux / macOS / Windows with Git Bash)

1. **Java 17**
   - Verify installation:
     ```bash
     java -version
     ```
   - If not installed, download from:
     - [Adoptium / Temurin](https://adoptium.net/)
     - [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)

2. **Maven**
   - Verify installation:
     ```bash
     mvn -version
     ```
   - If not installed, download from:
     - https://maven.apache.org/download.cgi

3. **Git Bash (Windows only)**
   - Needed to run `run.sh` on Windows.
   - Install Git for Windows:
     - https://git-scm.com/download/win

---

### ✅ Notes

- The project uses **Java 17** features.
- `run.sh` is a Linux shell script. On Windows, use **Git Bash** or **WSL**.
- If you prefer a Windows-native approach, use the provided `run.bat` script (optional).


## 🧠 How it Works

### Strategy Pattern
- `AllocationService` orchestrates the allocation process.
- Each strategy handles one part of the allocation:
  - `PremiumAllocationStrategy`
  - `EconomyAllocationStrategy`
  - `UpgradeAllocationStrategy`

### Guest Classification
- `GuestClassifier` classifies bids into premium and economy categories using BigDecimal.


## 🚀 Running the Application

### Build

```bash
mvn clean package

Run
java -jar target/*.jar

🏃 Running with run.sh

run.sh is a helper script to build the project, generate the JAR, and run it automatically.

✅ What run.sh does

Runs Maven build (mvn clean package -DskipTests)

Finds the generated JAR in target/

Runs the JAR using the java command

⚙️ How to run locally (Linux/macOS/Git Bash)
chmod +x run.sh
./run.sh
