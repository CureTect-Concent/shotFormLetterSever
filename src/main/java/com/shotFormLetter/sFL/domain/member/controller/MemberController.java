package com.shotFormLetter.sFL.domain.member.controller;

import com.shotFormLetter.sFL.domain.member.domain.LoginDto;
import com.shotFormLetter.sFL.domain.member.domain.MemberDto;
import com.shotFormLetter.sFL.domain.member.domain.TokenUser;
import com.shotFormLetter.sFL.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원가입 API
    @PostMapping("/join")
    public ResponseEntity<String> join(@Valid @RequestBody MemberDto memberDto) {
        Long memberId = memberService.join(memberDto);
        String message = "Okay!";
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"" + message + "\"}");
    }

    // 로그인 API
//    @PostMapping("/login")
//    public String login(@RequestBody MemberDto memberDto) {
//        memberService.login(memberDto);
//        return memberService.login(memberDto);
//    }
    @PostMapping("/login")
    public ResponseEntity<TokenUser> login(@Valid @RequestBody LoginDto loginDto) {
        TokenUser getToken= memberService.login(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(getToken);
    }
}
