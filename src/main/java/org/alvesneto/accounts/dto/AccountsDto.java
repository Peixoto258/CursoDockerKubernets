package org.alvesneto.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
public class AccountsDto {
    @NotEmpty(message = "accountNumber can not be empty or null!")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number has to have 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "accountType Can not be empty or null!")
    private String accountType;

    private String branchAddress;
}
