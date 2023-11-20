
<details>
	<summary>기존 프로젝트 - 익명게시판 만들기</summary>
    
# AnonymousForum
익명게시판 만들기

## 필수 요구 사항
- 게시글 작성 기능
    - `제목`, `작성자명`, `비밀번호`, `작성 내용`, `작성일`을 저장할 수 있습니다.
    - 저장된 게시글의 정보를 반환 받아 확인할 수 있습니다.
        - 반환 받은 게시글의 정보에 `비밀번호`는 제외 되어있습니다.
- 선택한 게시글 조회 기능
    - 선택한 게시글의 정보를 조회할 수 있습니다.
        - 반환 받은 게시글의 정보에 `비밀번호`는 제외 되어있습니다.
- 게시글 목록 조회 기능
    - 등록된 게시글 전체를 조회할 수 있습니다.
        - 반환 받은 게시글의 정보에 `비밀번호`는 제외 되어있습니다.
    - 조회된 게시글 목록은 작성일 기준 내림차순으로 정렬 되어있습니다.
- 선택한 게시글 수정 기능
    - 선택한 게시글의 `제목`, `작성자명`, `작성 내용`을 수정할 수 있습니다.
        - 서버에 게시글 수정을 요청할 때 `비밀번호`를 함께 전달합니다.
        - 선택한 게시글의 `비밀번호`와 요청할 때 함께 보낸 `비밀번호`가 일치할 경우에만 수정이 가능합니다.
    - 수정된 게시글의 정보를 반환 받아 확인할 수 있습니다.
        - 반환 받은 게시글의 정보에 `비밀번호`는 제외 되어있습니다.
- 선택한 게시글 삭제 기능
    - 선택한 게시글을 삭제할 수 있습니다.
        - 서버에 게시글 삭제를 요청할 때 `비밀번호`를 함께 전달합니다.
        - 선택한 게시글의 `비밀번호`와 요청할 때 함께 보낸 `비밀번호`가 일치할 경우에만 삭제가 가능합니다.

## 유스케이스 다이아그램
![image](https://github.com/hana2set/AnonymousForum/assets/97689567/1d899a1b-ea32-4530-b8d0-1d465b9b1aec)

## API 명세서
![api](https://github.com/hana2set/AnonymousForum/assets/97689567/8af10097-f1f2-4eb6-96cf-88a1eed81a65)

## ERD
![image](https://github.com/hana2set/AnonymousForum/assets/97689567/cec60368-2576-4cd5-bd00-1745827a3817)



</details>


# 투두앱 백엔드 서버 
- 기존 프로젝트를 수정하여 회원가입, 로그인 기능이 있는 투두앱 백엔드 서버 만들기

<br> 

<details>
	<summary> 필수 요구 사항 </summary>    

 ## 필수 요구 사항

-  **회원 가입 API**
    - username, password를 Client에서 전달받기
    - username은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 한다.
    - password는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성되어야 한다.
    - DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기
    - 참고자료
        1. https://mangkyu.tistory.com/174
        2. [https://ko.wikipedia.org/wiki/정규_표현식](https://ko.wikipedia.org/wiki/%EC%A0%95%EA%B7%9C_%ED%91%9C%ED%98%84%EC%8B%9D)
        3. https://bamdule.tistory.com/35
            
            
-  **🆕 로그인 API**
    - username, password를 Client에서 전달받기
    - DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기
    - 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고, 
    발급한 토큰을 Header에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기
-  **~~게시글~~ 할일카드 작성 기능 API**
    - 토큰을 검사하여, 유효한 토큰일 경우에만 할일 작성 가능
    - `할일 제목`,`할일 내용`, `작성일`을 저장할 수 있습니다. (~~작성자명, 비밀번호)~~
    - 할일 제목, 할일 내용을 저장하고
    - 저장된 할일을 Client 로 반환하기(username은 로그인 된 사용자)
-  **선택한 ~~게시글~~ 할일카드  조회 기능 API**
    - 선택한 ~~게시글~~ 할일 의 정보를 조회할 수 있습니다.
        - 반환 받은 할일 정보에는 `할일 제목`,`할일 내용`, `작성자` , `작성일`정보가 들어있습니다.
        - ~~반환 받은 게시글의 정보에 비밀번호는 제외 되어있습니다.~~
-  **~~게시글~~ 할일카드 목록 조회 기능 API**
    - 등록된 할일 전체를 조회할 수 있습니다.
        - 회원별로 각각 나누어서 할일 목록이 조회됩니다.
        - 반환 받은 할일 정보에는 `할일 제목`, `작성자` , `작성일`, `완료 여부`정보가 들어있습니다.
        - ~~반환 받은 할일 정보에 비밀번호는 제외 되어있습니다.~~
    - 조회된 할일 목록은 `작성일` 기준 내림차순으로 정렬 되어있습니다.
-  **선택한 ~~게시글~~ 할일카드 수정 기능 API**
    - 선택한 ~~게시글~~ 할일카드의 `제목`, `작성 내용`을 수정할 수 있습니다. (~~작성자명~~)
        - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 수정 가능
        - 할일 제목, 할일 내용을 수정하고 수정된 할일 정보는 Client 로 반환됩니다.
        - ~~서버에 게시글 수정을 요청할 때 비밀번호를 함께 전달합니다.~~
        - ~~선택한 게시글의 비밀번호와 요청할 때 함께 보낸 비밀번호가 일치할 경우에만 수정이 가능합니다.~~
    - 수정된 ~~게시글~~ 할일의 정보를 반환 받아 확인할 수 있습니다.
        - 반환 받은 할일 정보에는 `할일 제목`,`할일 내용`, `작성자` , `작성일`정보가 들어있습니다.
        - ~~반환 받은 게시글의 정보에 비밀번호는 제외 되어있습니다.~~
-  **🆕 할일카드 완료 기능 API**
    - **API 가 사용될 화면 보기**
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 할일카드 만 완료 가능
    - 완료처리 한 할일카드는 목록조회시 `완료 여부`필드가 TRUE 로 내려갑니다.
    - `완료 여부` 기본값은 FALSE
-  **🆕 댓글 작성 API**
    - 토큰을 검사하여, 유효한 토큰일 경우에만 댓글 작성 가능
    - 선택한 할일의 DB 저장 유무를 확인하기
    - 선택한 할일이 있다면 댓글을 등록하고 등록된 댓글 반환하기
-  **🆕 댓글 수정 API**
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 댓글만 수정 가능
    - 선택한 댓글의 DB 저장 유무를 확인하기
    - 선택한 댓글이 있다면 댓글 수정하고 수정된 댓글 반환하기
-  **🆕 댓글 삭제 API**
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 댓글만 삭제 가능
    - 선택한 댓글의 DB 저장 유무를 확인하기
    - 선택한 댓글이 있다면 댓글 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기
-  **🆕 예외 처리 (ResponseEntity 사용)**
    - 토큰이 필요한 API 요청에서 토큰을 전달하지 않았거나 정상 토큰이 아닐 때는 "토큰이 유효하지 않습니다." 라는 에러메시지와 statusCode: 400을 Client에 반환하기
    - 토큰이 있고, 유효한 토큰이지만 해당 사용자가 작성한 게시글/댓글이 아닌 경우에는 “작성자만 삭제/수정할 수 있습니다.”라는 에러메시지와 statusCode: 400을 Client에 반환하기
    - DB에 이미 존재하는 username으로 회원가입을 요청한 경우 "중복된 username 입니다." 라는 에러메시지와 statusCode: 400을 Client에 반환하기
    - 로그인 시, 전달된 username과 password 중 맞지 않는 정보가 있다면 "회원을 찾을 수 없습니다."라는 에러메시지와 statusCode: 400을 Client에 반환하기

</details>

## 디렉토리 구조
```cmd
─src
│  build.gradle			# Gradle 빌드 스크립트 파일
│  settings.gradle		# Gradle 설정 파일
│  README.md			# 프로젝트에 대한 설명이 담긴 마크다운 파일
└─main
   ├─java
   │  └─com
   │      └─study
   │          └─todocard
   │              ├─controller	
   │              ├─dto		
   │              ├─entity	# Entity 디렉토리
   │              ├─exception	# 커스텀 예외처리 디렉토리
   │              ├─handler	# global handler 디렉토리
   │              ├─repository	 
   │              ├─security	# 보안관련 로직 디렉토리
   │              │  └─dto	
   │              └─service	
   └─resources
	└─application.properties # 속성 파일
```

## API 명세서
https://documenter.getpostman.com/view/30923517/2s9Ye8hFxu
![image](https://github.com/hana2set/todocard/assets/97689567/e3fb7f1d-d731-497d-9cb0-f29ba01ea3a5)
![image](https://github.com/hana2set/todocard/assets/97689567/2421c155-4a24-4b38-8bbf-88696054570c)
![image](https://github.com/hana2set/todocard/assets/97689567/bab71b0d-6298-42b8-a034-b8daca064ce1)
![image](https://github.com/hana2set/todocard/assets/97689567/01a739a8-109f-40ee-b2a0-c9571d59d7e2)
![image](https://github.com/hana2set/todocard/assets/97689567/84abc5b5-ac64-485c-a977-1d9c4123281d)
![image](https://github.com/hana2set/todocard/assets/97689567/50ea7ca9-bc72-45d1-8dd4-424a211be78e)



## ERD
![image](https://github.com/hana2set/todocard/assets/97689567/727bb702-5b41-49db-84c1-beddd38e6a8f)
