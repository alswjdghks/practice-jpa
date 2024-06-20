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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member m = em.find(Member.class,member.getId()); // member의 쿼리 나감
            System.out.println("m = " + m.getTeam().getClass()); // 현재 member의 team은 proxy 객체 -> team에 대한 정보 없음

            System.out.println("===========");
            System.out.println("m.getTeam().getName() = " + m.getTeam().getName()); // 초기화 이때 team에 대한 쿼리문이 나감 -> team에 대한 정보 확인
            System.out.println("===========");

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
