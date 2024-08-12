package org.twspring.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(25) NOT NULL")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 5, max = 25, message = "Name must have between 5 to 25 letters")
    @Pattern(regexp = "[A-Za-z ]+" , message = "Name only contains letters and spaces")
    private String name;

    @Column(columnDefinition = "VARCHAR(25) UNIQUE")
    @Size(max = 25, message = "Emil cannot have more then 25 characters")
    @Email(message = "Invalid email format")
    private String email;

    @Column(columnDefinition = "VARCHAR(25) NOT NULL")
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 7, max = 25, message = "Password contain between 7 to 25 characters")
    private String password;

    @Column(columnDefinition = "INT NOT NULL") //@Column(columnDefinition = "INT NOT NULL CHECK (AGE>=21)")
    @NotNull(message = "Age cannot be null")
    @Positive(message = "Age must be a positive number")
    @Min(value = 21, message = "Age cannot be less than 21")
    private int age;

    @Column(columnDefinition = "VARCHAR(25) NOT NULL") //@Column(columnDefinition = "VARCHAR(25) NOT NULL CHECK (username='JOB_SEEKER' or username='EMPLOYER')")
    @Size(max=25,message = "Role cannot have more than 25 characters")
    @NotEmpty(message = "Role cannot be empty")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)$" , message = "Role can only be (JOB_SEEKER) or (EMPLOYER)")
    private String role;

}
