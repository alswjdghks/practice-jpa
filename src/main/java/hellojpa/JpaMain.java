package hellojpa;

import jakarta.persistence.*;


public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        //code
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Team team =new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class,member.getId());

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam);

            /*Team newTeam = em.find(Team.class,100L); 멤버의 팀 변경
            findMember.setTeam(newTeam);*/

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
