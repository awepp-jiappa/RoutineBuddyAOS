# Codex Task Strategy v1

## 목적
Codex가 한번에 너무 많은 코드를 생성하다 실패하는 것을 방지하기 위한 단계별 작업 전략이다.

---

## 원칙

한번에 전체 앱을 생성하지 않는다.

작업을 단계별로 나눈다.

각 단계는 build 가능한 상태여야 한다.

---

## Step 1

프로젝트 기본 구조 생성

요청 예시

Create Android project

modules:
app-parent
app-child
core

use:
Kotlin
XML
MVVM

Do not add network layer
Do not add Firebase

---

## Step 2

Room database 생성

Create:

ChildEntity
RoutineEntity
TaskEntity

Create:

ChildDao
RoutineDao
TaskDao

Create:

RoutineBuddyDatabase

빌드 가능 상태 유지

---

## Step 3

Parent 앱 화면 생성

Create screens:

ChildProfileFragment
RoutineListFragment
CreateRoutineFragment
EditRoutineFragment

Navigation 연결

---

## Step 4

Child 앱 화면 생성

Create screens:

TodayRoutineFragment
CompletedFragment

체크 UI 구현

---

## Step 5

데이터 연결

Room -> Repository -> ViewModel -> UI

데이터 표시
체크 상태 저장

---

## Step 6

UI 최소 개선

버튼
리스트
간격
텍스트 크기

디자인 과도하게 하지 않는다.

---

## Codex에게 전달 문장

Follow docs strictly.

Read first:

docs/mvp-spec-v1.md
docs/navigation-rules-v1.md
docs/data-model-v1.md
docs/room-schema-v1.md
docs/project-structure-v1.md

Implement step by step.

Do not implement extra features.

Each step must build successfully.

---

## 실패 줄이는 방법

한번에 요구사항을 많이 주지 않는다.

항상 다음 기준을 유지한다.

작게 만들고
빌드 확인하고
다음 단계 진행

---

## 목표

Codex가 자동으로

build 실패 없이

MVP 앱 구조 생성

하도록 유도한다.
