package Tasam.apiserver.dto.response;

import Tasam.apiserver.domain.Reservation;
import Tasam.apiserver.domain.ReservationStatus;
import Tasam.apiserver.domain.Sex;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class SearchResponseDto {

    public Long id;

    private LocalTime reserveTime;

    private String title;

    private String startPlace;
    private String destination;

    private Sex sex;

    private ReservationStatus reservationStatus;

    public SearchResponseDto(Reservation reservation) {
        this.id = reservation.getId();
        this. reserveTime = reservation.getReserveTime();
        this.title = reservation.getTitle();
        this.startPlace = reservation.getStartPlace();
        this.destination = reservation.getDestination();
        this.sex = reservation.getSex();
        this.reservationStatus = reservation.getReservationStatus();
    }

}
