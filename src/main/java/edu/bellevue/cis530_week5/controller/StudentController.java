
package edu.bellevue.cis530_week5.controller;

import edu.bellevue.cis530_week5.entity.Student;
import edu.bellevue.cis530_week5.entity.StudentProfile;
import edu.bellevue.cis530_week5.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(
            @Valid @RequestBody Student student) {

        Student createdStudent =
                studentService.createStudent(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {

        List<Student> students =
                studentService.getAllStudents();

        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable Long id) {

        Student student =
                studentService.getStudentById(id);

        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {

        Student updatedStudent =
                studentService.updateStudent(id, student);

        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<StudentProfile> getProfileByStudentId(
            @PathVariable Long id) {

        StudentProfile profile =
                studentService.getProfileByStudentId(id);

        return ResponseEntity.ok(profile);
    }

    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(
            @PathVariable String major) {

        List<Student> students =
                studentService.getStudentsByMajor(major);

        return ResponseEntity.ok(students);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Student>> getStudentsByEnrollmentYear(
            @PathVariable Integer year) {

        List<Student> students =
                studentService.getStudentsByEnrollmentYear(year);

        return ResponseEntity.ok(students);
    }
}
