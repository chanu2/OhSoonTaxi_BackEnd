package Tasam.apiserver.service;


import Tasam.apiserver.domain.Reservation;
import Tasam.apiserver.domain.user.User;
import Tasam.apiserver.dto.AddReservationDto;
import Tasam.apiserver.dto.UpdateReservationDto;
import Tasam.apiserver.dto.response.ReservationResponseDto;
import Tasam.apiserver.dto.response.ReserveDetailResponseDto;
import Tasam.apiserver.repository.ParticipationRepository;
import Tasam.apiserver.repository.ReservationRepository;
import Tasam.apiserver.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

        User user = userRepository.findByUid(userUid).get();

        Reservation reservation = Reservation.createReservation(user, addReservationDto.getReserveDate(), addReservationDto.getReserveTime(), addReservationDto.getTitle(), addReservationDto.getStartPlace()
                , addReservationDto.getDestination(), addReservationDto.getSex(), addReservationDto.getPassengerNum(), addReservationDto.getChallengeWord(), addReservationDto.getCountersignWord(),
                addReservationDto.getStartLatitude(),addReservationDto.getStartLongitude(),addReservationDto.getFinishLatitude(),addReservationDto.getFinishLongitude());

        reservationRepository.save(reservation);
        return reservation.getId();
    }


    //방 취소
    @Transactional
    public Long deleteReservation(Long reservationId, String userUid) throws  IOException{

        User user = userRepository.findByUid(userUid).get();

        if(reservationRepository.findOne(reservationId).getUser()!=user)return null;

        reservationRepository.delete(reservationId);

        return reservationId;

    }

    // 방 업데이트

    @Transactional
    public Long updateReservation(UpdateReservationDto updateReservationDto, String userUid)throws IOException{
        User user = userRepository.findByUid(userUid).get();
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
    @Transactional
    public ReserveDetailResponseDto getReservationDetail (Long reservationId){


        Reservation findReservation = reservationRepository.findOne(reservationId);
        ReserveDetailResponseDto reserveDetailResponseDto = new ReserveDetailResponseDto(findReservation);

        return reserveDetailResponseDto;


//                (findReservation.getId(),findReservation.getTitle(),findReservation.getReserveDate(),
//                        findReservation.getReserveTime(),findReservation.getStartPlace(),findReservation.getDestination(),findReservation.getSex(),findReservation.getPassengerNum(),
//                        findReservation.getChallengeWord(),findReservation.getCountersignWord(),findReservation.getCurrentNum(),findReservation.getStartLatitude(),findReservation.getStartLongitude(),
//                        findReservation.getFinishLatitude(),findReservation.getFinishLongitude(),findReservation.getReservationStatus(),)


    }




    //@Transactional





}
