# Implementation Plan — Member Management (회원 관리)

**Owner:** me · **Branch:** `feat/login` · **Requirements:** F106–F110 (required), F111 (follow, optional)

You also own the **shared foundation** because login gates the menus and everything (including community posts) needs a logged-in author. Build the foundation first so your partner can consume it.

## Scope

| # | Feature | Notes |
|---|---------|-------|
| F106 | Register | id, password, name + profile (height, weight, disease) |
| F107 | View my info | logged-in user views own info |
| F108 | Edit my info | edit own info |
| F109 | Delete / deactivate | withdrawal → delete OR flip status to INACTIVE |
| F110 | Login / logout | menus available differ by login state |
| F111 | Follow (optional) | add / cancel / list following |

## Layers to build (per spec §1.2)

```
com.ssafy.nyamnyamcoach
├─ Main                       # entry point + root menu (login-gated)   [SHARED]
├─ ui/        MemberMenu       # register / login / my info / edit / withdraw
├─ manager/   UserManager      # business rules for the above
├─ repository/UserRepository   # Map<String,User> + load/save users.json
├─ domain/    User, Session    # Session holds the current logged-in user  [SHARED]
├─ dto/       (as needed)
└─ util/      JsonUtil, FileUtil  # GSON wrapper + file I/O               [SHARED]
```

## Data model — `User`

`id` (or loginId), `password`, `name`, `height`, `weight`, `diseases` (List/String), `status` (ACTIVE/INACTIVE), `followingIds` (List, for F111), `createdAt`.
Persist to `assets/users.json` (spec: write the member JSON yourself — no seed provided). Shape: `{ "users": [ { ...User } ] }`.

## Order of work

1. **Project scaffold** — create the Java 17+ project (Gradle or plain), add **GSON** dependency, lay out the packages above.
2. **`util`** — `JsonUtil` (GSON serialize/deserialize generic list) + `FileUtil` (read/write file, create if missing). *This is shared — get it in early.*
3. **`domain`** — `User`, `Session` (current user, `isLoggedIn()`).
4. **`repository`** — `UserRepository`: load `users.json` on startup, save on change; find by id, existence check for duplicate id.
5. **`manager`** — `UserManager`: register (validate + dup check), findMyInfo, update, deactivate/delete, `login(id, pw)`, `logout()`.
6. **`ui`** — `MemberMenu` for the flows; **`Main` root menu** that shows guest options (register / login) vs. member options (my info / edit / withdraw / logout / → community) based on `Session`.
7. **F111 (if time)** — follow/unfollow/list on `UserManager` via `followingIds`.
8. Create/seed `assets/users.json`.

## Integration points (what your partner depends on)

- Expose **`Session.getCurrentUser()`** (name + id) so community posts/comments can stamp the author.
- **`Main` root menu** delegates to your partner's `CommunityMenu` once logged in — agree on that single entry-point method signature.
- Agree with your partner that **`JsonUtil`/`FileUtil` are yours**; they reuse them (don't duplicate) to avoid merge conflicts.
