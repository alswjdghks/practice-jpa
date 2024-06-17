package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Table(name = "USER")
public class Member {
    @Id
    private long id;
    @Column(name = "name",nullable = false)
    private String name;
    private int age;

    @Enumerated(EnumType.STRING) //EnumType.ORDINAL 너무 위험 값이 바뀜.
    private RoleType roleType;


    /*최신 버전 사용하는 것이 좋음*/
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
    private LocalDate createDate2;
    private LocalDateTime testLocalDateTime;

    @Lob // 문자는 clob, 나머지는 blob
    private String discription;

    @Transient
    private int temp;

    public Member() {

    }
    public Member(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
