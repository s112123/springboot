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

    // 회원 계정 삭제
    void deleteMemberShip(String email);
    
    // 닉네임 중복 여부
    int selectOneByNickName(@Param("email") String email, @Param("nickName") String nickName);

    // 임시 비밀번호 저장
    void updatePassword(@Param("email") String email, @Param("password") String password);
}
