package Tasam.apiserver.dto.response;


import Tasam.apiserver.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class ReservationResponseDto {


    public Long id;

    private LocalTime reserveTime;


    private String title;

    private String startPlace;
    private String destination;

    private Sex sex;

    private ReservationStatus reservationStatus;

    public ReservationResponseDto(Reservation reservation) {
        this.id = reservation.getId();
        this. reserveTime = reservation.getReserveTime();
        this.title = reservation.getTitle();
        this.startPlace = reservation.getStartPlace();
        this.destination = reservation.getDestination();
        this.sex = reservation.getSex();
        this.reservationStatus = reservation.getReservationStatus();
    }
}
