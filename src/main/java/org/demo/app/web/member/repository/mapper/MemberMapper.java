package org.demo.app.web.member.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.demo.app.web.member.domain.Member;

@Mapper
public interface MemberMapper {

    // 회원등록
    // memberId 반환
    void insertOne(Member member);

    // 회원조회
    // memberId로 조회
    Member selectOneById(Long memberId);
}
