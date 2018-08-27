package com.xyj.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmsApplication {

    public static void main(String[] args) {
      //  System.out.println(MD5Utils.MD5Encode("admin"));
        SpringApplication.run(EmsApplication.class, args);
    }
}
