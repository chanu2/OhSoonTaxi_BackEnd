package Tasam.apiserver.service;


import Tasam.apiserver.domain.Reservation;
import Tasam.apiserver.domain.User;
import Tasam.apiserver.dto.AddReservationDto;
import Tasam.apiserver.repository.ParticipationRepository;
import Tasam.apiserver.repository.ReservationRepository;
import Tasam.apiserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final UserRepository userRepository;

    private final ParticipationRepository participationRepository;



    // 방 예약

    @Transactional
    public Long addReservation(AddReservationDto addReservationDto,String userUid) throws IOException {
        User user = userRepository.findByUid(userUid);


        Reservation reservation = Reservation.createReservation(user, addReservationDto.getReserveDate(), addReservationDto.getStartT(), addReservationDto.getTitle(), addReservationDto.getStartPlace()
                , addReservationDto.getDestination(), addReservationDto.getSex(), addReservationDto.getPassengerNum(), addReservationDto.getChallengeWord(), addReservationDto.getCountersignWord());

        reservationRepository.save(reservation);
        return reservation.getId();
    }


    //방 취소
    @Transactional
    public Long deleteReservation(Long reservationId, String userUid){

        User user = userRepository.findByUid(userUid);
        if(){

        }
    }


}
