## LOOPZ : Vote-BE

### Git Convention
#### 1. Branch 전략
- 브랜치명 : ``` feat/{이슈번호}/{작업설명}``` 
- **일회성 브랜치 -> 지속 브랜치** 시 ```squash merge``` 사용
 
  ex) feat -> develop 
- **지속 브랜치 -> 지속 branch** 시 ```일반 merge```
  
  ex) develop -> main

#### 2. Commit convention
- ```feat:, fix:, docs:, refactor:, chore:``` 등으로 시작
- commit message 본문에 작업 상세 내용 기재

#### 3. PR/ISSUE convention
#### 📌 이슈 트래킹 규칙

- **Issue 등록 후** 작업 시작

- Issue 제목 형식:
```
  [FEAT]: 설명  
  [FIX]: 설명  
  [CHORE]: 설명
```
- ```Assignee``` 지정
- ```Label``` 설정

  **사용 가능한 라벨 종류**:

    - 🐞 **BugFix**: 버그 수정
    - ✨ **Feature**: 기능 개발
    - 🔨 **Refactor**: 코드 리팩토링
    - 📃 **Docs**: 문서 작성 및 수정
    - ⚙ **Setting**: 개발 환경 세팅
    - 🌏 **Deploy**: 배포 관련 작업

#### 🔀 Pull Request 규칙
- PR 제목 형식:
```
[FEAT] : 기능 설명
[FIX] : 버그 설명
[CHORE] : 기타 설명
```
- ```Label``` 설정
- 본문에 이슈 번호 연결 ```(close #1)```

- ```Squash Merge``` 시 커밋 메시지에 이슈 번호 포함

   - ex) ```feat: Google OAuth 구현 (#17)```

- PR 본문에 작업 내용 & 통합 테스트 스크린샷 첨부

#### 4. Code Review
- PR 작성 시 **Reviewer 지정**
- **1명 이상 승인(approve)** 후 merge 가능
- merge 전 Gemini Code Review를 통한 1차 자체 수정
- 본인 PR은 본인 **직접 Merge**
