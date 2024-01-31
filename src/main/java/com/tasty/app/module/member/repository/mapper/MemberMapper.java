package com.tasty.app.module.member.repository.mapper;

import com.tasty.app.module.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface MemberMapper {

    // 회원 등록
    void insertOne(Member member);

    // 이메일로 회원 조회
    Member selectOneByEmail(@Param("email") String email);

    // 회원 정보 변경
    Long updateOneByEmail(@Param("params") Map<String, Object> params);
}
