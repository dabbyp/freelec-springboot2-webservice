package com.jojoldu.book.springboot.config;

import com.jojoldu.book.springboot.Application;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties implements ApplicationRunner {
    @Value("${key}")
    private String key;

    public String getKey(){
        return key;
    }

    public static String strKey;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("================= key : " + key);
        strKey = key;
    }
}
