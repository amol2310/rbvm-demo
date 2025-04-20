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

## ✍️ Author

Created by Amol Marathe for DevSecOps MTech Dissertation.
