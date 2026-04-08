# Room Schema Guide v1

## 목적
RoutineBuddyAOS에서 Parent/Child 앱의 MVP 데이터를 로컬에 저장하기 위한 Room 기준 문서이다.

이번 버전은 서버/Firebase 동기화 없이 로컬 저장만 고려한다.

---

## 1. Entity 목록

### ChildEntity
부모가 등록한 자녀 정보

| field | type | note |
|------|------|------|
| id | String | primary key |
| name | String | 아이 이름 |
| ageGroup | String | 연령대 |
| createdAt | Long | 생성 시각 |

---

### RoutineEntity
하나의 루틴 묶음

| field | type | note |
|------|------|------|
| id | String | primary key |
| childId | String | 자녀 id |
| title | String | 루틴 이름 |
| icon | String | optional |
| orderIndex | Int | 정렬 순서 |
| createdAt | Long | 생성 시각 |
| updatedAt | Long | 수정 시각 |

---

### TaskEntity
루틴 내부 개별 할 일

| field | type | note |
|------|------|------|
| id | String | primary key |
| routineId | String | routine foreign key |
| title | String | 할 일 이름 |
| orderIndex | Int | 정렬 순서 |
| isCompleted | Boolean | 오늘 완료 여부 로컬 상태 |
| createdAt | Long | 생성 시각 |
| updatedAt | Long | 수정 시각 |

---

## 2. 관계

- Child 1 : N Routine
- Routine 1 : N Task

즉 한 아이는 여러 루틴을 가질 수 있고,
하나의 루틴은 여러 task를 가진다.

---

## 3. MVP 저장 원칙

### Parent App
- ChildEntity 저장
- RoutineEntity 저장
- TaskEntity 저장

### Child App
- Parent에서 생성된 루틴/태스크를 읽어와 표시
- task 체크 상태는 MVP에서는 TaskEntity.isCompleted 로 저장 가능

---

## 4. DAO 가이드

### ChildDao
필요 기능
- getChild()
- insertChild()
- updateChild()

### RoutineDao
필요 기능
- getAllRoutines(childId)
- getRoutineById(routineId)
- insertRoutine()
- updateRoutine()
- deleteRoutine()

### TaskDao
필요 기능
- getTasksByRoutineId(routineId)
- insertTasks()
- updateTask()
- updateTaskChecked()
- deleteTasksByRoutineId(routineId)

---

## 5. Room Database 구성

예상 Database:
- RoutineBuddyDatabase

포함 entity:
- ChildEntity
- RoutineEntity
- TaskEntity

---

## 6. 주의사항

### MVP 단순화 원칙
- CompletionHistory 테이블은 아직 만들지 않는다.
- Reward 테이블은 아직 만들지 않는다.
- DailySummary 테이블은 아직 만들지 않는다.

지금은 루틴 생성/표시/체크 흐름을 먼저 완성한다.

---

## 7. 추후 확장 예정

향후 버전에서 추가 가능:
- CompletionHistoryEntity
- RewardEntity
- DailyProgressEntity
- ParentSummaryEntity

---

## 8. Codex 구현 지침

Codex는 아래 원칙을 따른다.

1. Room entity는 과도하게 늘리지 않는다.
2. MVP에서는 Child / Routine / Task 3개 테이블 중심으로 구현한다.
3. nullable 남용 금지
4. orderIndex를 활용해 루틴 및 task 순서를 보장한다.
5. DAO는 화면 요구사항에 맞는 최소 메서드만 먼저 구현한다.
