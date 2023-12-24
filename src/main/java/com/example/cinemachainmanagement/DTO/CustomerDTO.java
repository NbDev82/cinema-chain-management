package com.example.cinemachainmanagement.DTO;

import com.example.cinemachainmanagement.enums.ERole;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class CustomerDTO {

    private Long customerId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private int accountBalance;

    private String passHash;
    private ERole role;

    public CustomerDTO(String firstName, String lastName, String email, String phoneNumber, String passHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passHash = passHash;
    }

    public CustomerDTO(String firstName, String lastName, String email, String phoneNumber, int accountBalance, String passHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accountBalance = accountBalance;
        this.passHash = passHash;
    }
}
