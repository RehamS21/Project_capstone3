package com.example.labority_project.Repository;


import com.example.labority_project.Model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer> {
    UserDetails findUserDetailsById(Integer id);
}