package Tasam.apiserver.service;


import Tasam.apiserver.domain.Participation;
import Tasam.apiserver.domain.Reservation;
import Tasam.apiserver.domain.user.User;
import Tasam.apiserver.dto.AddParticipationDto;
import Tasam.apiserver.repository.ParticipationRepository;
import Tasam.apiserver.repository.ReservationRepository;
import Tasam.apiserver.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipationService {


    private final UserRepository userRepository;

    private final ReservationRepository reservationRepository;

    private final ParticipationRepository participationRepository;




    //참여하기
    @Transactional
        public Long addParticipation(AddParticipationDto addParticipationDto, String userUid){
            User findUser = userRepository.findByUid(userUid).get();
            Reservation findReservation = reservationRepository.findOne(addParticipationDto.getReservationId());


            Participation participation = participationRepository.save(Participation.builder()
                    .user(findUser)
                    .reservation(findReservation)
                    .seatPosition(addParticipationDto.getSeatPosition())
                    .build());

            if(findUser.getUid()==userUid){
                return null;
            }

            findReservation.addCurrentNum();
            findReservation.addParticipation(participation);
            findReservation.changeReservationStatus();


        return participation.getId();


    }

    //참여 취소
    @Transactional
    public Long deleteParticipation(Long reservationId,String userUid){

        User findUser = userRepository.findByUid(userUid).get();
        Participation participation = participationRepository.findParticipation(reservationId, findUser.getId());

        participation.getReservation().subtractCurrentNum();
        participation.getReservation().subParticipation(participation);
        participation.getReservation().changeReservationStatus();

        participationRepository.delete(participation.getId());


        return participation.getId();

    }





}
