package org.twspring.lab10.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(25) NOT NULL")
    @NotEmpty(message = "Tile cannot be empty")
    @Size(min = 5, max = 25, message = "Title must have between 5 to 25 letters")
    private String tile;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    @NotEmpty(message = "Description cannot be empty")
    @Size(max = 255, message = "Description cannot have more then 255 characters")
    private String description;

    @Column(columnDefinition = "VARCHAR(155) NOT NULL")
    @NotEmpty(message = "Location cannot be empty")
    @Size(max = 155, message = "Location cannot have more then 155 characters")
    private String location;

    @Column(columnDefinition = "double NOT NULL")
    @NotNull(message = "Salary cannot be empty")
    @Positive(message = "Salary can only be a positive number")
    private String salary;

    @Column(columnDefinition = "DATETIME DEFAULT TIMESTAMP(CURRENT_DATE)")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;

}
