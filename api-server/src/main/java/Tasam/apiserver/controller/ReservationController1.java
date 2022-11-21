package Tasam.apiserver.controller;


import Tasam.apiserver.dto.AddReservationDto;
import Tasam.apiserver.dto.UpdateReservationDto;
import Tasam.apiserver.dto.response.*;
import Tasam.apiserver.response.DefaultRes;
import Tasam.apiserver.response.StatusCode;
import Tasam.apiserver.service.ReservationService;
import Tasam.apiserver.service.ReservationService1;
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
public class ReservationController1 {

    private final ReservationService1 reservationService;


    // 경기 생성
    @PostMapping("/add1")
    public ResponseEntity createReserve(@RequestBody AddReservationDto addReservationDto, @RequestParam(name = "userUid") String userUid) throws IOException {

        Long reservationId = reservationService.addReservation(addReservationDto, userUid);

        return reservationId !=null ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "방생성 완료"), HttpStatus.OK):
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "잘못된 요청"), HttpStatus.OK);
    }

    // 경기 삭제
    @DeleteMapping("/delete1/{reservationId}")
    public ResponseEntity deleteReserve(@PathVariable("reservationId")Long reservationId, @RequestParam(name = "userUid") String userUid) throws IOException {

        Long deleteReservationId = reservationService.deleteReservation(reservationId, userUid);

        return deleteReservationId !=null ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "방 삭제 완료"), HttpStatus.OK):
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "잘못된 요청"), HttpStatus.OK);
    }

    //방 정보 업데이트
    @PostMapping("reserves1/update")
    public ResponseEntity updateReservation(@RequestBody UpdateReservationDto updateReservationDto,@RequestParam(name = "userUid") String userUid) throws IOException{

        Long reservationId = reservationService.updateReservation(updateReservationDto, userUid);

        return reservationId != null ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "약속 수정 완료"), HttpStatus.OK) :
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "잘못된 요청"), HttpStatus.OK);    }




    // 날짜 정렬 방들 보여주기
    @GetMapping("/list/date1")
    public ResponseEntity getReservationList(@RequestParam(name = "reserveDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate reserveDate){
 
        List<ReservationResponseDto> reservation = reservationService.getReservationSortList(reserveDate);

        return reservation.size() != 0 ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "방 조회 완료", reservation), HttpStatus.OK):
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "방 없음", new ArrayList()), HttpStatus.OK);

    }

    //경기 세부 정보 보여주기
    @GetMapping("/list/detail1/{reservationId}")
    public ResponseEntity reserve1(@PathVariable("reservationId") Long reservationId) {

        ReserveDetailResponseDto reservationDetail = reservationService.getReservationDetail(reservationId);


        return reservationDetail != null ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "경기 정보 보여주기 완료", reservationDetail), HttpStatus.OK) :
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "잘못된 요청", reservationDetail), HttpStatus.OK);

    }

    // 내가 참여한 경기 보여주기
    @GetMapping("/list1/my1")
    public ResponseEntity getParticipatedList(@RequestParam(name = "userUid") String userUid){


        List<ParticipatedReserveResponseDto> participatedReserveResponseDtoList = reservationService.participatedReservation(userUid);

        return participatedReserveResponseDtoList != null ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "경기 정보 보여주기 완료", participatedReserveResponseDtoList), HttpStatus.OK) :
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "잘못된 요청", participatedReserveResponseDtoList), HttpStatus.OK);

    }

    //내가 생성한 약속 보여주기
    @GetMapping("/list1/reserved-me")
    public ResponseEntity getReservedByMe(@RequestParam(name = "userUid") String userUid){


        List<ReservedByMeResponseDto> reservedByMeResponseDtoList = reservationService.reservedByMe(userUid);

        return reservedByMeResponseDtoList != null ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "경기 정보 보여주기 완료", reservedByMeResponseDtoList), HttpStatus.OK) :
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "잘못된 요청",reservedByMeResponseDtoList), HttpStatus.OK);

    }

    //키워드 검색을 통한 방 리스트 보여주기

    @GetMapping("/search")
    public ResponseEntity getSearchReservation(@RequestParam(name = "keyword") String keyword){


        List<SearchResponseDto> searchReservation = reservationService.getSearchReservation(keyword);

        return searchReservation != null ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "경기 정보 보여주기 완료", searchReservation), HttpStatus.OK) :
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "잘못된 요청",searchReservation), HttpStatus.OK);

    }

    //암구호 보여주기
    @GetMapping("/passphrase/{reservationId}")
    public ResponseEntity getPassphrase(@PathVariable("reservationId") Long reservationId){

        PassphraseResponseDto passphrase = reservationService.getPassphrase(reservationId);

        return passphrase != null ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "경기 정보 보여주기 완료", passphrase), HttpStatus.OK) :
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "잘못된 요청",passphrase), HttpStatus.OK);

    }






}
