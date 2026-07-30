# 구현 계획 — 커뮤니티 기능

**담당:** 파트너 · **브랜치:** `feat/login` · **요구사항:** F114(게시판), F115(댓글)

로그인/세션·JSON 유틸·`Main` 루트 메뉴 등 **공통 기반**은 회원 관리 담당(파트너)이 먼저 만든다. 커뮤니티는 그 위에서 동작하므로, 공통 기반이 준비된 뒤 시작하는 것이 매끄럽다. (작성자 정보는 로그인 세션에서 받아온다.)

## 범위

| # | 기능 | 상세 |
|---|------|------|
| F114 | 게시판 | 식단 리뷰 / 전문가 / 자유 게시판. 각 게시판에서 글 **등록·조회·수정·삭제** |
| F115 | 댓글 | 게시글에 댓글 **작성·조회·수정·삭제** |

## 구현 계층 (명세 §1.2 구조)

```
com.ssafy.nyamnyamcoach
├─ ui/         CommunityMenu    # 게시판 선택 → 글/댓글 CRUD 메뉴
├─ manager/    PostManager      # 글·댓글 비즈니스 로직 (규칙/검증)
├─ repository/ PostRepository   # List<Post> + community.json 로드/저장
├─ domain/     Post, Comment, BoardType(enum)
└─ util/       JsonUtil, FileUtil   ← 회원 관리 담당이 만든 것을 재사용 (중복 작성 금지)
```

## 데이터 모델 (`assets/community.json` 시드 참고)

- **`BoardType`** (enum): `식단리뷰`, `전문가`, `자유`
- **`Post`**: `id`, `boardType`, `title`, `content`, `author`, `date`, `category`, `likes`, `comments`(List)
- **`Comment`**: `id`, `author`, `content`, `date`

기존 `assets/community.json`의 `posts[]` 구조(댓글 중첩 포함)를 그대로 매핑하면 된다. `boardType` 필드는 시드에 없으므로 추가한다.

## 작업 순서

1. **domain** — `BoardType`, `Post`, `Comment` 클래스 정의 (`community.json` 구조에 맞춤).
2. **repository** — `PostRepository`: 시작 시 `community.json` 로드, 변경 시 저장. ID로 글/댓글 조회, 게시판별 목록 조회. *(JSON 처리는 공통 `JsonUtil` 재사용)*
3. **manager** — `PostManager`:
   - 글: 등록 / 상세 조회 / 게시판별 목록 / 수정 / 삭제
   - 댓글: 작성 / 목록 / 수정 / 삭제
   - 작성자는 로그인 세션에서 가져옴 (아래 연동 지점 참고).
4. **ui** — `CommunityMenu`: 게시판 선택 → 글 목록/상세 → 글 CRUD → 댓글 CRUD. 출력 형식은 회원 메뉴와 일관성 유지(NF102).
5. 테스트: 글/댓글 작성 후 프로그램 재시작 시 `community.json`에 저장·복원 확인.

## 연동 지점 (회원 관리 담당과 합의)

- 작성자 정보는 **`Session.getCurrentUser()`** 로 받는다 (직접 로그인 구현 X).
- `CommunityMenu`는 **`Main` 루트 메뉴**에서 로그인 후 진입한다 — 진입 메서드 시그니처를 서로 맞춘다.
- **`JsonUtil`/`FileUtil`** 은 회원 관리 담당이 만든 것을 재사용한다 (병합 충돌 방지).
