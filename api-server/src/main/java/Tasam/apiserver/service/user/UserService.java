package Tasam.apiserver.service.user;

import Tasam.apiserver.domain.user.RefreshToken;
import Tasam.apiserver.domain.user.User;
import Tasam.apiserver.dto.SignInDto;
import Tasam.apiserver.dto.SignUpDto;
import Tasam.apiserver.filter.CustomAuthenticationEntryPoint;
import Tasam.apiserver.repository.user.RefreshTokenRepository;
import Tasam.apiserver.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RefreshTokenRepository refreshTokenRepository;

    //private final TokenRepositorySupport tokenRepositorySupport;


    // 회원 가입
    public Long signUp(SignUpDto signUpDto) {
        Long id = userRepository.save(
                        User.builder()
                                .uid(signUpDto.getUid())
                                .password(passwordEncoder.encode(signUpDto.getPassword()))
                                .name(signUpDto.getName())
                                .schoolNum(signUpDto.getSchoolNum())
                                .phoneNum(signUpDto.getPhoneNum())
                                .sex(signUpDto.getSex())
                                .roles(Collections.singletonList("ROLE_USER"))
                                .build())
                .getId();
        return id;
    }



    public User findUserByUid(String user) {
        User member = userRepository.findByUid(user).get();
        return member;
    }

    public Boolean signIn (String refreshToken,User user) {

        refreshTokenRepository.save(new RefreshToken(refreshToken));


        logger.info(user.getUid() + " (id : " + user.getId() + ") login");
        return true;
    }


    // 로그아웃
    public Boolean signOut (String refreshToken,User user) {
        if(!refreshTokenRepository.existsByRefreshToken(refreshToken)) return false;

        refreshTokenRepository.deleteByRefreshToken(refreshToken);

        logger.info(user.getUid() + " (id : " + user.getId() + ") logout");

        return true;
    }


    public Boolean checkUnique(String uid) {
        Boolean result = userRepository.existsByUid(uid);  // 아이디가 존재하면 true

        return !result;
    }
    public boolean checkPassword(User member, SignInDto user) {
        return passwordEncoder.matches(user.getPassword(), member.getPassword());
    }
//
//    public MyPageResponseDto getMyPageInfo(String userUid) {
//        User user = userRepository.findByUid(userUid).get();
//        MyPageResponseDto myPageResponseDto = new MyPageResponseDto(user);
//        if (myPageResponseDto.getUser().getIsFarmer()) {
//            myPageResponseDto.setFarmerName(userFarmerDetailRepository.findById(user.getId()).get().getFarmerName());
//            myPageResponseDto.setCropHandling(userFarmerDetailRepository.findById(user.getId()).get().getCropHandling());
//        } else {
//            myPageResponseDto.setDistributorName(userDistributorDetailRepository.findById(user.getId()).get().getDistributorName());
//            myPageResponseDto.setBusinessRegistrationNum(userDistributorDetailRepository.findById(user.getId()).get().getBusinessRegistrationNum());
//        }
//        return myPageResponseDto;
//    }
//
//    public GetNameResponseDto getName(String userUid) {
//        GetNameResponseDto result = new GetNameResponseDto();
//        User user = userRepository.findByUid(userUid).get();
//        if (user.getIsFarmer()) {
//            result.setIsFarmer(true);
//            result.setName(userFarmerDetailRepository.findById(user.getId()).get().getFarmerName());
//            return result;
//        }
//        else {
//            result.setIsFarmer(false);
//            result.setName(userDistributorDetailRepository.findById(user.getId()).get().getDistributorName());
//            return result;
//        }
//    }
}
