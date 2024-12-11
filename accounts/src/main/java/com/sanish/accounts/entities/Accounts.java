package com.sanish.accounts.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Accounts extends BaseEntity {

    @Id
    private Long accountNumber;

    private String accountType;

    private String branchAddress;

    private Long customerId;
}
