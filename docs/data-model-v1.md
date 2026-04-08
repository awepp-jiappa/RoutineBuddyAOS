# Data Model v1

## Child

| field | type |
|------|------|
| id | String |
| name | String |
| ageGroup | String |

---

## Routine

| field | type |
|------|------|
| id | String |
| childId | String |
| title | String |

---

## Task

| field | type |
|------|------|
| id | String |
| routineId | String |
| title | String |
| order | Int |
| isCompleted | Boolean |
