package Tasam.apiserver.dto;


import Tasam.apiserver.domain.Sex;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddReservationDto1 {

    private LocalDate reserveDate;
    private LocalTime startT;

    private String title;
    private String startPlace;
    private String destination;

    private Sex sex;

    private Integer passengerNum;
    private String challengeWord;
    private String countersignWord;

    public AddReservationDto1(LocalDate reserveDate, LocalTime startT, String title, String startPlace, String destination, Sex sex, Integer passengerNum, String challengeWord, String countersignWord) {
        this.reserveDate = reserveDate;
        this.startT = startT;
        this.title = title;
        this.startPlace = startPlace;
        this.destination = destination;
        this.sex = sex;
        this.passengerNum = passengerNum;
        this.challengeWord = challengeWord;
        this.countersignWord = countersignWord;
    }
}
