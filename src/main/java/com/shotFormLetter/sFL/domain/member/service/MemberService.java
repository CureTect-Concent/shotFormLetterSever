package com.shotFormLetter.sFL.domain.member.service;

import com.shotFormLetter.sFL.domain.member.domain.*;
import com.shotFormLetter.sFL.domain.member.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(MemberDto memberDto){
        Optional<Member> existingMember = memberRepository.findByUserId(memberDto.getUserId());
        if (existingMember.isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }
        Optional<Member> existingNickname = memberRepository.findByUserName(memberDto.getUserName());
        if (existingNickname.isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }
        Member member = Member.builder()
                .userId(memberDto.getUserId())
                .userName(memberDto.getUserName())
                .password(passwordEncoder.encode(memberDto.getPassword()))  //비밀번호 인코딩
                .roles(Collections.singletonList("ROLE_USER"))         //roles는 최초 USER로 설정
                .build();
        return memberRepository.save(member).getId();
    }

    @Transactional
    public TokenUser login(LoginDto loginDto){
            Member member = memberRepository.findByUserId(loginDto.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다."));
            if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
                throw new IllegalArgumentException("잘못된 비밀번호입니다.");
            }
            System.out.println(member.getUsername());
            String token = jwtTokenProvider.createToken(member.getUserId(), member.getRoles());
            TokenUser tokenUser =new TokenUser();
            tokenUser.setToken(token);
            tokenUser.setUserName(member.getUsername());
            tokenUser.setId(member.getId());
            return tokenUser;
    }
}

//    Optional<Member> userName=memberRepository.findByUserName(loginDto.getUserId());
//    Member nowName = userName.get();
//    Member member = memberRepository.findByUserId(loginDto.getUserId())
//            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다."));
//        if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
//                throw new IllegalArgumentException("잘못된 비밀번호입니다.");
//                }
//
////        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
//                String token = jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
//                TokenUser tokenUser =new TokenUser();
//                tokenUser.setToken(token);
//                tokenUser.setUserName(nowName.getUsername());
//                tokenUser.setId(member.getId());
//                return tokenUser;
//Optional<Member> userNameOptional = memberRepository.findByUserName(loginDto.getUserId());
//        if (userNameOptional.isPresent()) {
//                Member nowName = userNameOptional.get();
//                Member member = memberRepository.findByUserId(loginDto.getUserId())
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다."));
//
//                if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
//                throw new IllegalArgumentException("잘못된 비밀번호입니다.");
//                }
//
//                String token = jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
//                TokenUser tokenUser = new TokenUser();
//                tokenUser.setToken(token);
//                tokenUser.setUserName(nowName.getUserId());
//                tokenUser.setId(member.getId());
//                return tokenUser;
//                } else {
//                throw new IllegalArgumentException("가입되지 않은 ID 입니다.");
//                }