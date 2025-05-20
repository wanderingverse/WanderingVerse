package com.wanderingverse;


import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

@Slf4j
@SpringBootTest
class ProjectTest {


    @Test
    public void projectTest() throws InterruptedException {
        Faker faker = new Faker(Locale.forLanguageTag("zh-CN"));
    }
}