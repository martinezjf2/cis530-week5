
package edu.bellevue.cis530_week5.service.impl;

import edu.bellevue.cis530_week5.entity.Student;
import edu.bellevue.cis530_week5.entity.StudentProfile;
import edu.bellevue.cis530_week5.repository.StudentRepository;
import edu.bellevue.cis530_week5.service.StudentService;
import edu.bellevue.cis530_week5.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(
            StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {

        StudentProfile profile = student.getProfile();

        if (profile == null) {
            throw new IllegalArgumentException(
                    "Student profile is required"
            );
        }

        profile.setStudent(student);

        return studentRepository.save(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with ID: " + id
                        )
                );
    }

    @Override
    public Student updateStudent(Long id, Student student) {

        Student existingStudent = getStudentById(id);

        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setMajor(student.getMajor());
        existingStudent.setGpa(student.getGpa());
        existingStudent.setEnrollmentYear(
                student.getEnrollmentYear()
        );

        StudentProfile updatedProfile = student.getProfile();

        if (updatedProfile == null) {
            throw new IllegalArgumentException(
                    "Student profile is required"
            );
        }

        StudentProfile existingProfile =
                existingStudent.getProfile();

        if (existingProfile == null) {
            updatedProfile.setId(null);
            updatedProfile.setStudent(existingStudent);
            existingStudent.setProfile(updatedProfile);
        } else {
            existingProfile.setPhone(updatedProfile.getPhone());
            existingProfile.setAddress(updatedProfile.getAddress());
            existingProfile.setCity(updatedProfile.getCity());
            existingProfile.setState(updatedProfile.getState());
            existingProfile.setZip(updatedProfile.getZip());

            existingProfile.setEmergencyContactName(
                    updatedProfile.getEmergencyContactName()
            );

            existingProfile.setEmergencyContactPhone(
                    updatedProfile.getEmergencyContactPhone()
            );

            existingProfile.setStudent(existingStudent);
        }

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {

        Student student = getStudentById(id);

        studentRepository.delete(student);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentProfile getProfileByStudentId(Long id) {

        Student student = getStudentById(id);

        return student.getProfile();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getStudentsByMajor(String major) {

        return studentRepository.findByMajor(major);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getStudentsByEnrollmentYear(
            Integer year) {

        return studentRepository.findByEnrollmentYear(year);
    }
}
