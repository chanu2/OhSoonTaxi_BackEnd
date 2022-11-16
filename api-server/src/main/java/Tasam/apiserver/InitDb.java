package Tasam.apiserver;

import Tasam.apiserver.domain.Sex;
import Tasam.apiserver.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;


    @PostConstruct
    public void init() {
        initService.dbInit1();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;


        public void dbInit1() {


            User user1 = new User("asd", "qwesd", "chan", "1234", "23414123",Sex.WOMAN,Collections.singletonList("ROLE_USER"));
            User user2 = new User("asdasd","qwes1d","cha23n","123784","234123", Sex.MAN, Collections.singletonList("ROLE_USER"));
            User user3 = new User("123","123","park","1237434","223", Sex.MAN,Collections.singletonList("ROLE_USER"));

            em.persist(user1);
            em.persist(user2);
            em.persist(user3);

            em.flush();


        }



    }


}








