package com.microservices.learning.message.functions;

import com.microservices.learning.message.dto.AccountMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;


@Configuration
public class MessageFunction {
    private static final Logger log = LoggerFactory.getLogger(MessageFunction.class);

    @Bean
    public Function<AccountMsgDto, AccountMsgDto> sendEmailNotification() {
        /**
         * without lambda
         */
        Function<AccountMsgDto,AccountMsgDto> function = new Function<AccountMsgDto, AccountMsgDto>() {
            @Override
            public AccountMsgDto apply(AccountMsgDto accountMsgDto) {
                log.info("Sending email with details {}",accountMsgDto);
                return accountMsgDto;
            }
        };
        return function;
//        return accountsMsgDto -> {
//            log.info("Sending email with details: {}", accountsMsgDto.toString());
//            return accountsMsgDto;
//        };
    }

    @Bean
    public Function<AccountMsgDto, Long> sendSmsNotification() {
        return accountsMsgDto -> {
            log.info("Sending sms with details: {}", accountsMsgDto.toString());
            return accountsMsgDto.accountNumber();
        };
    }
}
