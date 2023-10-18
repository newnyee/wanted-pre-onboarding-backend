# wanted-pre-onboarding-backend
프리온보딩 백엔드 인턴십 선발과제


### 💻 프로젝트 소개
- 기업의 채용을 위한 웹 서비스
- 회사는 채용공고를 생성하고, 이에 사용자는 지원할 수 있음
------------


### ✔ 프리온보딩 요구사항
 1. 요구사항(의도)을 만족시킨다면 다른 형태의 요청 및 리스폰스를 사용하여도 좋습니다.
 2. 필요한 모델: 회사, 사용자, 채용공고, 지원내역(선택사항) (이외 추가 모델정의는 자유입니다.)
 3. 위 제공된 필드명은 예시이며, 임의로 생성 가능합니다.
 4. 회사, 사용자 등록 절차는 생략합니다. (DB에 임의로 생성하여 진행)
 5. 로그인 등 사용자 인증절차(토큰 등)는 생략합니다.
 6. Frontend 요소(html, css, js 등)는 개발 범위에 제외됩니다. (구현시 불이익은 없지만, 평가에 이점 또한 없습니다.)
 7. 명시되지 않은 조건또한 자유롭게 개발 가능합니다.
------------


### ✔ 요구사항 분석
 1. 사용자 가입은 아이디, 이름, 거주지역, 사용기술, 희망포지션을 입력해야합니다.
 2. 사용자는 사용자 아이디(userId)로 식별합니다. 

 
 3. 회사 가입은 회사명, 국가, 지역을 입력해야합니다.
 4. 회사명은 회사 아이디와 별개로 가입 시 중복되지 않게 해야합니다.(companyName -> unique)
 5. 회사는 회사 아이디(companyId)로 식별합니다.


 6. 채용공고는 채용공고 아이디(employId)로 식별합니다.
 7. 채용공고는 회사가 등록하고, 여러 채용공고를 등록할 수 있습니다.
 8. 채용공고 등록에는 회사아이디, 채용 포지션, 채용 보상금, 채용 내용, 사용기술을 입력해야합니다.
 9. 채용공고는 회사정보와 채용공고 정보를 유지해야 합니다.
 10. 공고 수정은 회사 아이디 이외 모두 수정 가능합니다.


 11. 공고 지원은 공고 지원 아이디(applyId)로 식별합니다.
 12. 사용자는 한개의 채용공고당 1회만 지원 가능합니다.
 13. 공고 지원시 지원한 사용자 정보, 채용공고 정보를 유지해야 합니다.

  
 14. 사용자는 모든 채용공고를 조회할 수 있으며, 채용 공고 정보를 유지해야 합니다.
 15. 사용자가 특정 공고를 조회했을 경우 회사정보와 채용공고 정보를 유지해야 하며, 해당 공고를 올린 회사의 다른 공고들의 아이디를 확인할 수 있어야 합니다.
------------


### 개체 추출
![image](https://github.com/newnyee/wanted-pre-onboarding-backend/assets/121937711/0284ed97-16f1-40c8-9596-4943807919b8)
------------


### 구현 과정
#### 1. [ERD-Cloud]
![image](https://github.com/newnyee/wanted-pre-onboarding-backend/assets/121937711/24bf37cc-b489-4295-9119-d2c30d2f2942)


#### 2. [Api 구현 - RESTful Api]
#### create
- url : employ
- method : post
- request dto field : companyId, employPosition, employMoneyGift, employContent, employSkill

#### reads - list
- url : employ
- method : get
- response dto field : employId, companyName, companyCountry, companyArea, employPosition, employMoneyGift, employSkill

#### read - detail
- url : employ/{employId}
- method : get
- request field : employId
- response dto field : employId, companyName, companyCountry, companyArea, employPosition, employMoneyGift, employSkill, employContent, otherEmployList

#### update
- url : employ
- method : put
- request, response dto field : employPosition, employMoneyGift, employContent, employSkill

#### delete
- url : employ
- method : delete
- request dto field : employId, companyId
