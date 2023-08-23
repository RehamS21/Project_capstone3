package com.example.labority_project.Repository;

import com.example.labority_project.Model.Laboratories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboartoriesRepository extends JpaRepository<Laboratories , Integer> {

    Laboratories findLaboratoriesById(Integer id);
}
