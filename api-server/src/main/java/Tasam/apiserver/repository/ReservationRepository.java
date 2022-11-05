package Tasam.apiserver.repository;


import Tasam.apiserver.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ReservationRepository {

    private final EntityManager em;


    public void save(Reservation reservation){

        em.persist(reservation);
    }

    public Reservation findOne(Long id){
        return em.find(Reservation.class,id);
    }





}
