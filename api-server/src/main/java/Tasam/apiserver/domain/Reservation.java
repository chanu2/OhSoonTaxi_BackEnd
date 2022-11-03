package Tasam.apiserver.domain;


import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Reservation {

    @Id @GeneratedValue
    @Column(name = "reservation_id")
    public Long id;


    private LocalDate reserveDate;
    private LocalTime startT;
    private LocalDateTime writeT;

    private String title;
    private String startPlace;
    private String destination;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private reservationStatus reservationStatus;

    private Integer passengerNum;
    private Integer currentNum;

    private String challengeWord;
    private String countersignWord;

    @Builder
    public Reservation(Long id, LocalDate reserveDate, LocalTime startT, LocalDateTime writeT, String title, String startPlace, String destination,
                       Sex sex, Tasam.apiserver.domain.reservationStatus reservationStatus, Integer passengerNum, Integer currentNum, String challengeWord, String countersignWord) {
        this.id = id;
        this.reserveDate = reserveDate;
        this.startT = startT;
        this.writeT = writeT;
        this.title = title;
        this.startPlace = startPlace;
        this.destination = destination;
        this.sex = sex;
        this.reservationStatus = reservationStatus;
        this.passengerNum = passengerNum;
        this.currentNum = currentNum;
        this.challengeWord = challengeWord;
        this.countersignWord = countersignWord;
    }
}
