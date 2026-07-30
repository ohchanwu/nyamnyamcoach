# 공유 계약

> English version: [`contracts.md`](contracts.md)

회원 관리(본인)와 커뮤니티(파트너)가 만나는 두 개의 연동 지점.
**이 시그니처를 먼저 합의**한 뒤 양쪽이 병렬로 작업한다 — 커뮤니티 쪽은 실제 구현이 완성되기 전까지 스텁/가짜 객체로 개발한다. 소유: 회원 관리 (`feat/login`).

패키지: `com.ssafy.nyamnyamcoach`

---

## 1. Session — 현재 로그인 사용자

**소유:** 회원 관리 · **사용:** 커뮤니티 (글/댓글 작성자)

```java
package com.ssafy.nyamnyamcoach.domain;

public class Session {
    public static User getCurrentUser();   // 로그인 안 됐으면 null
    public static boolean isLoggedIn();
}
```

`User` 는 최소한 다음을 제공해야 한다:

```java
public String getId();
public String getName();
```

**로그인 완성 전까지:** 커뮤니티 쪽은 가짜 사용자(예: `"테스트유저"`)로 테스트하고, 마지막에 `Session.getCurrentUser()` 로 교체한다.

---

## 2. JsonUtil / FileUtil — JSON + 파일 입출력 유틸

**소유:** 회원 관리 · **사용:** 커뮤니티 (재사용, 중복 작성 금지)

```java
package com.ssafy.nyamnyamcoach.util;

public class JsonUtil {
    // 객체/리스트를 JSON 문자열로 직렬화
    public static String toJson(Object obj);

    // JSON 배열 문자열을 List<T> 로 역직렬화
    // type 예시: new TypeToken<List<Post>>(){}.getType()
    public static <T> List<T> fromJsonList(String json, Type type);
}

public class FileUtil {
    public static String read(String path);           // 파일 전체 -> 문자열 (없으면 "")
    public static void   write(String path, String content);  // 덮어쓰기, 없으면 생성
}
```

**GSON 2.11.0** 기반 (이미 `lib/` 에 포함됨).

**완성 전까지:** 커뮤니티 쪽은 동일한 시그니처로 임시 스텁을 두고 진행한다.

---

## 3. 메뉴 진입점 (마지막 연동만)

`Main` 의 로그인 게이트 루트 메뉴가 로그인 후 커뮤니티 메뉴를 호출한다. 메서드 하나만 합의한다:

```java
package com.ssafy.nyamnyamcoach.ui;

public class CommunityMenu {
    public void show();   // 커뮤니티 루프 실행, 종료 시 루트 메뉴로 복귀
}
```

이 연결만 회원 관리 완료 후 진행하면 되는 유일한 단계다.
