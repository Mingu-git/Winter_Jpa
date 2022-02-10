package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        //로딩 시점에 하나만 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello") ;
        //persistence.xml에 저장한 unit name : hello
        //일단 db에 연결된상태

        //트랙잭션 행동 /즉하나의 행동을 할때 마다 이e/ ntitiy manage 를 생성 해줘야함.
        EntityManager em = emf.createEntityManager(); //ex) db 커넥션을 얻었다 예시

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //멤버 저장
        // 모든 정보를 변경하는 과정에서는 JPA는 트랜잭션을 부여해줘야함
        Member member = new Member();
        member.setId(1L);
        member.setName("HelloA");
        em.persist(member);

        tx.commit();

        em.close();

        emf.close();
    }
}
