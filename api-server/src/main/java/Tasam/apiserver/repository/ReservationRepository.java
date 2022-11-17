package Tasam.apiserver.repository;


import Tasam.apiserver.domain.Reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

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


    public void delete(Long reservationId){ em.createQuery("DELETE FROM Reservation r WHERE r.id =: reservationId ").setParameter("reservationId",reservationId).executeUpdate();}



    public List<Reservation> findBySortDate(LocalDate day){
        return em.createQuery("select r from Reservation r where r.reserveDate = :day order by r.reserveTime ", Reservation.class)
                .setParameter("day",day).getResultList();
    }


    public List<Reservation> findParticipatedReserve(String userUid){
        return em.createQuery("select distinct r from Reservation r"+
                " join fetch r.participations p"+
                " join fetch p.user u"+
                " where u.uid =: userUid ",Reservation.class).setParameter("userUid",userUid).getResultList();
    }



//
//     return em.createQuery("select distinct r from Reservation r"+
//             " join fetch r.participations p"+
//             " join fetch p.user u"+
//             " where u.uid = :userUid ",Reservation.class).setParameter("userUid",userUid).getResultList();



}
