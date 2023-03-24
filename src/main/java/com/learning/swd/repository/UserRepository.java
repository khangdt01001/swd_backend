package com.learning.swd.repository;

import com.learning.swd.models.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByUsernameAndPassword(String username,String pass);

    @Query(value = "SELECT * FROM user where name like :name and password like :pass", nativeQuery = true)
    User login (@Param("name") String name, @Param("pass") String pass );

    User findUserById(Long id);

    Boolean existsByUsername(String name);
}
