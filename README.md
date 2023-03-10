# Narchive_AOS

이슈 1 -
  홈화면 API를 제작하던 중, API 및 Repo 재사용성에 관해 생각해보면서 한 화면의 API (피드화면 각 아이템) 을 여러 API로 나눈 후 불러와서 매핑해주는 방식으로 구현해봤으나,
  API 호출 및 매핑으로 인해 뷰가 보여지는 속도가 현저히 느려지는 이슈가 있었다.
  화면별로 각각 묶어내는게 좋을까?
  한 화면에서 오는 데이터들을 가져온 후 필요에 따라 각 Data로 매핑 후 DataBinding에 활용하는게 좋은가? 그렇다면 왜 굳이 매핑을 해야할까?
  어처피 각 화면마다 다른 API를 불러와야할텐데 그렇다면 매핑을 하는 의미가 있을까?

이슈 1 - 해결시도 1 -
  배열로 받아와야만 하는 Image를 제외한 모든 값들을 flat하게 구성해봤다.
  하지만 각 객체 (Book, Movie, Comment) 값이 없을 때, null들이 들어가 해당 객체가 비어있는지 판단하기에 로직이 너무 불필요하고,
  null로 그냥 넣어두자니 Glide를 사용하는 이미지 로딩 파트에서 오류가 발생했다.

이슈 1 - 해결 - 
  각 객체별로 서버에서 구분해서 보내주도록 했다.
  API를 재사용하지 못한다는게 조금 아쉽긴 하지만, 이게 최선인 것 같다..
  

이슈 2 - 
  2중 리사이클러뷰를 사용하다보니, 클릭 시 2중으로 감싸진 이미지 RV의 이벤트를 받아오는게 잘 되지 않았다.

이슈 2 - 해결시도 1 -
  interface를 각 어댑터에 클릭시 이벤트를 반환하도록 넘겨준 후 동작시켜보았으나 각 인터페이스간 연결이 원활하게 되지 않아 아무런 동작도 일어나지 않았다.

이슈 2 - 해결시도 2 -
  interface 대신 콜백을 각 어댑터에 파라미터로 넘겨준 후 동작시켜보았으나 달라지지 않았다. 


이슈 3 -
  위의 이슈를 해결하던 중 동시에 터진 이슈이다..
  RecyclerView와 ViewStub을 사용하다보니, 아이템이 재활용되면서 특정 아이템들에서 Inflate된 ViewStub들이 남아있는 이슈가 발생했다.
  이미지/책/영화 가 마법처럼 나타났다 사라지기 시작했다.
  각 아이템이 바인드 될 때 null체크를 해준 후 그려주는 식으로 동작시켜서 전혀 생각하지 못했던 오류였다.

이슈 3 - 해결시도 1 -
  각 아이템들의 Null Check에서 Visibility를 View/Gone 으로 동작시켰다.
  하지만 멀쩡했던 ViewStub들의 Visibility가 스크롤됨에 따라 Gone으로 변경되어서, 있어야 할 아이템들이 안 보이는 이슈도 발생했다.

해결? - 완!
  이슈2, 이슈3 을 동시에 해결하기 위한 기획변경이다.
  기존에는 피드에서 이미지, 책, 영화를 모두 보여줬다면, 변경안으로는 피드에서는 글(텍스트) 만 보여주고, 
  해당 피드를 클릭했을 때 넘어가는 상세화면에서 사진/책/영화 를 보여주기로 했다.
  그렇게하면 RecyclerView로 인한 ViewStub 재활용 이슈도, 이미지들을 2중 RV로 구성할 필요가 없다보니 중첩 RV 클릭 이슈도 해결이 가능할 것으로 보인다.
  - 위의 방식으로 해결 완료했다!

이슈 4 -
  위의 이슈와 또 연결되는 이슈이다..
  새 글 등록시 스크롤 상단에(현재 스크롤의 위쪽에) 새 피드가 등록되는 이슈가 있다.
  이걸 어떻게 해결할지는 일단 추후 구성을 마친 후 다시 생각해볼 예정이다.

이슈 5 - 
  나카이브 릴리즈 검토 리젝 먹었다.
  사유는 인증방법을 미게시했기 때문인데, 나카이브는 카카오 로그인을 사용한다..
  그래서 내 카카오 계정을 알려줘야하나 하고 구글링을 해 본 결과, 카카오 포럼? 같은 곳에서 이메일로 계정생성을 해보라는 글을 보고
  이메일로 테스트계정을 생성 후 재검토 요청을 보냈다.
  결과는 업데이트되는대로 업데이트 할 예정이다!

이슈 5 - 해결
  로그인 문제는 테스트 계정 생성 후 보내는것으로 해결했다!
  이로써 릴리즈 성공!

Plan - 1 
  내 프로필 사진/닉네임 모아볼 수 있는 기능을 추가하려고 한다.
Plan - 2
  캘린더 기능도 추가해보면 어떨까? 계획을 미리 세워둘 수도 있고, 이 날엔 어떤 일을 할 것인지 써보는것도 좋을 것 같다. 물론 홈 피드의 글과는 별개로 관리해야겠지만..
Plan - 3
  리팩토링 예정 : 리사이클러뷰 어댑터를 모두 BindingAdapter로 변경할 예정이다.
  View단에서의 Binding코드를 최대한 줄여보고자 한다.
  


  
