package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

//가장 중요한건 JPA의 모든 데이터 변경은 트랜잭션 안에서 실행 되어야 한다!!
public class JpaMain {
    public static void main(String[] args) {
        //db당 하나 , 웹서버가 올라오는 동안에 하나만 생성.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello") ;
        //persistence.xml에 저장한 unit name : hello
        //일단 db에 연결된상태

        //트랙잭션 행동 /즉하나의 행동을 할때 마다 이e/ ntitiy manage 를 생성 해줘야함.
        //고객의 요청이 올때 썻다가 버렸다가 하는 느낌 -> 따라서 쓰데르간에 공유 x(사용하고 버려야함)
        //영속성 컨텍스트도 새로 생성이 되서 실행
        EntityManager em = emf.createEntityManager(); //ex) db 커넥션을 얻었다 예시

        EntityTransaction tx = em.getTransaction();


        tx.begin();
        // 항상 트라이 캐치 는 중요한 에러 캐리에 중요한부분 / 따라서 이게 정석 코드 실제에서 이렇게 안씀 why? 스프링이 해줌
        try {
            //멤버 저장
            // 모든 정보를 변경하는 과정에서는 JPA는 트랜잭션을 부여해줘야함
            Member member = new Member();
            //member.setId("2L");
            member.setUsername("HelloB");
            em.persist(member);

            //영속 -> 영속성 컨텍스트를 통해 멤버가 관리 -> 쓰기지연 sql저장소에 저장이됨 =쿼리가 나가지않고 저장만
            // 쿼리를 날린다 = db와 상호적용
            // em.persist(member);
            // em.persist(member2);

            // 동일 성보장 2개가 똑같음  A==B -> true IN 동잁 트랜잭션
            //ex2) 2번조회시 -> 1.1차캐시없음 2.디비 조회후 1차캐시 넣어놈 3.따라서 2번째 호출은 1차캐쉬에서 처리 /별로안씀 컨셉의 이점 존재
            //Member memberA = em.find(Member.class,1L); //디비조회 -> 쿼리 나감
            // Member memberB = em.find(Member.class,1L);// 1차캐쉬 -> 쿼리 안나감



            //멤버 찾기
            //Member findMember = em.find(Member.class,1L);
            //System.out.println("findMember.id ###@@=  " + findMember.getName() );


            //삭제
            //em.remove(findMember);

            //수정 -> update 쿼리를 날려서 작동
            //findMember.setName("Hellominuk");



            //멤버 객체를 다 가져오는 쿼리 + 그걸 리스트로
            //JPQL + 페이징 하는게 좋음 5~8 페이지 -> 객체지향 쿼리 + 각디비에 맞게 방언 번역
            //List<Member> result = em.createQuery("select m from Member as m",Member.class).getResultList();
            //for(Member member : result) {
            //    System.out.println("findMember.id ###@@=  " + member.getId());
            //}



            tx.commit();


        }catch (Exception e) {
            //다시 이전으로 복원
            tx.rollback();
        }finally {
            //꼭 닫아주어야함
            em.close();
        }
        emf.close();
    }
}
