package Tasam.apiserver.dto;

import Tasam.apiserver.domain.Sex;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class SignUpDto {
    // TODO :: 검증 조건 추가하기
    private String uid;
    private String password;

    private String name;
    private String schoolNum;
    private String phoneNum;

    private Sex sex;


}
