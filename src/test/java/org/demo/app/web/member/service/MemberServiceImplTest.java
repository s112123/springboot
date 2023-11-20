package org.demo.app.web.member.service;

import org.demo.app.web.member.dto.MemberForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Test
    void addMember() {
        MemberForm memberForm = new MemberForm();
        memberForm.setEmail("a1234@naver.com");
        memberForm.setPassword("1234");
        memberForm.setUsername("lee");

        Long savedId = memberService.addMember(memberForm);
        MemberForm findMember = memberService.getMemberById(savedId);

        assertThat(savedId).isEqualTo(findMember.getMemberId());
    }
}