package com.SmartContactManager.SCM.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserForm {
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    private String email;
    @NotBlank
    @Size(min = 6,message = "Min 6 character")
    private String password;
    @NotBlank
    private String phone;

}
