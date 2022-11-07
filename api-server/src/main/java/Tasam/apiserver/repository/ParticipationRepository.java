package Tasam.apiserver.repository;


import Tasam.apiserver.domain.Participation;
import Tasam.apiserver.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class ParticipationRepository {

    private final EntityManager em;


    public Participation findOne(Long id){
        return em.find(Participation.class,id);
    }

    public Participation save(Participation participation){
        em.persist(participation);
        return participation;
    }

    public Participation findParticipation(Long reserveId,Long userId){

        Participation result = em.createQuery("select p from Participation p"+
                " left join fetch p.user u"+
                " left join fetch p.reservation r"+
                " where r.id = :reservationId and u.id = :userId ", Participation.class).setParameter("reservationId", reserveId).setParameter("userId", userId).getSingleResult();

        return result;
    }

    public void delete(Long id){
        em.createQuery("delete from Participation p where p.id = :id ").setParameter("id",id).executeUpdate();
    }
}
