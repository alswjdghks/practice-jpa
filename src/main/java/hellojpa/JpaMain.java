package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        //code
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Member member = new Member();
            member.setName("member1");
            em.persist(member);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member.getId());
            System.out.println("refMember = " + refMember.getClass());  //Proxy

            Member findMember = em.find(Member.class,member.getId());
            System.out.println("findMember = " + findMember.getClass()); // Member이 아닌 Proxy 반환


            System.out.println("refMember == findMember: " + (refMember == findMember));  // 항상 true 반환
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
