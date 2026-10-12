
package edu.bellevue.cis530_week5.repository;

import edu.bellevue.cis530_week5.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {

    List<Student> findByMajor(String major);

    List<Student> findByEnrollmentYear(Integer enrollmentYear);
}
