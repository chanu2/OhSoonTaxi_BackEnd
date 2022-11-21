package Tasam.apiserver.repository;

import Tasam.apiserver.domain.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ParticipationRepository1 extends JpaRepository<Participation,Long> {

    Participation save(Participation participation);

    Optional<Participation> findById(Long id);


    @Query("select p from Participation p"+
            " join fetch p.user u"+
            " join fetch p.reservation r"+
            " where r.id = :reservationId and u.id = :userId ")
    Participation findParticipation(@Param("reservationId") Long reserveId, @Param("userId")Long userId);


    @Override
    void deleteById(Long id);
}
