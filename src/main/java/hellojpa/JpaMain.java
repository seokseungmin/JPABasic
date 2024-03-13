package hellojpa;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        //엔티티 매니저는 데이터 변경시 트랜잭션을 시작해야 한다.
        tx.begin();

        try {

            //Member member = new Member(201L, "member300");
            //em.persist(member);
            //DB에 반영되기전(커밋전)에 미리좀 보고 싶을때 강제로 하는행동
            //em.flush();

            //변경감지
            //만약 캐시에 없으면 DB에서 가져와서 캐시에 영속 상태로 만듬.
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

            //준영속 JPA가 더이상 관리를 안함. 커밋해도 변화가 없음!
            //em.detach(member);

            //영속성 컨텍스트 통으로 날려버림 이때도 준영속이랑 비슷한!
            em.clear();
            Member member1 = em.find(Member.class, 150L);
            //em.persist(member);

            System.out.println("====================");

            //객체를 생성한 상태(비영속), JPA와 관련도 없고 DB에 들어가지도 않음.
            //Member member = new Member();
            //member.setId(100L);
            //member.setName("HelloJPA");

            //객체를 저장한 상태(영속), 영속성 컨텍스트라는데 등록되서 관리됨
            //System.out.println("=== BEFORE ===");
            //em.persist(member);
            //System.out.println("=== AFTER ===");

            //회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
            //em.detach(member);

            //객체를 삭제한 상태(삭제)
            //em.remove(member);

            //영속
            //Member member1 = new Member(150L,"A");
            //Member member2 = new Member(160L,"B");

            //em.persist(member1);
            //em.persist(member2);

            //System.out.println("====================");

            //Member findMember1 = em.find(Member.class, 100L);
            //Member findMember2 = em.find(Member.class, 100L);

            //System.out.println("result = " + (findMember1 == findMember2));
            //System.out.println("findMember.id = " + findMember.getId());
            //System.out.println("findMember.name = " + findMember.getName());

            //List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();

            //for (Member member : result) {
            //    System.out.println("member.name = " + member.getName());
            //}

            //커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
            emf.close();
    }
}
