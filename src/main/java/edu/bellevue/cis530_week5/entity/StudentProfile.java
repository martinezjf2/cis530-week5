
package edu.bellevue.cis530_week5.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_profiles")
@Getter
@Setter
@NoArgsConstructor
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^\\d{3}-\\d{3}-\\d{4}$",
            message = "Phone must follow XXX-XXX-XXXX format"
    )
    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @NotBlank(message = "State is required")
    @Size(min = 2, max = 2,
            message = "State must contain exactly 2 characters")
    @Column(name = "state", length = 2)
    private String state;

    @NotBlank(message = "ZIP code is required")
    @Pattern(
            regexp = "^\\d{5}$",
            message = "ZIP code must contain exactly 5 digits"
    )
    @Column(name = "zip", length = 5)
    private String zip;

    @NotBlank(message = "Emergency contact name is required")
    @Column(name = "emergency_contact_name")
    private String emergencyContactName;

    @NotBlank(message = "Emergency contact phone is required")
    @Column(name = "emergency_contact_phone")
    private String emergencyContactPhone;

    @JsonIgnore
    @OneToOne(mappedBy = "profile")
    private Student student;
}
