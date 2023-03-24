package com.learning.swd.repository;
import com.learning.swd.models.Entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentsRespository extends JpaRepository<Students,Long>{

    List<Students> findStudentsById(Long id);

    List<Students> findStudentsByNameContaining(String name);

    Boolean existsByName(String name);
}
