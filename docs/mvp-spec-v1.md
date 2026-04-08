# RoutineBuddy MVP Specification v1

## 1. 앱 개요

RoutineBuddy는 부모가 아이의 일과 루틴을 생성하고,
아이는 루틴을 수행하며 습관을 형성하도록 돕는 앱이다.

앱 구성:

### Parent App
- 루틴 생성
- 루틴 수정
- 루틴 목록 관리

### Child App
- 오늘의 루틴 수행
- 체크 완료
- 진행률 확인
- 완료 경험 제공

참고 와이어프레임:
- `docs/parent-wireframe.pdf`
- `docs/child-wireframe.pdf`
- `docs/concept-wireframe.pdf`

---

## 2. MVP 범위

이번 버전에서는 다음 기능만 구현한다.

### Parent
- 자녀 프로필 생성
- 루틴 생성
- 루틴 수정
- 루틴 목록 조회

### Child
- 오늘의 루틴 목록
- 루틴 체크
- 진행률 표시
- 완료 화면

### 제외 기능
- 로그인
- 푸시 알림
- Firebase
- 보상 시스템
- 멀티 자녀 관리

---

## 3. UX 방향

앱은 단순하고 직관적이어야 한다.

### Parent
- 빠르게 루틴 생성 가능해야 한다.
- 입력 과정이 복잡하지 않아야 한다.

### Child
- 아이 스스로 수행 가능해야 한다.
- 화면은 복잡하지 않아야 한다.
- 완료 여부와 진행률이 명확해야 한다.

---

## 4. 기술 스택

- Language: Kotlin
- UI: Android XML View system
- Architecture: MVVM
- Local database: Room

---

## 5. 모듈 구조

- `app-parent` : 부모용 앱
- `app-child` : 아이용 앱

---

## 6. 완료 기준

다음이 가능하면 완료로 본다.

1. 부모가 자녀 프로필을 생성할 수 있다.
2. 부모가 루틴을 생성하고 수정할 수 있다.
3. 아이가 오늘의 루틴을 체크할 수 있다.
4. 진행률이 화면에 반영된다.
5. 앱 재실행 시 데이터가 유지된다.
