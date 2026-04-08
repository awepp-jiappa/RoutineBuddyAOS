# Sync Architecture v1

## overview

Parent App creates routine data

Cloud Firestore stores routine data

Child App reads routine data

---

## roles

Parent App
create child
create routine
create task
update routine

Child App
read routine
update completion status only

---

## sync flow

Parent save
→ firestore document created

Child app start
→ firestore read

---

## room role

Room database can be used as cache layer

supports offline usage

---

## advantages

multi device usage
parent device and child device separated
real time sync possible

---

## codex rule

firestore structure first
repository layer can abstract firestore later
for MVP direct firestore access allowed
