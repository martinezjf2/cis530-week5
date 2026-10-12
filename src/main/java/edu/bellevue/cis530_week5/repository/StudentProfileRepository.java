
package edu.bellevue.cis530_week5.repository;

import edu.bellevue.cis530_week5.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentProfileRepository
        extends JpaRepository<StudentProfile, Long> {

}