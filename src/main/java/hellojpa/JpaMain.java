package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

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

            Member refMember = em.getReference(Member.class, member.getId()); //proxy 클래스 확인 방법
            refMember.getName();//JPA식 강제 초기화
            System.out.println("isLoaded=" + emf.getPersistenceUnitUtil().isLoaded(refMember));
            // proxy 인스턴스의 초기화 여부 확인

            Hibernate.initialize(refMember); // 강제 초기화, JPA 표준에는 강제 초기화가 없다.


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }
}
