package Tasam.apiserver.repository;


import Tasam.apiserver.domain.Participation;
import Tasam.apiserver.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

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


    public Optional<Participation> findParticipation(Long reserveId, Long userId){

        Optional participation = null;

        try{
            participation = Optional.ofNullable(em.createQuery("select p from Participation p"+
                    " join fetch p.user u"+
                    " join fetch p.reservation r"+
                    " where r.id = :reservationId and u.id = :userId ", Participation.class).setParameter("reservationId", reserveId).setParameter("userId", userId).getSingleResult());
        }catch (NoResultException e){
            participation = Optional.empty();
        }finally {
            return participation;
        }

    }

    public void delete(Long id){
        em.createQuery("delete from Participation p where p.id = :id ").setParameter("id",id).executeUpdate();
    }
}
