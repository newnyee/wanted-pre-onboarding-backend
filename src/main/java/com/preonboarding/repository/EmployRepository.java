package com.preonboarding.repository;

import com.preonboarding.entity.Company;
import com.preonboarding.entity.Employ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface EmployRepository extends JpaRepository<Employ, Long> {

    Optional<Employ> findByEmployIdAndCompany(Long id, Company company);

    List<Employ> findByCompanyAndEmployIdNot(Company company, Long employId);
}
