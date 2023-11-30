package org.demo.app.member.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.app.member.dto.MemberForm;
import org.demo.app.member.exception.DuplicateEmailException;
import org.demo.app.member.exception.dto.ErrorResponse;
import org.demo.app.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberControllerR {

    private final MemberService memberService;

    // 회원등록
    @GetMapping
    public String memberForm() {
        return "go member register page!";
    }

    @PostMapping
    public ResponseEntity<Object> addMember(@RequestBody @Valid MemberForm memberForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        memberService.addMember(memberForm);
        return new ResponseEntity(memberForm, HttpStatus.OK);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> duplicateEmailException(DuplicateEmailException e) {
        ErrorResponse errorResponse = new ErrorResponse("duplicated", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // 조회하기
}
