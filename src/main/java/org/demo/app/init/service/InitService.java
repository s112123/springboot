package org.demo.app.init.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.app.init.repository.InitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class InitService {

    private final InitRepository initRepository;

    public void addMembers() {
        List<Map<String, String>> members = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("email", "temp" + i + "@naver.com");
            map.put("password", "1234");
            map.put("username", ((i % 2 == 0) ? "lee" : "kim") + i);
            members.add(map);
        }

        initRepository.save(members);
    }

    public void removeMembers() {
        initRepository.delete();
    }
}
