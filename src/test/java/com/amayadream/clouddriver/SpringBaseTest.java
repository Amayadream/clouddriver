package com.amayadream.clouddriver;

import com.amayadream.clouddriver.core.dao.FilesRepository;
import com.amayadream.clouddriver.core.enums.FilesStatusEnum;
import com.amayadream.clouddriver.core.model.Files;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author : Amayadream
 * @date :   2017-09-13 16:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBaseTest {

    private static Logger logger = LoggerFactory.getLogger(SpringBaseTest.class);

    @Resource
    private FilesRepository filesRepository;

    @Test
    @Transactional
    public void baseTest() {
        Files files = new Files();
        files.setName("测试文件.txt");
        files.setCreatedTime(LocalDateTime.now());
        files.setPath("c:/1.txt");
        files.setSize(123L);
        files.setStatus(FilesStatusEnum.COMPLETED.value);
        files.setMd5("md5");
        files.setSuffix("txt");
        Files f = filesRepository.save(files);
        Assert.assertNotNull(f);
    }

}
