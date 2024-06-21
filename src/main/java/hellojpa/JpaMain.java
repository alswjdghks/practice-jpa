package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        //code
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setName("jerry");
            member.setHomeAddress(new Address("city","street","zipcode"));

            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getAddressHistory().add(new AddressEntity("old1","street","zipcode"));
            member.getAddressHistory().add(new AddressEntity("old2","street","zipcode"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("==============start==============");
            Member findMember = em.find(Member.class,member.getId());

            //homeCity -> newCity
            // findMember.getHomeAddress().setCity("newCity");
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity",a.getStreet(),a.getZipcode()));

            //치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

//            findMember.getAddressHistory().remove(new Address("old1","street","zipcode")); //이래서 equals를 구현해야함,
//            findMember.getAddressHistory().add(new Address("newCity","street","zipcode"));

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
