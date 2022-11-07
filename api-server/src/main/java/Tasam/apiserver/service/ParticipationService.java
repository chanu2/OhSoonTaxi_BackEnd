package Tasam.apiserver.service;


import Tasam.apiserver.domain.Participation;
import Tasam.apiserver.domain.Reservation;
import Tasam.apiserver.domain.User;
import Tasam.apiserver.repository.ParticipationRepository;
import Tasam.apiserver.repository.ReservationRepository;
import Tasam.apiserver.repository.UserRepository;
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
    public Long addParticipation(Long reserveId, String userUid){
        User findUser = userRepository.findByUid(userUid);
        Reservation findReservation = reservationRepository.findOne(reserveId);


        Participation participation = participationRepository.save(Participation.builder()
                .user(findUser)
                .reservation(findReservation)
                .build());

        findReservation.addCurrentNum();
        findReservation.addParticipation(participation);


        return participation.getId();


    }

    //참여 취소
    @Transactional
    public Long deleteParticipation(Long reservationId,String userUid){

        User findUser = userRepository.findByUid(userUid);
        Participation participation = participationRepository.findParticipation(reservationId, findUser.getId());

        participation.getReservation().subtractCurrentNum();
        participation.getReservation().subParticipation(participation);

        participationRepository.delete(participation.getId());


        return participation.getId();

    }





}
