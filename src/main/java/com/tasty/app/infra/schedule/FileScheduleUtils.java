package com.tasty.app.infra.schedule;

import com.tasty.app.infra.file.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.File;
import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileScheduleUtils {

    @Value("${upload.dir.member}")
    private String uploadDirMember;
    @Value("${upload.dir.review}")
    private String uploadDirReview;

    private final FileUtils fileUtils;

    // 1일마다 2일전 임시 파일 지우기 (임시파일은 파일명 앞에 "2024-02-01_" 접두어가 붙은 파일)
    //@Async
    //@Scheduled(cron = "0/30 * * * * ?")
    @Scheduled(fixedRate = 1000 * 30)
    public void deleteTempFiles() {
        // 파일을 저장하는 경로가 2개 밖에 없으므로 공통인 부모 폴더 대신 해당 경로를 바로 순환
        String[] uploadDirPaths = new String[] {uploadDirMember, uploadDirReview};
        // 오늘날짜에서 1일 전
        // 2일전 임시파일을 지우지면 isBefore()은 기준날짜 이전까지만 판단하여 2일째 날짜는 false이므로 1일전으로 판단
        LocalDate beforeOneDays = LocalDate.now().minusDays(1);

        for (String uploadDirPath : uploadDirPaths) {
            File uploadDir = new File(uploadDirPath);
            File[] tempFiles = uploadDir.listFiles();

            log.info("uploadDirPath={}", uploadDirPath);
            for (File tempFile : tempFiles) {
                String tempFileName = tempFile.getName();
                if (tempFileName.indexOf("_") != -1) {
                    // 파일명에서 접두어로 있는 날짜를 뽑아서 LocalDate 타입으로 변경
                    String tempDate = tempFileName.split("_")[0];
                    LocalDate uploadDate = LocalDate.parse(tempDate);

                    // 기준 날짜보다 2일 전인 파일은 삭제
                    if (uploadDate.isBefore(beforeOneDays)) {
                        // 파일 삭제
                        fileUtils.deleteFile(uploadDirPath, tempFileName);
                        log.info("삭제된 파일명: {}", tempFileName);
                    }
                }
            }
        }
    }
}
