package com.banking.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
    public class AuthDTO {
        private Integer id;
        private String email;
        private String password;
        private Integer accountId;


    }

