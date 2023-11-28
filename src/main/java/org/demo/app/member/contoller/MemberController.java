package org.demo.app.member.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.app.member.dto.MemberForm;
import org.demo.app.member.service.MemberService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원등록
    @GetMapping
    public String memberForm() {
        return "go member register page!";
    }

    @PostMapping
    public Object addMember(@RequestBody @Valid MemberForm memberForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        memberService.addMember(memberForm);
        return memberForm;
    }

    // 조회하기

}
