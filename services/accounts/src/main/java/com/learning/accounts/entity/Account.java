package com.learning.accounts.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {

    @Column(name = "customer_id")
    private Long customerId;
    @Id
    @Column(name = "account_number")
    private long accountNumber;
    private String accountType;
    @Column(name = "branch_address")
    private String branchAddress;
}
