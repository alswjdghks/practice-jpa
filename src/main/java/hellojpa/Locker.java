package hellojpa;

import jakarta.persistence.*;

@Entity
public class Locker {
    @Id@GeneratedValue
    private Long id;
    @Column(name = "NAME")
    private String name;
    @OneToOne(mappedBy = "locker")
    private Member member;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
