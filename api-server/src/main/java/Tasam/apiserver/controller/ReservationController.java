package Tasam.apiserver.controller;


import Tasam.apiserver.dto.AddReservationDto;
import Tasam.apiserver.dto.response.ReservationResponseDto;
import Tasam.apiserver.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;



    // 경기 생성
    @PostMapping("reservation/add")
    public Long createReserve(@RequestBody AddReservationDto addReservationDto, @RequestParam(name = "userUid") String userUid) throws IOException {

        Long reservation = reservationService.addReservation(addReservationDto, userUid);

        return reservation;
    }
//nvb
//    // 날짜 정렬 방들 보여주기
//    @GetMapping("/test")
//    public List<ReservationResponseDto> getList(@RequestParam(name = "reserveDate") String reserveDate){
//        LocalDate date = LocalDate.parse(reserveDate, DateTimeFormatter.ISO_DATE);
//
//        return reservationService.getReservationSortList(date);
//    }


}
