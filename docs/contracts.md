# Shared Contracts (공유 계약)

The two integration points where member-management (me) and community (partner) meet.
**Agree on these signatures first**, then both sides code in parallel against them — the community side uses stubs/fakes until the real implementations land. Owner: member-management (`feat/login`).

Package: `com.ssafy.nyamnyamcoach`

---

## 1. Session — current logged-in user

**Owner:** member-management · **Consumer:** community (author of posts/comments)

```java
package com.ssafy.nyamnyamcoach.domain;

public class Session {
    public static User getCurrentUser();   // null if not logged in
    public static boolean isLoggedIn();
}
```

`User` must expose at least:

```java
public String getId();
public String getName();
```

**Until login is ready:** the community side tests with a fake user (e.g. `"테스트유저"`) and swaps in `Session.getCurrentUser()` at the end.
커뮤니티 담당은 로그인 완성 전까지 가짜 사용자로 테스트하고, 마지막에 `Session.getCurrentUser()` 로 교체한다.

---

## 2. JsonUtil / FileUtil — JSON + file I/O helpers

**Owner:** member-management · **Consumer:** community (reuse, do NOT duplicate)

```java
package com.ssafy.nyamnyamcoach.util;

public class JsonUtil {
    // serialize any object/list to a JSON string
    public static String toJson(Object obj);

    // deserialize a JSON array string into a List<T>
    // type example: new TypeToken<List<Post>>(){}.getType()
    public static <T> List<T> fromJsonList(String json, Type type);
}

public class FileUtil {
    public static String read(String path);           // whole file -> string ("" if missing)
    public static void   write(String path, String content);  // overwrite, create if missing
}
```

Backed by **GSON 2.11.0** (already in `lib/`).

**Until these land:** the community side stubs them with the same signatures so nothing is blocked.
커뮤니티 담당은 같은 시그니처로 임시 스텁을 두고 진행한다.

---

## 3. Menu entry point (final wiring only)

`Main`'s login-gated root menu calls into the community menu once logged in. Agree on one method:

```java
package com.ssafy.nyamnyamcoach.ui;

public class CommunityMenu {
    public void show();   // runs the community loop; returns to root menu on exit
}
```

This is the only step that truly waits on member-management.
이 연결만 회원 관리 완료 후 진행하면 된다.
