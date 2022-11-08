package Tasam.apiserver.controller;


import Tasam.apiserver.dto.AddReservationDto;
import Tasam.apiserver.dto.response.ReservationResponseDto;
import Tasam.apiserver.response.DefaultRes;
import Tasam.apiserver.response.StatusCode;
import Tasam.apiserver.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;



    // 경기 생성
    @PostMapping("/add")
    public ResponseEntity createReserve(@RequestBody AddReservationDto addReservationDto, @RequestParam(name = "userUid") String userUid) throws IOException {

        Long reservationId = reservationService.addReservation(addReservationDto, userUid);

        return reservationId !=null ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "방생성 완료"), HttpStatus.OK):
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "잘못된 요청"), HttpStatus.OK);
    }


    // 날짜 정렬 방들 보여주기
    @GetMapping("/list/date")
    public ResponseEntity getReservationList(@RequestParam(name = "reserveDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate reserveDate){
        //LocalDate date = LocalDate.parse(reserveDate, DateTimeFormatter.ISO_DATE);

        List<ReservationResponseDto> reservation = reservationService.getReservationSortList(reserveDate);

        return reservation.size() != 0 ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "방 조회 완료", reservation), HttpStatus.OK):
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "방 없음", new ArrayList()), HttpStatus.OK);

    }


}
