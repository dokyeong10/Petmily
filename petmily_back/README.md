# 🐶 Petmily_BackEnd

### 👨‍ 👧‍ Developer

김정윤
오도경

## 📑 사용 기술 & 환경

- Spring Boot (Gradle)
- Openvidu WebRTC Platform
- SMTP Email Auth
- JPA
- Socket JS Chatting System
- JWT Token

## 💡 IDEA

```
Petmily : 유기동물 입양을 위한 홍보 및 정보 제공 웹 서비스
```

## 📆 개발 일정

- <Strong>1 ~ 3 주차 : 아이디어 회의 및 기본 설계</Strong>
    - 아이디어 선정
    - 요구사항 명세서 작성
    - 사이트맵 작성
    - Figma를 이용한 UI 설계
    - DB 설계

- <Strong>4 ~ 6 주차 : Front & Back 환경개발</Strong>
    - Vue3를 이용한 Front-end 개발
    - Spring Boot 를 이용한 Back-end 개발
    - WebRTC 기술을 이용한 Live System
    - SMTP 를 이용한 Email Auth
    - Socket JS를 이용한 실시간 채팅

- <Strong>7 주차 : 배포 및 유지보수 & 발표 준비</Strong>
    - AWS 배포 서버에 Jar 파일 배포
    - 테스트 데이터 삽입
    - UCC 제작

### 🧾 요구사항 명세서

![스크린샷 2021-08-18 오후 10 58 27](https://user-images.githubusercontent.com/48318620/129913357-a7370895-ddf8-441b-951f-8824c84c0585.png)

### 🧾 사이트 맵

![스크린샷 2021-08-18 오후 10 58 44](https://user-images.githubusercontent.com/48318620/129913370-43564b6e-628e-4c84-991c-04a9ecee64ef.png)

## 📌 Feature

|분류|구현기능|세부|작성여부(O/X)|
|:---:|---|---|:---:|
|Main|메인페이지 Controller||O|
|Main|회원관리|개인 회원정보 등록|O|
|Main|회원관리|기관 회원정보 등록|O|
|Main|회원관리|회원정보 인증|O|
|Main|회원관리|회원정보 찾기|O|
|Main|회원관리|회원정보 수정|O|
|Main|회원관리|회원정보 삭제|O|
|Main|회원관리|회원정보 조회 (마이페이지)|O|
|Main|로그인/로그아웃 페이지||O|
|Animal|유기동물관리|전체검색|O|
|Animal|유기동물관리|세부정보 검색|O|
|Animal|유기동물관리|유기동물 등록|O|
|Animal|유기동물관리|유기동물 수정|O|
|Animal|유기동물관리|유기동물 삭제|O|
|Animal|유기동물관리|다중 파일 등록|O|
|Animal|유기동물관리|즐겨찾기 추가|O|
|Animal|유기동물관리|즐겨찾기 삭제|O|
|Shelter|보호소관리|보호소 검색|O|
|Shelter|보호소관리|보호소 상세페이지|O|
|Shelter|보호소관리|보호소 위치 지도 표시|O|
|Community|커뮤니티관리|게시글 등록|O|
|Community|커뮤니티관리|게시글 수정|O|
|Community|커뮤니티관리|게시글 삭제|O|
|Community|커뮤니티관리|게시글 조회|O|
|Community|커뮤니티관리|댓글 등록|O|
|Community|커뮤니티관리|답글 등록|O|
|Community|커뮤니티관리|댓글 삭제|O|
|Community|커뮤니티관리|답글 삭제|O|
|Live|라이브관리|라이브 등록|O|
|Live|라이브관리|라이브 조회|O|
|Live|라이브관리|라이브 종료|O|
|Live|라이브관리|실시간 채팅|O|
|Live|라이브관리|시청자수 관리|O|


### 📃 회원 관리

- 개인 및 기관회원으로 나누어 관리
- 각 회원은 고유의 email을 가짐
- 가입 전 반드시 email 인증을 통해 가입 승인
- 개인 회원은 유기동물 즐겨찾기 추가 가능
- 기관 회원은 유기동물 등록,수정,삭제 가능
- 마이페이지에서 회원 수정 가능

### 📃 유기 동물 관리

- 유기동물 조회 가능
- Filtering 기능을 이용한 유기 동물 검색, 즐겨찾기한 동물 보기 가능
- 각 유기동물에 다중 파일 (사진 & 동영상 등) 추가 가능 -> carousel로 열람
- 유기동물 수정 가능 (세부정보, 파일 수정 등등)
- 유기동물 삭제 가능

### 📑 보호소 관리

- 보호소 상세정보 열람
- 보호소 위치 Kakao Map API 사용
- KaKao Map API를 사용한 위치찾기

### 📑 커뮤니티 관리

- 커뮤니티 글 리스트 확인
- 커뮤니티 글 검색 (제목, 내용)
- 커뮤니티 글 등록 (내용, 다중 파일 업로드 가능)
- 커뮤니티 글 수정 가능 (파일 수정, 내용 수정, 제목 수정)
- 커뮤니티 글 삭제 가능
- 댓글, 답글 달기 기능 & 댓글 갯수 파악 가능

### 📑 Live 관리

- 기관 회원은 1개의 라이브 등록 가능 (Openvidu 이용)
- 해당 라이브에 많은 회원이 들어와 시청 가능
- 라이브 시 실시간 채팅 가능 (Socket JS)
- 라이브 입장 및 퇴장 시 실시간 시청자 수 계산
- 다양한 라이브 리스트 제공

### 📑 배포 및 유지보수

- 통합 빌드 환경 구성 (Spring Boot 실행 시 Vue 자동 빌드)
- AWS 우분투 서버를 이용한 배포 (Docker로 배포가 아닌 Jar 파일 실행으로 배포)
- https 적용
- 다양한 실제 테스트 데이터 삽입
- 각종 에러 & 이슈 사항 보완

![스크린샷 2021-08-18 오후 10 58 58](https://user-images.githubusercontent.com/48318620/129913363-d38927a4-cdde-4f9b-b08b-4cd505bfe436.png)
