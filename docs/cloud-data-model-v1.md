# Cloud Data Model v1

## Firestore structure

families
   familyId
      children
         childId
      routines
         routineId
      tasks
         taskId

---

## Child document fields

childId
name
ageGroup
createdAt

---

## Routine document fields

routineId
childId
title
orderIndex
createdAt
updatedAt

---

## Task document fields

taskId
routineId
title
orderIndex
createdAt
updatedAt
isCompleted
completedAt

---

## example

families/family123

children/child1
{
 name: "child name"
 ageGroup: "7-9"
}

routines/routine1
{
 childId: "child1"
 title: "morning routine"
}

tasks/task1
{
 routineId: "routine1"
 title: "brush teeth"
}

---

## design rules

query routines by childId
query tasks by routineId
use orderIndex for sorting
