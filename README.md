# microservice-interaction

A minimal Spring Cloud demo showing service discovery + gateway routing + service-to-service calls.

| Module             | Role                                   | Port | Eureka name |
|--------------------|----------------------------------------|------|-------------|
| `discovery-server` | Eureka registry / discovery            | 8761 | -           |
| `api-gateway`      | Single entry point for users           | 8080 | API-GATEWAY |
| `app1`             | Business service 1                     | 8081 | APP1        |
| `app2`             | Business service 2                     | 8082 | APP2        |

## Endpoints

Through the gateway (what a user calls):

| Request                                      | Routed to | Behaviour                                                        |
|----------------------------------------------|-----------|-----------------------------------------------------------------|
| `GET http://localhost:8080/app1/aggregate`   | APP1      | App1 calls `http://APP2/app2/data` (resolved via Eureka), returns a string embedding App2's response |
| `GET http://localhost:8080/app1/standalone`  | APP1      | Returns a static string, no downstream call                     |
| `GET http://localhost:8080/app2/data`        | APP2      | Returns a static string (also callable by App1)                 |

## Flow

```
User -> api-gateway (8080) --(Eureka lookup)--> app1 (8081) --(Eureka lookup)--> app2 (8082)
User -> api-gateway (8080) --(Eureka lookup)--> app2 (8082)
```

## Run (Java 21 required)

Start each in its own terminal, in this order:

```bash
./gradlew :discovery-server:bootRun
./gradlew :api-gateway:bootRun
./gradlew :app1:bootRun
./gradlew :app2:bootRun
```

- Eureka dashboard: http://localhost:8761 (wait until APP1, APP2, API-GATEWAY show up)
- Then: `curl http://localhost:8080/app1/aggregate`

## Build

```bash
./gradlew build
```
