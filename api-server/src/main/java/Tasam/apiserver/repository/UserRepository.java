package Tasam.apiserver.repository;


import Tasam.apiserver.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class UserRepository {


    @PersistenceContext
    private EntityManager em;


    public User findByUid(String uid){
        return em.createQuery("select u from User u where u.uid = :uid", User.class).setParameter("uid",uid).getSingleResult();
    }
}
