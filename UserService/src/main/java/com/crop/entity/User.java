package com.crop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection="User")
public class User {
	@Id
	
	private String id;
	@NotBlank(message = "User Name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "User Name should contain only alphabets")
    private String userName;

    @NotBlank(message = "User Email is required")
    @Email(message = "User Email should be valid")
    private String userEmail;

    @NotBlank(message = "User Password is required")
    @Size(min = 8, message = "User Password should be at least 8 characters long")
    private String userPassword;
    private long mobileNumber;

    @NotBlank(message = "Role is required")
    private String roles;

    @NotNull(message = "Address is required")
    private Address address;
  
}
