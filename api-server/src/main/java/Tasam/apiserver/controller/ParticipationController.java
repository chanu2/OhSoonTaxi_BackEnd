package Tasam.apiserver.controller;


import Tasam.apiserver.dto.AddParticipationDto;
import Tasam.apiserver.response.DefaultRes;
import Tasam.apiserver.response.StatusCode;
import Tasam.apiserver.service.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/participation")
public class ParticipationController {

    private final ParticipationService participationService;


    //참여 하기

    @PostMapping("/add")
    public ResponseEntity addParticipate(@RequestBody AddParticipationDto addParticipationDto,@RequestParam(name = "userUid") String userUid){
        Long participationId = participationService.addParticipation(addParticipationDto, userUid);

        return participationId != null ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "경기 참여 완료"), HttpStatus.OK) :
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "잘못된 요청"), HttpStatus.OK);
    }


    // 참여 취소
    @DeleteMapping("/delete/{reservationId}")
    public ResponseEntity deleteParticipation(@PathVariable Long reservationId,@RequestParam(name = "userUid") String userUid){

        Long participationId = participationService.deleteParticipation(reservationId, userUid);


        return participationId != null ?

                new ResponseEntity(DefaultRes.res(StatusCode.OK, "경기 참여 취소"), HttpStatus.OK):
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "잘못된 요청"), HttpStatus.OK);
    }




}
