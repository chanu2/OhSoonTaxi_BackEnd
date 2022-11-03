package Tasam.apiserver.domain;


import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    public Long id;

    private String uid;
    private String password;

    private String name;
    private String schoolNum;
    private String phoneNum;
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
