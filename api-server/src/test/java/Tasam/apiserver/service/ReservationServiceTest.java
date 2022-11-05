package Tasam.apiserver.service;

import Tasam.apiserver.domain.Reservation;
import Tasam.apiserver.domain.Sex;
import Tasam.apiserver.domain.User;
import Tasam.apiserver.dto.AddReservationDto;
import Tasam.apiserver.dto.AddReservationDto1;
import Tasam.apiserver.repository.ReservationRepository;
import Tasam.apiserver.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback(value = false)
public class ReservationServiceTest {

    @Autowired
    UserService userService;

    @Autowired ParticipationService participationService;

    @Autowired ReservationService reservationService;

    @Autowired UserRepository userRepository;

    @Autowired
    EntityManager em;

    @Autowired
    ReservationRepository reservationRepository;

    @Test
    public void 경기생성() throws Exception{

        //given

        User user1 = new User("asd","qwesd","chan","1234","23414123", Sex.MAN);
        User user2 = new User("asdasd","qwes1d","cha23n","123784","234123", Sex.MAN);

        em.persist(user1);
        em.persist(user2);
        em.flush();

        AddReservationDto a = new AddReservationDto(LocalDate.of(2022, 11, 5), LocalTime.of(15, 30), "컴", "신창역", "순천향대 후문", Sex.ALL, 4, "고추", "미역");
        Long reserveId = reservationService.addReservation(a, "asd");

        //Long id = reservationService.addReservation(LocalDate.of(2000, 1, 3), LocalTime.of(14, 30), "컴", "신창역", "후문", Sex.ALL, 4, "코끼리", "미역", "asd");

        System.out.println(reserveId);

        em.flush();


    }





}