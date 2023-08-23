package com.example.labority_project.Repository;

import com.example.labority_project.Model.TestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TestTypeRepository extends JpaRepository<TestType , Integer> {

    TestType findTestTypeById(Integer id);

}
