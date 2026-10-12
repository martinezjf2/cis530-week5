
package edu.bellevue.cis530_week5.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50,
            message = "First name must be between 2 and 50 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50,
            message = "Last name must be between 2 and 50 characters")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Major is required")
    @Column(name = "major")
    private String major;

    @NotNull(message = "GPA is required")
    @DecimalMin(value = "0.0",
            message = "GPA cannot be below 0.0")
    @DecimalMax(value = "4.0",
            message = "GPA cannot exceed 4.0")
    @Column(name = "gpa", precision = 3, scale = 2)
    private BigDecimal gpa;

    @NotNull(message = "Enrollment year is required")
    @Min(value = 2000,
            message = "Enrollment year must be 2000 or later")
    @Max(value = 2026,
            message = "Enrollment year cannot exceed the current year")
    @Column(name = "enrollment_year")
    private Integer enrollmentYear;

    @Valid
    @NotNull(message = "Student profile is required")
    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            optional = false
    )
    @JoinColumn(
            name = "profile_id",
            referencedColumnName = "id",
            nullable = false,
            unique = true
    )
    private StudentProfile profile;
}
