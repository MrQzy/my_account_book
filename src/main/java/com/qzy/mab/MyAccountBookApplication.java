package com.qzy.mab;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.qzy.mab")
@EnableSwagger2
@ImportResource("classpath:applicationContext.xml")
public class MyAccountBookApplication {

    public static void main(String[] args) {

        SpringApplication.run(MyAccountBookApplication.class, args);
        log.info("MAB 启动成功");
    }

}
