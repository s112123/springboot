package org.demo.app.web.login.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.demo.app.web.member.domain.Member;

@Mapper
public interface LoginMapper {

    // 회원조회
    // email로 조회
    Member selectOneByEmail(String email);
}
