# RoutineBuddy MVP Specification v2

## 핵심 개념

RoutineBuddy는 Parent App 과 Child App 이
Cloud Database 를 통해 데이터를 공유하는 구조이다.

---

## 데이터 흐름

Parent App
→ 루틴 생성
→ Cloud 저장

Child App
→ Cloud 데이터 조회
→ 루틴 수행

---

## Source of Truth

Cloud Database 를 Single Source of Truth 로 사용한다.

Local DB(Room)는 캐시 용도로 사용 가능하다.

---

## MVP 범위

이번 버전에서는 다음 흐름까지 구현한다.

### Parent
- child 생성
- routine 생성
- task 생성
- cloud 저장

### Child
- cloud 데이터 조회
- routine 표시
- task 체크

---

## 제외 기능

로그인 UI
푸시 알림
결제
보상 시스템

---

## 기술 스택

Android Kotlin
MVVM
Room (cache)
Firebase Firestore

---

## Codex 구현 원칙

Read the markdown docs as the implementation source of truth.
Use the PDF wireframes as UI layout references.
Follow the markdown rules strictly, and use the PDFs to match screen structure and flow.

---

## 중요 규칙

Parent 가 데이터를 생성한다.
Child 는 데이터를 삭제하지 않는다.
Child 는 완료 상태만 업데이트 가능하다.
