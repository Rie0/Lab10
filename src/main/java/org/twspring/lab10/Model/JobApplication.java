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
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "INT NOT NULL")
    @NotNull(message = "User Id cannot be null")
    private Integer userId;

    @Column(columnDefinition = "INT NOT NULL")
    @NotNull(message = "Job post Id cannot be null")
    private Integer jobPostId;

}
