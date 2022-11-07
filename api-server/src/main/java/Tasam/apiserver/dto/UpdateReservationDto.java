package Tasam.apiserver.dto;


import lombok.Getter;

@Getter
public class UpdateReservationDto {

    private Long id;
    private String title;

    public UpdateReservationDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
