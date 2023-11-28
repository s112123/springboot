package org.demo.app.member.service;

import org.demo.app.member.dto.MemberForm;

public interface MemberService {

    // 회원등록
    Long addMember(MemberForm memberForm);
}
