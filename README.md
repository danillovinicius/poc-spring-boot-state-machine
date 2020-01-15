# POC Spring boot State Machine


How to run application
---------------------------------------------
```bash
## Build application from base directory
mvn clean install

## Run Spring boot application with embedded H2 database
mvn spring-boot:run

## Or if You want to run application with production profile
mvn spring-boot:run -Dspring.profiles.active=prod
```