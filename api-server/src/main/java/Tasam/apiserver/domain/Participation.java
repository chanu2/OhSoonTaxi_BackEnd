package Tasam.apiserver.domain;


import lombok.Builder;

import javax.persistence.*;

@Entity
public class Participation {

    @Id @GeneratedValue
    @Column(name ="participation _id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservations;


    //==생성 메서드==//

    @Builder
    public Participation(Long id, User user, Reservation reservations) {
        this.id = id;
        this.user = user;
        this.reservations = reservations;


    }

}
