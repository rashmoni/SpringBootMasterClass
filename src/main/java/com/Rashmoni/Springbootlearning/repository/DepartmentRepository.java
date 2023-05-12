package com.Rashmoni.Springbootlearning.repository;

import com.Rashmoni.Springbootlearning.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
     @Query
    public Department findByDepartmentNameIgnoreCase(String departmentName);
}



