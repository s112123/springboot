package org.demo.app.init.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface InitMapper {

    // 임시회원등록
    void insertMembers(@Param("members") List<Map<String, String>> members);

    // 전체회원삭제
    void deleteMembers();
}
