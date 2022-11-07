package Tasam.apiserver.dto.response;


import Tasam.apiserver.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReservationResponseDto {


    public Long id;

    private LocalTime startT;


    private String title;

    private String startPlace;
    private String destination;

    private Sex sex;

    private ReservationStatus reservationStatus;

    public ReservationResponseDto(Reservation reservation) {
        this.id = reservation.getId();
        this.startT = reservation.getStartT();
        this.title = reservation.getTitle();
        this.startPlace = reservation.getStartPlace();
        this.destination = reservation.getDestination();
        this.sex = reservation.getSex();
        this.reservationStatus = reservation.getReservationStatus();
    }
}
