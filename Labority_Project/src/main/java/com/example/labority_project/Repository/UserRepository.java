package com.example.labority_project.Repository;

import com.example.labority_project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {
    User findUserById(Integer id);
}
