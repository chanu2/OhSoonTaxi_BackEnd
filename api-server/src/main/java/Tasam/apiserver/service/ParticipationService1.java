package Tasam.apiserver.service;


import Tasam.apiserver.domain.Participation;
import Tasam.apiserver.domain.Reservation;
import Tasam.apiserver.domain.ReservationStatus;
import Tasam.apiserver.domain.user.User;
import Tasam.apiserver.dto.AddParticipationDto;
import Tasam.apiserver.repository.ParticipationRepository;
import Tasam.apiserver.repository.ParticipationRepository1;
import Tasam.apiserver.repository.ReservationRepository;
import Tasam.apiserver.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipationService1 {


    private final UserRepository userRepository;

    private final ReservationRepository reservationRepository;


    private final ParticipationRepository1 participationRepository1;


    //참여하기
    @Transactional
    public Long addParticipation(AddParticipationDto addParticipationDto, String userUid) {
        User findUser = userRepository.findByUid(userUid).get();
        Reservation findReservation = reservationRepository.findOne(addParticipationDto.getReservationId());


        Participation participation = participationRepository1.save(Participation.builder()
                .user(findUser)
                .reservation(findReservation)
                .seatPosition(addParticipationDto.getSeatPosition())
                .build());

        if (findUser.getUid() == userUid) {
            return null;
        }

        findReservation.addCurrentNum();
        findReservation.addParticipation(participation);
        findReservation.changeReservationStatus();


        return participation.getId();


    }

    //참여 취소
    @Transactional
    public Long deleteParticipation(Long reservationId, String userUid) {

        User findUser = userRepository.findByUid(userUid).get();
        Participation participation = participationRepository1.findParticipation(reservationId, findUser.getId());

        participation.getReservation().subtractCurrentNum();
        participation.getReservation().subParticipation(participation);
        participation.getReservation().changeReservationStatus();

        participationRepository1.deleteById(participation.getId());


        return participation.getId();

    }


    //방에 참여했는지 확인
    @Transactional
    public String checkParticipation(Long reservationId, String userUid) {

        Optional<User> user = userRepository.findByUid(userUid);

        Reservation findReservation = reservationRepository.findOne(reservationId);

        Participation participation = participationRepository1.findParticipation(reservationId, user.get().getId());

        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime reserveDateTime = LocalDateTime.of(findReservation.getReserveDate(), findReservation.getReserveTime());



        if (participation!=null) {

            if (nowDateTime.isAfter(reserveDateTime.minusMinutes(30)) && nowDateTime.isBefore(reserveDateTime)) {
                return "1";
            } else if (nowDateTime.isBefore(reserveDateTime)) {
                System.out.println(nowDateTime.isBefore(reserveDateTime));
                return "2";
            }
            return "4";
        } else if (nowDateTime.isBefore(reserveDateTime)
                && findReservation.getReservationStatus() != ReservationStatus.DEADLINE) {
            return "3";
        }
        return "4";

    }

}
