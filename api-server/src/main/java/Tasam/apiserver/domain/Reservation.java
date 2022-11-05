package Tasam.apiserver.domain;


import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    public Long id;

    @OneToMany(mappedBy = "reservation",orphanRemoval = true)
    private List<Participation> participations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate reserveDate;
    private LocalTime startT;
    private LocalDateTime writeT;

    private String title;
    private String startPlace;
    private String destination;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    private Integer passengerNum;
    private Integer currentNum;

    private String challengeWord;
    private String countersignWord;


    @Builder
    public Reservation(User user, List<Participation> participations, LocalDate reserveDate, LocalTime startT, LocalDateTime writeT, String title, String startPlace, String destination,
                       Sex sex, ReservationStatus reservationStatus, Integer passengerNum, Integer currentNum, String challengeWord, String countersignWord) {
        this.user=user;
        this.participations=participations;
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

        //==연간 관계 메서드==//
        Participation.builder().reservation(this).build();

    }

    //==생성메서드==//
    public static Reservation createReservation(User user,LocalDate reserveDate, LocalTime startT, String title, String startPlace, String destination,
                                                Sex sex, Integer passengerNum, String challengeWord, String countersignWord){
        return builder()
                .user(user)
                .reserveDate(reserveDate)
                .startT(startT)
                .title(title)
                .writeT(LocalDateTime.now())
                .startPlace(startPlace)
                .destination(destination)
                .reservationStatus(ReservationStatus.POSSIBLE)
                .passengerNum(passengerNum)
                .sex(sex)
                .currentNum(0)
                .challengeWord(challengeWord)
                .countersignWord(countersignWord)
                .build();

    }
}
