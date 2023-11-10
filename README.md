# vending-machine

# Needed tools how to start and build the project.

  1) Docker
  2) Postman
  3) MySQL Workbench (or similar tool) for DB
  4) Maven

# Commands and steps to run the project with Docker

  1) Locate project folder and execute mvn clean package -DskipTests (tests are being skipped here because there are no spring profiles added)
  2) Build the app docker image -> docker build --no-cache -t vending-machine:latest .
  3) Run the MySQL docker image and add environmental variables for URL, User, Pass -> docker run -d -p 3307:3306 --name mysql-container -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=jdbc:mysql://localhost:3306/vending-machine?createDatabaseIfNotExist=true -e MYSQL_USER=test -e MYSQL_PASSWORD=123 mysql:latest
  4) After MySQL starts (might need to start it manually if docker run command fails), start vending-machine image -> docker run -p 8080:8080 vending-machine:latest and add the relevant environmental variables either in the CLI or via the GUI.
  5) After everything starts use Postman to reach the endpoints.

# Steps to test the project locally

  1) Download the project and start it via an IDE (IntelliJ, Eclipse etc..).
  2) Use Postman and Workbench to verify the endpoints.
  3) Run test manually.
