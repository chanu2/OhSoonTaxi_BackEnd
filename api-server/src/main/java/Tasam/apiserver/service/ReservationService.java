package Tasam.apiserver.service;


import Tasam.apiserver.domain.Reservation;
import Tasam.apiserver.domain.User;
import Tasam.apiserver.dto.AddReservationDto;
import Tasam.apiserver.dto.UpdateReservationDto;
import Tasam.apiserver.dto.response.ReservationResponseDto;
import Tasam.apiserver.repository.ParticipationRepository;
import Tasam.apiserver.repository.ReservationRepository;
import Tasam.apiserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
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
    public Long deleteReservation(Long reservationId, String userUid) throws  IOException{

        User user = userRepository.findByUid(userUid);

        if(reservationRepository.findOne(reservationId).getUser()!=user)return null;

        reservationRepository.delete(reservationId);

        return reservationId;

    }

    // 방 업데이트

    @Transactional
    public Long updateReservation(UpdateReservationDto updateReservationDto, String userUid)throws IOException{
        User user = userRepository.findByUid(userUid);
        Reservation reservation = reservationRepository.findOne(updateReservationDto.getId());
        if(reservation.getUser()!=user) return null;

        reservation.changeTitle(updateReservationDto.getTitle());

        return reservation.getId();


    }

    // 키워드 검색



    //  원하는 날짜 시간 정렬해서 택시방 보여 주기
    @Transactional
    public List<ReservationResponseDto> getReservationSortList(LocalDate reserveDate){

        List<Reservation> list = reservationRepository.findBySortDate(reserveDate);

        return list.stream().map( r -> new ReservationResponseDto(r)).collect(Collectors.toList());

    }




    // 택시방 관련 정보 상세 보여주기
    //@Transactional





}
