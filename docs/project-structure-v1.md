# Project Structure Guide v1

## 목적
Codex가 불필요한 구조를 생성하지 않도록 기준 구조를 정의한다.

---

## 전체 구조

RoutineBuddyAOS

app-parent
app-child
core

---

## app-parent

부모용 루틴 관리 앱

예상 구조

ui
- child
- routine

viewmodel

repository

data
- entity
- dao
- db

---

## app-child

아이용 루틴 수행 앱

예상 구조

ui
- today
- complete

viewmodel

repository

data
- entity (shared 가능)
- dao
- db

---

## core 모듈

공통 코드 위치

예상 내용

model
util
extensions

---

## 패키지 예시

com.routinebuddy.parent

com.routinebuddy.child

com.routinebuddy.core

---

## 화면 목록 기준

Parent

ChildProfileScreen
RoutineListScreen
CreateRoutineScreen
EditRoutineScreen

Child

TodayRoutineScreen
CompletedScreen

---

## 생성 금지 항목

Codex는 다음을 생성하지 않는다.

analytics
firebase
network layer
login
multi profile
notification

---

## 아키텍처 원칙

MVVM

View
Fragment

ViewModel
state 관리

Repository
Room 접근

Database
Room

---

## 네이밍 규칙

Fragment

RoutineListFragment

ViewModel

RoutineListViewModel

Repository

RoutineRepository

---

## MVP 목표

구조를 단순하게 유지한다.
확장 가능성은 고려하되 지금 구현하지 않는다.
