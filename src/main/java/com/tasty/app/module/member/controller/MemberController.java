package com.tasty.app.module.member.controller;

import com.tasty.app.module.member.domain.Member;
import com.tasty.app.module.member.form.AddForm;
import com.tasty.app.module.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 등록 화면
    @GetMapping("/add")
    public String addForm(@ModelAttribute("form") AddForm form) {
        return "sign_up";
    }

    // 회원 등록 처리
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid @RequestBody AddForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }

        int res = memberService.addMember(form);
        Map<String, Object> response = new HashMap<>();
        response.put("res", res);
        response.put("defaultMessage", "중복된 이메일입니다");
        return response;
    }

    // 회원 조회 화면
    @GetMapping("/view")
    public String viewMember(
            @RequestParam("email") String email,
            @RequestParam(value = "menu_option", defaultValue = "0") int menuOption,
            Model model
    ) {
        Member member = memberService.getMemberByEmail(email);
        model.addAttribute("member", member);
        model.addAttribute("menuOption", menuOption);
        return "my/profile";
    }
}
