package com.restapijwt.repository;


import com.restapijwt.dto.UserProjection;
import com.restapijwt.entity.Company;
import com.restapijwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(value = "select * from users",
            nativeQuery = true)
    List<UserProjection> getAll();


}
