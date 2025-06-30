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


### 5. 과제 내용

![](https://velog.velcdn.com/images/grammi_boii/post/279e269c-9ec8-4769-b69d-7f3b2a239d13/image.png)

- AWS ALB, RDS, EC2, Route 53을 사용해 배포
- 서브 도메인 사용
  - vote.loopz.co.kr (이번 과제 경로)
  - api.loopz.co.kr (프로젝트 경로)
  - loopz.co.kr (메인 페이지) OR vercel

![](https://velog.velcdn.com/images/grammi_boii/post/7dc5496e-559b-4ff1-8846-b763bdbe6764/image.png)


데이터 초기화

![](https://velog.velcdn.com/images/grammi_boii/post/ae2e2545-e401-4be8-bfb0-a76090a12cdf/image.png)


아키텍쳐

![](https://velog.velcdn.com/images/grammi_boii/post/685a977d-06d0-4dc0-be23-b3252c6caf4a/image.png)


모니터링 - vote에 미완성

![](https://velog.velcdn.com/images/grammi_boii/post/78a49eb3-1371-4e3b-a2ca-8991641f8dc1/image.png)

액츄에이터
```
{
    "status": 200,
    "message": "OK",
    "data": {
        "status": "UP",
        "components": {
            "db": {
                "status": "UP",
                "details": {
                    "database": "PostgreSQL",
                    "validationQuery": "isValid()"
                }
            },
            "diskSpace": {
                "status": "UP",
                "details": {
                    "total": 30083776512,
                    "free": 20612050944,
                    "threshold": 10485760,
                    "path": "/.",
                    "exists": true
                }
            },
            "ping": {
                "status": "UP"
            },
            "redis": {
                "status": "UP",
                "details": {
                    "version": "8.0.1"
                }
            },
            "ssl": {
                "status": "UP",
                "details": {
                    "validChains": [],
                    "invalidChains": []
                }
            }
        }
    }
}
```

api - postman

![](https://velog.velcdn.com/images/grammi_boii/post/c32bd316-9fc5-4c45-839d-01466ae9b2b8/image.png)

공통 응답 포맷 - intercpetor 구현


![](https://velog.velcdn.com/images/grammi_boii/post/27d03bfa-5ff8-4960-8de8-479233732ee7/image.png)


```
{
    "status": 200,
    "message": "OK",
    "data": {
        "candidateType": "PART_LEADER",
        "candidates": [
            {
                "id": 1,
                "part": "BACKEND",
                "name": "김준형"
            },
            {
                "id": 2,
                "part": "BACKEND",
                "name": "임도현"
            },
            {
                "id": 3,
                "part": "BACKEND",
                "name": "박정하"
            },
            {
                "id": 4,
                "part": "BACKEND",
                "name": "서채연"
            },
            {
                "id": 5,
                "part": "BACKEND",
                "name": "이석원"
            },
            {
                "id": 6,
                "part": "BACKEND",
                "name": "최근호"
            },
            {
                "id": 7,
                "part": "BACKEND",
                "name": "오지현"
            },
            {
                "id": 8,
                "part": "BACKEND",
                "name": "한혜수"
            },
            {
                "id": 9,
                "part": "BACKEND",
                "name": "박서연"
            },
            {
                "id": 10,
                "part": "BACKEND",
                "name": "박채연"
            },
            {
                "id": 11,
                "part": "FRONTEND",
                "name": "김철흥"
            },
            {
                "id": 12,
                "part": "FRONTEND",
                "name": "송아영"
            },
            {
                "id": 13,
                "part": "FRONTEND",
                "name": "권동욱"
            },
            {
                "id": 14,
                "part": "FRONTEND",
                "name": "김서연"
            },
            {
                "id": 15,
                "part": "FRONTEND",
                "name": "신수진"
            },
            {
                "id": 16,
                "part": "FRONTEND",
                "name": "원채영"
            },
            {
                "id": 17,
                "part": "FRONTEND",
                "name": "김영서"
            },
            {
                "id": 18,
                "part": "FRONTEND",
                "name": "이주희"
            },
            {
                "id": 19,
                "part": "FRONTEND",
                "name": "최서연"
            },
            {
                "id": 20,
                "part": "FRONTEND",
                "name": "한서정"
            }
        ]
    }
}
```

```java
package com.ceos.vote.global.handler;

import com.ceos.vote.global.dto.CommonResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseInterceptor implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (body instanceof CommonResponse) {
            return body;
        }

        int status = ((ServletServerHttpResponse) response).getServletResponse().getStatus();

        // swagger 제외
        String path = request.getURI().getPath();
        if (path.contains("swagger") || path.contains("api-docs") || path.contains("webjars")) {
            return body;
        }

        // 조건부 메시지 처리: 2xx -> "Success", 그 외 -> "Error"
        String message = (status >= 200 && status < 300) ? "OK" : "Error";

        CommonResponse<Object> commonResponse = new CommonResponse<>();
        commonResponse.setStatus(status);
        commonResponse.setMessage(message);
        commonResponse.setData(body);

        // 응답을 String으로 내는 경우 따로 예외처리
        if (body instanceof String) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.writeValueAsString(commonResponse);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("String response conversion error", e);
            }
        }
        return commonResponse;
    }

}

```