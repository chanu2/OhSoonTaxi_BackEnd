package Tasam.apiserver.service;

import Tasam.apiserver.domain.Participation;
import Tasam.apiserver.domain.Reservation;
import Tasam.apiserver.domain.Sex;
import Tasam.apiserver.domain.User;
import Tasam.apiserver.dto.AddReservationDto;
import Tasam.apiserver.dto.AddReservationDto1;
import Tasam.apiserver.dto.UpdateReservationDto;
import Tasam.apiserver.repository.ParticipationRepository;
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

    @Autowired
    ParticipationRepository participationRepository;

    @Autowired ParticipationService participationService;

    @Autowired ReservationService reservationService;

    @Autowired UserRepository userRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    EntityManager em;



    @Test
    public void 인원생성() throws Exception{

        //given

        User user1 = new User("asd","qwesd","chan","1234","23414123", Sex.MAN);
        User user2 = new User("asdasd","qwes1d","cha23n","123784","234123", Sex.MAN);

        em.persist(user1);
        em.persist(user2);


        AddReservationDto a = new AddReservationDto(LocalDate.of(2022, 11, 5), LocalTime.of(15, 30), "컴", "신창역", "순천향대 후문", Sex.ALL, 4, "고추", "미역");
        Long reserveId = reservationService.addReservation(a, "asd");

        System.out.println(reserveId);

        em.flush();
        em.clear();


    }

//
//    @Test
//    public void 경기삭제() throws Exception{
//
//        //given
//
//        User user1 = new User("asd","qwesd","chan","1234","23414123", Sex.MAN);
//        User user2 = new User("asdasd","qwes1d","cha23n","123784","234123", Sex.MAN);
//
//        em.persist(user1);
//        em.persist(user2);
//        em.flush();
//        em.clear();
//
//
//        AddReservationDto a = new AddReservationDto(LocalDate.of(2022, 11, 5), LocalTime.of(15, 30), "컴", "신창역", "순천향대 후문", Sex.ALL, 4, "고추", "미역");
//        AddReservationDto b = new AddReservationDto(LocalDate.of(2022, 11, 3), LocalTime.of(14, 30), "컴", "신창", "순천향대 후문", Sex.MAN, 2, "고기", "미역");
//        Long reserveId = reservationService.addReservation(a, "asd");
//        Long asdasd = reservationService.addReservation(b, "asdasd");
//
//
//        System.out.println(reserveId);
//        System.out.println("asdasd = " + asdasd);
//
//        em.flush();
//        em.clear();
//
//        Long id = reservationService.deleteReservation(1L, "asd");
//
//        System.out.println("id = " + id);
//
//        UpdateReservationDto updateReservationDto = new UpdateReservationDto(2L,"바꾸기");
//
//        Long asdasd1 = reservationService.updateReservation(updateReservationDto, "asdasd");
//
//        System.out.println(asdasd1);
//
//
//
//    }
//
    @Test
    public void 경기참여() throws Exception{

        //given

        User user1 = new User("asd","qwesd","chan","1234","23414123", Sex.MAN);
        User user2 = new User("asdasd","qwes1d","cha23n","123784","234123", Sex.MAN);
        User user3 = new User("123","123","park","1237434","223", Sex.MAN);

        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.flush();


        AddReservationDto a = new AddReservationDto(LocalDate.of(2022, 11, 5), LocalTime.of(15, 30), "컴", "신창역", "순천향대 후문", Sex.ALL, 4, "고추", "미역");
        AddReservationDto b = new AddReservationDto(LocalDate.of(2022, 11, 3), LocalTime.of(14, 30), "컴", "신창", "순천향대 후문", Sex.MAN, 2, "고기", "미역");
        Long reserveId = reservationService.addReservation(a, "asd");
        Long asdasd = reservationService.addReservation(b, "asdasd");


        Long participationId = participationService.addParticipation(1L, "asdasd");
        Long participationId2 = participationService.addParticipation(1L, "123");

        Reservation reserve = reservationRepository.findOne(reserveId);
        for (Participation participation : reserve.getParticipations()) {
            System.out.println("participation = " + participation.getReservation().getId());
            System.out.println("participation.getUser().getName() = " + participation.getUser().getName());


            Long id = participationService.deleteParticipation(1L, "123");




        }





    }





}