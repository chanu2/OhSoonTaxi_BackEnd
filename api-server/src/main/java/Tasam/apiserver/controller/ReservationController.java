package Tasam.apiserver.controller;


import Tasam.apiserver.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reserveService;



//    // 경기 생성
//    @PostMapping("/add")
//    public ResponseEntity createReserve(@RequestParam(name = "") @RequestBody CreateReserveDto createReserveDto){
//
//        Long reserve = reserveService.reserve(createReserveDto);
//
//        return reserve != null ?
//                new ResponseEntity(DefaultRes.res(StatusCode.OK, "구장 예약 완료"), HttpStatus.OK) :
//                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "잘못된 요청"), HttpStatus.OK);
//    }


}
