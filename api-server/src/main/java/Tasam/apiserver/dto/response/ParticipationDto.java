package Tasam.apiserver.dto.response;


import Tasam.apiserver.domain.Participation;
import lombok.Getter;

@Getter
public class ParticipationDto {

    private String name;
    private String schoolNum;
    private Integer seatPosition;

    public ParticipationDto(Participation participation){

        name= participation.getUser().getName();
        schoolNum = participation.getUser().getSchoolNum();
        seatPosition = participation.getSeatPosition();
    }
}
