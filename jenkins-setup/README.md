# 🔧 Jenkins DevSecOps CI Setup – RBVM Scanner

This setup runs Jenkins with:
- All required plugins pre-installed
- A seeded pipeline job for [amol2310/rbvm-demo](https://github.com/amol2310/rbvm-demo)
- Docker support for scanning images
- Streamlit dashboard exposure at port 8501

---

## 🚀 How to Use

### 1. Build and Start Jenkins

```bash
docker-compose up --build -d
```

Then open:

👉 http://localhost:8080

---

### 2. Unlock Jenkins

```bash
docker exec jenkins-rbvm cat /var/jenkins_home/secrets/initialAdminPassword
```

Paste the password into the Jenkins UI and complete setup.

---

### 3. Preinstalled Pipeline

After startup, the pipeline job **RBVM-demo** will be automatically created and configured to:
- Pull from `https://github.com/amol2310/rbvm-demo.git`
- Watch the `main` branch
- Auto-trigger via SCM polling every minute

---

### 4. View Dashboard

Once pipeline runs, open:
👉 http://localhost:8501

To view the full Streamlit-based RBVM dashboard.

---

## 🛠️ Prerequisites for Local Demo

To run the full DevSecOps demo with Jenkins + RBVM + AI Fix Dashboard, ensure the following are installed on your system:

---

### 1. 🐳 Docker Desktop

- Required for building and scanning Docker images
- Jenkins uses Docker socket to trigger scans

**Install:**
- Download Docker Desktop for Windows or Mac:  
  👉 https://www.docker.com/products/docker-desktop/
- For Linux: use your package manager (e.g. `apt install docker.io`)

Verify:
```bash
docker --version
```

---

### 2. 🧠 Ollama (for local AI fix suggestions)

Used to run LLaMA 3 locally and provide AI-powered remediation in the RBVM dashboard.

**Install:**
- 👉 https://ollama.com/download
- Available for Windows, macOS, and Linux

Start the LLaMA model:
```bash
ollama run llama3
```

---

### 3. 🐍 Python 3.11 (optional, for local development)

- Required only if you want to run the scanner outside Docker

Install via:
- https://www.python.org/downloads/release/python-31110/

Verify:
```bash
python3.11 --version
```

---

### 4. ✅ Recommended Tools

| Tool      | Purpose                      | Install via                   |
|-----------|-------------------------------|-------------------------------|
| Git       | Clone GitHub repo             | https://git-scm.com/download |
| Make      | Optional scripting convenience| `choco install make` (Win)   |
| jq        | JSON parser for shell use     | `apt install jq` / `choco install jq` |

---

## ✍️ Author

Created by [Amol Marathe](https://github.com/amolmarathe) for DevSecOps MTech Dissertation.
