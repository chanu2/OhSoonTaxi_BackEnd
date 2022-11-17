package Tasam.apiserver.controller;


import Tasam.apiserver.domain.Reservation;
import Tasam.apiserver.dto.AddReservationDto;
import Tasam.apiserver.dto.response.ParticipatedReserveResponseDto;
import Tasam.apiserver.dto.response.ReservationResponseDto;
import Tasam.apiserver.dto.response.ReserveDetailResponseDto;
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

    //경기 세부 정보 보여주기
    @GetMapping("/list/detail/{reservationId}")
    public ResponseEntity reserve1(@PathVariable("reservationId") Long reservationId) {

        ReserveDetailResponseDto reservationDetail = reservationService.getReservationDetail(reservationId);


        return reservationDetail != null ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "경기 정보 보여주기 완료", reservationDetail), HttpStatus.OK) :
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "잘못된 요청", reservationDetail), HttpStatus.OK);

    }

    // 내가 참여한 경기 보여주기
    @GetMapping("/list/my")
    public ResponseEntity getParticipatedList(@RequestParam(name = "userUid") String userUid){


        List<ParticipatedReserveResponseDto> participatedReserveResponseDtoList = reservationService.participatedReservation(userUid);

        return participatedReserveResponseDtoList != null ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "경기 정보 보여주기 완료", participatedReserveResponseDtoList), HttpStatus.OK) :
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "잘못된 요청", participatedReserveResponseDtoList), HttpStatus.OK);

    }






}
