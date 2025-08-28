package com.learning.accounts.dto;

import com.learning.accounts.entity.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * records create final properties and object is immutable
 * since we have to update config properties at runtime, which we are fetching from spring cloud config server
 * we need to set property in object which cant be done using record
 * hence making it simple class
 */
//public record CustomerInfoDetails(String message, Map<String,String> contactDetails, List<String> onCallSupport) {
//}
@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class CustomerInfoDetails {
    private Map<String, String> contactDetails = new HashMap<>();
    private String message;
    private List<String> onCallSupport;

}
