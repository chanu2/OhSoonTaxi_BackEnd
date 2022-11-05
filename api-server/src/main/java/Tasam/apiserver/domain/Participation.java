package Tasam.apiserver.domain;


import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Participation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="participation_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;



    @Builder
    public Participation( User user, Reservation reservation) {
        this.user = user;
        this.reservation = reservation;

    }


    //==생성 메서드==//
    public static Participation createParticipation(User user,Reservation reservation){
        return builder()
                .user(user)
                .reservation(reservation)
                .build();
    }

}
