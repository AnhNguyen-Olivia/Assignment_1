# Assignment_1
This is the github for Java_RMI assignment 1 the project is about apply the knowledge from https://www.tutorialspoint.com/java_rmi/index.htm
Using docker for the sql. The choodsen folder for the assignment is the Maven folder. The guide to how to run will be in a obsidian note but hopefully I will update it to the github

# Java RMI + PostgreSQL Tutorial (Assignment 1)

This guide explains how to run the Java RMI database project in:
- `JavaRMI_DBmitMavenTest/test`
The app has:
- An RMI server (`rmidatabase.Server`)
- An RMI client (`rmidatabase.Client`)
- A PostgreSQL database queried by the server
## 1. Prerequisites
Install these tools first:
- Java 21 (required by `pom.xml`)
- Maven 3.8+
- Docker Desktop (with `docker compose`)
Verify:
```powershell
java -version
mvn -version
docker --version
docker compose version
```
## 2. Go to the Maven Project Folder
From the repository root:
```powershell
cd .\JavaRMI_DBmitMavenTest\test
```
All commands below are run from this folder unless noted.
## 3. Start PostgreSQL (and pgAdmin)
Run Docker Compose using the file included in the Java package folder:
```powershell
docker compose -f .\src\main\java\rmidatabase\docker-compose.yml up -d
```
This starts:
- PostgreSQL on `localhost:5432`
- pgAdmin on `http://localhost:5050`
Database credentials used by the Java code:
- DB: `retail_db`
- User: `postgres`
- Password: `1234`
## 4. Import the SQL Schema + Sample Data
Use PowerShell to pipe the SQL file into PostgreSQL container:
```powershell
Get-Content ".\src\main\java\rmidatabase\Retail Sales Database.sql" | docker exec -i postgres-db psql -U postgres -d retail_db
```
Expected result: table creation + many `INSERT` confirmations.
## 5. Build the Project
```powershell
mvn clean compile
```
## 6. Run the RMI Server
Open Terminal 1 in `JavaRMI_DBmitMavenTest/test`:
```powershell
mvn -q exec:java -Dexec.mainClass=rmidatabase.Server
```
Expected output includes:
```text
Server is ready! :D
```
Keep this terminal running.
## 7. Run the RMI Client
Open Terminal 2 in `JavaRMI_DBmitMavenTest/test`:
```powershell
mvn -q exec:java -Dexec.mainClass=rmidatabase.Client
```
You should see output like:
- Server uptime
- Message length response
- Current time in `Asia/Ho_Chi_Minh`
- A list of `BEER` items from PostgreSQL
## 8. Stop Services
Stop the app:
- Press `Ctrl + C` in the server/client terminals.
Stop containers:
```powershell
docker compose -f .\src\main\java\rmidatabase\docker-compose.yml down
```
## Common Issues
1. `Connection refused` to DB
	- Make sure Docker containers are running: `docker ps`
	- Confirm PostgreSQL is on port `5432`
2. `relation "retail_items" does not exist`
	- SQL file was not imported yet. Run Step 4 again.
3. `Client exception` on RMI lookup
	- Start server first (Step 6), then client (Step 7).
4. Java version errors during Maven compile
	- Ensure Java 21 is active in your terminal.
## Quick Run Checklist
1. `cd .\JavaRMI_DBmitMavenTest\test`
2. `docker compose -f .\src\main\java\rmidatabase\docker-compose.yml up -d`
3. Import SQL file
4. `mvn clean compile`
5. Run server terminal
6. Run client terminal

# How to shutdown cleanly
## 1) Clean Workspace (Java + Maven)
Run from project folder:
```powershell
cd E:\VGU_Summer_26-26\Distributed_System\Project\Assignment_1\JavaRMI_DBmitMavenTest\test
mvn clean
```
Optional deep clean (remove IDE and build leftovers):
```powershell
cd E:\VGU_Summer_26-26\Distributed_System\Project\Assignment_1\JavaRMI_DBmitMavenTest\test
Remove-Item -Recurse -Force target -ErrorAction SilentlyContinue
```
## 2) Clean RMI Processes
If old RMI/Java processes are still running and causing port conflicts:
```powershell
Get-NetTCPConnection -LocalPort 1099 -ErrorAction SilentlyContinue | Select-Object -ExpandProperty OwningProcess -Unique
```
Then stop the process IDs returned:
```powershell
Stop-Process -Id <PID> -Force
```
Also close old terminals running:
- rmiregistry
- Server process
## 3) Clean Docker (Project Scope)
From the same folder, use your compose file path:
```powershell
docker compose -f src/main/java/rmidatabase/docker-compose.yml down
```
To fully reset database data too (recommended when re-importing from SQL file):
```powershell
docker compose -f src/main/java/rmidatabase/docker-compose.yml down -v
```
What this does:
- Stops containers
- Removes containers
- With -v: removes the postgres volume so DB starts fresh
# Note
Visit the Obsidian note for more infomation. In the future I will host the obsidian note somewhere but right now it is on github
