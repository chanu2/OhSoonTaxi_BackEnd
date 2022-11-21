package Tasam.apiserver.repository;


import Tasam.apiserver.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface ReservationRepository1 extends JpaRepository<Reservation,Long> {


    Reservation save(Reservation reservation);

    Optional<Reservation> findById(Long id);


    @Override
    void deleteById(Long reservationId);


    @Query("select r from Reservation r where r.reserveDate = :day order by r.reserveTime ")
    List<Reservation> findBySortDate(@Param("day") LocalDate day);


    @Query("select distinct r from Reservation r"+
            " join fetch r.participations p"+
            " join fetch p.user u"+
            " where u.uid = :userUid order by r.reserveDate ")
    List<Reservation> findParticipatedReserve(@Param("userUid") String userUid);


    @Query("select distinct r from Reservation r"+
            " join fetch r.user u"+
            " where u.id = :userId order by r.reserveDate ")
    List<Reservation> reservedByMe(@Param("userId") Long userId);


    List<Reservation> findByTitleContaining(String keyword);



}
