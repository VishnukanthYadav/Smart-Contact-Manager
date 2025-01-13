package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message="Username is Required ! ")
    @Size(min=3,message="Minimum 3  Characters are Required")
    private String name;
    @NotBlank(message="Email is Required")
    @Email(message="Invalid email Address")
    private String email;
    @NotBlank(message="Password is Required")
    @Size(min=6,message = "Min 6 Characters are Required")
    private String password;
    @NotBlank(message = "About is Required")
    private String about;
    @Size(min=6,max=12, message = "Invalid Phone Number")
    private String phoneNumber;
}
