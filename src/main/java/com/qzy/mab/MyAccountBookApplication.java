package com.qzy.mab;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication
@EnableSwagger2
public class MyAccountBookApplication {

    public static void main(String[] args) {

        SpringApplication.run(MyAccountBookApplication.class, args);
        log.info("MAB 启动成功");
    }

}
