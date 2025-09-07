package com.learning.accounts;

import com.learning.accounts.dto.CustomerInfoDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableConfigurationProperties(value = {CustomerInfoDetails.class})
@EnableFeignClients
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
