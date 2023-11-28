package org.demo.app.init.repository;

import lombok.RequiredArgsConstructor;
import org.demo.app.init.repository.mapper.InitMapper;
import org.demo.app.member.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class InitRepository {

    private final InitMapper initMapper;

    public void save(List<Map<String, String>> members) {
        initMapper.insertMembers(members);
    }

    public void delete() {
        initMapper.deleteMembers();
    }
}
