package Tasam.apiserver.domain;


import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Long id;

    private String uid;
    private String password;

    private String name;
    private String schoolNum;
    private String phoneNum;
    @Enumerated(EnumType.STRING)
    private Sex sex;


    @Builder
    public User( String uid, String password, String name, String schoolNum, String phoneNum, Sex sex) {
        this.uid = uid;
        this.password = password;
        this.name = name;
        this.schoolNum = schoolNum;
        this.phoneNum = phoneNum;
        this.sex = sex;
    }
}
