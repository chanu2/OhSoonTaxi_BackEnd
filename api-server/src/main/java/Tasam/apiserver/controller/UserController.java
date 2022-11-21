package Tasam.apiserver.controller;

import Tasam.apiserver.config.JwtTokenProvider;
import Tasam.apiserver.domain.user.User;
import Tasam.apiserver.dto.SignInDto;
import Tasam.apiserver.dto.SignUpDto;
import Tasam.apiserver.response.DefaultRes;
import Tasam.apiserver.response.StatusCode;
import Tasam.apiserver.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;


    // 아이디 중복체크
    @PostMapping("/checkUnique")
    public ResponseEntity check(@RequestBody HashMap<String, String> param) {
        String uid = param.get("uid");
        Boolean result = userService.checkUnique(uid);

        return result ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "사용가능한 아이디입니다"), HttpStatus.OK):
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "중복된 아이디입니다"), HttpStatus.OK);
    }

    // 회원가입 요청
    @PostMapping("/signUp")
    public ResponseEntity signUp(@RequestBody SignUpDto user) {
        Long result = userService.signUp(user);

        return result != null ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "회원가입 요청을 성공하였습니다"), HttpStatus.OK):
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "잘못된 요청"), HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/signIn")
    public ResponseEntity signIn(@RequestBody SignInDto user, HttpServletResponse response) {
        User member = userService.findUserByUid(user.getUid());
        if (member == null) return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "없는 사용자"), HttpStatus.OK);

        Boolean isRight = userService.checkPassword(member, user);
        System.out.println(isRight);
        if (!isRight) return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "잘못된 비밀번호"), HttpStatus.OK);

        // 어세스, 리프레시 토큰 발급 및 헤더 설정
        String accessToken = jwtTokenProvider.createAccessToken(member.getUid(), member.getRoles());
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getUid(), member.getRoles());

        jwtTokenProvider.setHeaderAccessToken(response, accessToken);
        jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);

        // 리프레시 토큰 저장소에 저장
        userService.signIn(refreshToken, member);

        if (user.getUid().equals("admin")) return new ResponseEntity(DefaultRes.res(StatusCode.OK, "관리자 로그인 완료", "ROLE_ADMIN"), HttpStatus.OK);
        else if (user.getUid().equals("guest")) return new ResponseEntity(DefaultRes.res(StatusCode.OK, "게스트 로그인 완료", "ROLE_GUEST"), HttpStatus.OK);
        else return new ResponseEntity(DefaultRes.res(StatusCode.OK, "사용자 로그인 완료", "ROLE_USER"), HttpStatus.OK);
    }


    //로그아웃
    @PostMapping("/signOut")
    public ResponseEntity signOut(@RequestHeader("RefreshToken") String refreshToken, @RequestParam(name = "userUid")String userUid) {
        refreshToken = refreshToken.substring(7);
        User user = userService.findUserByUid(userUid);
        Boolean existAndOut = userService.signOut(refreshToken,user);

        return existAndOut ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "로그아웃 완료"), HttpStatus.OK):
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "잘못된 요청"), HttpStatus.OK);
    }

}
