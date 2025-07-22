package com.mdmuntasirazad.blog.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

	private int id;

	// Use @NotBlank for better validation and add a custom message and size constraint
	@jakarta.validation.constraints.NotBlank(message = "User name cannot be blank!")
	@jakarta.validation.constraints.Size(min = 4, message = "Username must be a minimum of 4 characters!")
	private String name;
	
	// Added the missing @Email import and a custom message
	@jakarta.validation.constraints.Email(message = "The email address is not valid!")
	@jakarta.validation.constraints.NotBlank(message = "Email cannot be blank!")
	private String email;
	
	// Use @NotBlank and @Size for the password
	@jakarta.validation.constraints.NotBlank(message = "Password cannot be blank!")
	@jakarta.validation.constraints.Size(min = 3, max = 10, message = "Password must be between 3 and 10 characters!")
	private String password;
	
	// Use @NotBlank for the about field
	@jakarta.validation.constraints.NotBlank(message = "About section cannot be blank!")
	private String about;
	
}