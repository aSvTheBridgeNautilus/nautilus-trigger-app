# Transfers & Balance

- Core api and stream processor for both microservice

## Pre-requisites 📋

- jdk 11
- maven
- docker & docker-compose
- git
- kafkatool (optional to check records published on kafka)


## Usage ⚙️

- git clone both projects: [transfers-ms](https://github.com/Nautilus-team/core-transfers-ms.git) and [balance-ms](https://github.com/Nautilus-team/core-balances-ms.git). checkout **develop** branch

- run below comand on **docker-environment/kafka-cluster/** and **docker-environment/postgres-pgadmin4/**

```bash
docker-compose up
```

- check **application-local.yaml** for connection string to database

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/compose-postgres
    username: compose-postgres
    password: compose-postgres
```

- run both the core-api and stream processor for each project from IDE or go to their directory and run

```bash
mvn spring-boot:run
```

- finally, go to localhost:5050 and login with credentials

```
user: admin@example.com 
password: admin123
```
and run the script **inserts_balance.sql**