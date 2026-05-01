package org.alvesneto.accounts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@ToString
public class Accounts extends BaseEntity {

    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "account_number")
    private Long accoutNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;
}
