package org.demo.app.member.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.demo.app.member.domain.Member;

@Mapper
public interface MemberMapper {

    // 회원등록
    void insertOne(Member member);

    // 중복 이메일 조회
    int selectOneByEmail(String email);
}
