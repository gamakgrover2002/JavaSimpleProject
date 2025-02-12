package com.example.JavaProject.DTO.Response;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String userName;
    private String password;
    private String name;
}
