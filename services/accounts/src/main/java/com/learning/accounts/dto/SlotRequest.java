package com.learning.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is just for Tesco interview preparation purpose
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotRequest {
    String storeId;
    int start;
    int end;
}
