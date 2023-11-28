package org.demo.app.init.service;

import lombok.RequiredArgsConstructor;
import org.demo.app.init.repository.InitRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InitService {

    private final InitRepository initRepository;

    public void addMembers() {
        List<Map<String, String>> members = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("email", "temp" + i + "@naver.com");
            map.put("password", BCrypt.hashpw("1234", BCrypt.gensalt()));
            map.put("username", ((i % 2 == 0) ? "lee" : "kim") + i);
            members.add(map);
        }

        initRepository.save(members);
    }

    public void removeMembers() {
        initRepository.delete();
    }
}
