# [JAVA Semi-project] 🥗샐러드 매장 키오스크
<br>

## 1. 프로젝트 소개
📌 **기간** : 2023/09/25 ~ 2023/10/15<br>
📌 **주제** : 샐러드 매장 키오스크<br>
📌 **사용언어** : JAVA<br>
<br><br>

## 2. 프로젝트 진행과정
| 단계 | 기간 | 내용 |
|--|--|--|
| 기획 | 09/25 ~ 10/02 | 주제 선정, 플로우 차트 구성, 기획안 작성, 파트 분배 |
| 기능 구현 | 10/02 ~ 10/09 | 기능 구현, 코드리뷰, 피드백 및 수정 |
| 기능 통합 | 10/10 ~ 10/12 | 기능 통합, 수정 |
| 프로토 타입 | 10/13 | 테스트 및 오류 해결 |
| 검토 및 수정 | 10/13 ~ 10/15 | 최종 결과물 수정 완료, 보고서 작성, ppt 발표 준비 |

<br>

## 2-1. 기획
샐러드 소비 증가에 따라 사용자가 원하는대로 만들어먹는 **샐러드(🥑+🥒+🌽+🍅+🥦 = 🥗) 매장 키오스크**를 기획해보았습니다.
<br><br>

### [ 파트 분배 ]
|   | 팀원1 | 팀원2 | 나 (팀원3) | 팀장 | 팀원4 |
| --- | --- | --- | --- | --- | --- |
| 담당파트 | [관리자-재고관리] | [관리자-매출관리] | [관리자-회원관리] | [사용자-구매] | [사용자-결제] |

<br>

## 2-2. 기능구현

### [ 버전 ]
**▶ ver1.0** 
- 프로젝트 기간 내 구현

**▶ ver2.0**
- 프로젝트 이후 기능 수정 (관리자 기능)
	- 기존 기획 버전으로 롤백하여 재고관리를 대메뉴에 포함시키고,  판매관리 기능을 [재고관리, 사장추천관리] > [상세 기능] 에 포함
	- 재료리스트 수정시 사장추천 개수와 연동 될 수 있도록 수정
- 리팩토링
	- 가독성과 통일성을 위해 메뉴 반복 코드 수정
	- 불필요한 코드 및 직렬화 코드 수정 및 삭제
<br>

### [ 관리자 ]
| 기능 | 상세 |
|--|--|
| 재고관리 | 재료셋팅, 품절관리 |
| 재료관리 | 재료목록출력, 신규재료등록, 재료정보변경, 재료정보삭제 |
| 사장추천관리 | 추천조합출력, 추천조합등록, 추천조합정보변경, 추천조합정보삭제 |
| 매출관리 | 결제취소, 영수증출력, 매출조회 |
| 회원관리 | 회원목록출력, 회원등록, 회원정보수정, 회원삭제 |
| 기타 | 관리자로그인 |

### [ 사용자 ]
| 기능 | 상세 |
|--|--|
| 구매 | 메뉴선택, 추가주문, 장바구니 |
| 회원 | 로그인, 회원가입 |
| 결제 | 포인트사용, 결제, 영수증 출력 |

<br>

## 3. 후기

### 1) 담당 파트 내용
1. [관리자 - 관리자 로그인]<br>
관리자 로그인 기능을 구현했다.

3.  [관리자 - 회원 관리] <br>
회원을 생성하고, 회원정보를 수정하고, 회원을 삭제하고 회원내역을 출력하는 기능을 구현했다. 회원이 가진 포인트가 연동되어 사용자 결제 파트, 매출관리(결제취소) 파트에 영향을 받아서 변동이 되어야 했다.

3.  [사용자 - 회원]<br>
사용자 로그인, 회원 가입 파트를 구현했고, 사용자가 회원 가입시 [관리자 - 회원관리] 메뉴에서 회원정보를 출력, 수정, 삭제할 수 있도록 관리자-사용자 파트가 연동가능하게 구현했다.
<br>

### 2) 프로젝트 설계 단계
#### ▶ 아쉬웠던 / 발전시켜야할 점
-   첫 프로젝트인 만큼 많은 아이디어 중 기획단계에서 구현이 가능할지 고민하는 과정, 팀원들과 함께 어떤 기능이 들어가야 할지 아이디어들을 정리하고 조율하는 부분에서 시간을 많이 투자했고, 어려움을 느꼈다. 또한, 모아진 의견으로 플로우 차트를 만들어보니 생각보다 구현해야할 기능이 많게 느껴졌고, 기능을 적절하게 추가하고 제거하는 것의 중요성을 느꼈다.
-   팀원들과 플로우 차트를 구성할 때 나의 의견을 말로만 전달하고 설명하는 것이 쉬운 일이 아님을 느꼈다. 피드백을 받은 후 꼼꼼하게 하나씩 체크하고 넘어가고 싶은 마음에 팀원들에게 질문을 많이 했는데, 열심히 답해준 팀원들에게 고맙고 미안한 마음이 들었다.

<br>

### 3) 프로젝트 구현 단계
| 구분 | 문제발생 | 해결방법 |
|--|--|--|
| #1 | 기능 구현단계에 들어오니 어떤 자료구조를 활용해야 하는지에 대한 고민이 많았다. 입력되는 회원 데이터들을 관리, 변동해야하는 기능을 자료구조를 활용하여 작성해보고자 하였지만, hashMap 의 value에 객체를 넣어서 사용해보는 것이 처음이어서 많은 시행착오를 겪었다. | 자료구조에 대한 공부 후에, 사용자 정의 생성자를 만들어서 자료구조안의 객체에 데이터들을 넣을 수 있었고, getter/setter 메소드를 이용하여 속성값을 가져오거나 세팅해줄 수 있게 했다. 또한, static 으로 한 클래스에 자료구조를 한 번만 선언하여 다른 클래스에서도 공유의 개념으로 사용할 수 있도록 했다. |
| #2 | 다른 팀원의 소스코드를 받아 통합하여 테스트하던 중 사용자 기능과 관리자 기능이 연동되지 않는 오류가 발생했다. | 해당 문제를 해결하기 위해 팀원의 코드를 분석하고 해당 파트 팀원과 논의한 결과, static으로 선언한 자료구조를 활용하는 과정이 누락된 문제가 있었음을 발견하였고, 해당 부분을 수정하였다. |
| #3 | 팀원들과 각 파트를 구현하는 과정에서 서로의 코드에 대해 잘 이해하지 못하는 문제가 발생하였다. | 코드리뷰를 통해 각 팀원들의 코드를 이해하고 피드백하는 시간을 가졌다. |
| #4 | 프로젝트 이후 각 팀원들의 코드 구조가 달라서 가독성이 낮고 유지보수가 어렵다는 점이 아쉽게 느껴졌다. | 가독성을 높이기 위해 중복되거나 불필요한 코드를 제거하였고, 오류가 발생했던 부분들은 기능 수정을 통해 해결하였다. |