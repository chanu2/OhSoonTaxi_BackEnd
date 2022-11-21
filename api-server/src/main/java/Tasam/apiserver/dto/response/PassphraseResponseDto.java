package Tasam.apiserver.dto.response;

import Tasam.apiserver.domain.Reservation;
import lombok.Getter;

@Getter
public class PassphraseResponseDto {

    private String challengeWord;
    private String countersignWord;

    public PassphraseResponseDto(Reservation reservation) {
        challengeWord = reservation.getChallengeWord();
        countersignWord = reservation.getCountersignWord();
    }
}
